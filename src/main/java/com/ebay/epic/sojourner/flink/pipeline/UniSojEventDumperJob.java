package com.ebay.epic.sojourner.flink.pipeline;

import com.ebay.epic.sojourner.common.constant.EventType;
import com.ebay.epic.sojourner.common.env.FlinkEnvUtils;
import com.ebay.epic.sojourner.common.model.SojEvent;
import com.ebay.epic.sojourner.common.model.SojWatermark;
import com.ebay.epic.sojourner.flink.connectors.kafka.schema.AvroDeSerSchema;
import com.ebay.epic.sojourner.flink.connectors.kafka.schema.UbiLateEventKafkaDeSerSchemaWrapper;
import com.ebay.epic.sojourner.flink.connectors.kafka.schema.UniSojEventKafkaDeSerSchemaWrapper;
import com.ebay.epic.sojourner.flink.function.ExtractWatermarkProcessFunction;
import com.ebay.epic.sojourner.utils.DataCenter;
import com.ebay.epic.sojourner.utils.Property;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import static com.ebay.epic.sojourner.common.env.FlinkEnvUtils.getString;
import static com.ebay.epic.sojourner.utils.Property.*;

public class UniSojEventDumperJob extends KafkaToHdfsBaseJob {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment see = streamExecutionEnvironmentBuilder(args);
        UniSojEventDumperJob uniSojEventDumperJob = new UniSojEventDumperJob();
        // consumer
        DataStream<SojEvent> sourceDs = uniSojEventDumperJob.consumerBuilder(see,
                DataCenter.valueOf(getString(FLINK_APP_SOURCE_DC_BASE).toUpperCase()),
                new UniSojEventKafkaDeSerSchemaWrapper(
                        new AvroDeSerSchema(SojEvent.class)));
        SingleOutputStreamOperator<SojWatermark> sojWatermarkDataStream = uniSojEventDumperJob
                .processFunctionBuilder(sourceDs,
                        new ExtractWatermarkProcessFunction<>(getString(Property.FLINK_APP_METRIC_NAME)));
        uniSojEventDumperJob.hdfsSinkBuilder(sojWatermarkDataStream,
                EventType.valueOf(getString(FLINK_APP_SINK_WATERMARK_BASE).toUpperCase()));
        uniSojEventDumperJob.hdfsSinkBuilder(sourceDs,
                EventType.valueOf(getString(FLINK_APP_SINK_EVENTTYPE_BASE).toUpperCase()));

        FlinkEnvUtils.execute(see, getString(Property.FLINK_APP_NAME));
    }
}
