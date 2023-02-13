package com.ebay.epic.sojourner.flink.pipeline;

import com.ebay.epic.sojourner.common.constant.EventType;
import com.ebay.epic.sojourner.common.env.FlinkEnvUtils;
import com.ebay.epic.sojourner.common.model.SojEvent;
import com.ebay.epic.sojourner.common.model.SojWatermark;
import com.ebay.epic.sojourner.flink.connectors.kafka.schema.AvroDeSerSchema;
import com.ebay.epic.sojourner.flink.connectors.kafka.schema.UbiLateEventKafkaDeSerSchemaWrapper;
import com.ebay.epic.sojourner.flink.function.ExtractWatermarkProcessFunction;
import com.ebay.epic.sojourner.utils.DataCenter;
import com.ebay.epic.sojourner.utils.Property;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import static com.ebay.epic.sojourner.common.env.FlinkEnvUtils.getString;
import static com.ebay.epic.sojourner.utils.DataCenter.RNO;
import static com.ebay.epic.sojourner.utils.Property.*;

public class LateEventUbiDumperJob extends KafkaToHdfsBaseJob {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment see = streamExecutionEnvironmentBuilder(args);
        LateEventUbiDumperJob lateEventSurfaceDumperJob = new LateEventUbiDumperJob();
        // consumer
        DataStream<SojEvent> sourceDs = lateEventSurfaceDumperJob.consumerBuilder(see,
                DataCenter.valueOf(getString(FLINK_APP_SOURCE_DC_BASE).toUpperCase()),
                new UbiLateEventKafkaDeSerSchemaWrapper(
                        new AvroDeSerSchema(SojEvent.class)));
        SingleOutputStreamOperator<SojWatermark> sojWatermarkDataStream = lateEventSurfaceDumperJob
                .processFunctionBuilder(sourceDs,
                        new ExtractWatermarkProcessFunction<>(getString(Property.FLINK_APP_METRIC_NAME)));
        lateEventSurfaceDumperJob.hdfsSinkBuilder(sojWatermarkDataStream,
                EventType.valueOf(getString(FLINK_APP_SINK_WATERMARK_BASE).toUpperCase()));
        lateEventSurfaceDumperJob.hdfsSinkBuilder(sourceDs,
                EventType.valueOf(getString(FLINK_APP_SINK_EVENTTYPE_BASE).toUpperCase()));

        FlinkEnvUtils.execute(see, getString(Property.FLINK_APP_NAME));
    }
}
