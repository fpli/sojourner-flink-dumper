package com.ebay.epic.sojourner.flink.pipeline;

import com.ebay.epic.sojourner.common.constant.EventType;
import com.ebay.epic.sojourner.common.env.FlinkEnvUtils;
import com.ebay.epic.sojourner.common.model.MiscEvent;
import com.ebay.epic.sojourner.common.model.SojWatermark;
import com.ebay.epic.sojourner.flink.connectors.kafka.schema.MiscEventDeserializationSchema;
import com.ebay.epic.sojourner.flink.connectors.kafka.schema.MiscEventKafkaDeSerSchemaWrapper;
import com.ebay.epic.sojourner.flink.function.ExtractWatermarkProcessFunction;
import com.ebay.epic.sojourner.utils.DataCenter;
import com.ebay.epic.sojourner.utils.Property;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

import static com.ebay.epic.sojourner.common.env.FlinkEnvUtils.getString;
import static com.ebay.epic.sojourner.utils.Property.*;

public class NewFeatureDumperJob extends KafkaToHdfsBaseJob {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment see = streamExecutionEnvironmentBuilder(args);
        NewFeatureDumperJob newFeatureDumperJob = new NewFeatureDumperJob();
        List<DataStream<MiscEvent>> sourcesList = new ArrayList<>();
        // consumer
        String[] dcs = getString(FLINK_APP_SOURCE_DC_BASE).split(",");
        for (String dc : dcs) {
            DataStream<MiscEvent> sourceDs = newFeatureDumperJob.consumerBuilder(see,
                    DataCenter.valueOf(dc.toUpperCase()),
                    new MiscEventKafkaDeSerSchemaWrapper(
                            new MiscEventDeserializationSchema()),true);
            sourcesList.add(sourceDs);
        }
        DataStream<MiscEvent> dsUnion = sourcesList.get(0);
        for (int i = 1; i < sourcesList.size(); i++) {
            dsUnion = dsUnion.union(sourcesList.get(i));
        }
        SingleOutputStreamOperator<SojWatermark> sojWatermarkDataStream = newFeatureDumperJob
                .processFunctionBuilder(dsUnion,
                        new ExtractWatermarkProcessFunction<>(getString(Property.FLINK_APP_METRIC_NAME)));
        newFeatureDumperJob.hdfsSinkBuilder(sojWatermarkDataStream,
                EventType.valueOf(getString(FLINK_APP_SINK_WATERMARK_BASE).toUpperCase()));
        newFeatureDumperJob.hdfsSinkBuilder(dsUnion,
                EventType.valueOf(getString(FLINK_APP_SINK_EVENTTYPE_BASE).toUpperCase()));

        FlinkEnvUtils.execute(see, getString(Property.FLINK_APP_NAME));
    }
}
