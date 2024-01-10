package com.ebay.epic.sojourner.flink.pipeline;

import com.ebay.epic.sojourner.common.constant.EventType;
import com.ebay.epic.sojourner.common.env.FlinkEnvUtils;
import com.ebay.epic.sojourner.common.model.AkamaiLog;
import com.ebay.epic.sojourner.common.model.SojWatermark;
import com.ebay.epic.sojourner.flink.connectors.kafka.schema.AkamaiLogDeserializationSchema;
import com.ebay.epic.sojourner.flink.connectors.kafka.schema.AkamaiLogKafkaDeserializationSchemaWrapper;
import com.ebay.epic.sojourner.flink.function.ExtractWatermarkProcessFunction;
import com.ebay.epic.sojourner.utils.DataCenter;
import com.ebay.epic.sojourner.utils.Property;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import static com.ebay.epic.sojourner.common.env.FlinkEnvUtils.getString;
import static com.ebay.epic.sojourner.utils.Property.*;

public class AkamaiLogDumperJob extends KafkaToHdfsBaseJob {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment see = streamExecutionEnvironmentBuilder(args);

        AkamaiLogDumperJob akamaiLogDumperJob = new AkamaiLogDumperJob();

        // build source data
        DataStream<AkamaiLog> dataStream = akamaiLogDumperJob.consumerBuilder(see,
                DataCenter.LVS,
                new AkamaiLogKafkaDeserializationSchemaWrapper(
                        new AkamaiLogDeserializationSchema(getString(Property.RHEOS_KAFKA_REGISTRY_URL))));
        // hdfs sink
        akamaiLogDumperJob.hdfsSinkBuilder(dataStream,
                EventType.AKAMAI_EVENT);

        // extract timestamp
        SingleOutputStreamOperator<SojWatermark> watermarkDS = akamaiLogDumperJob.processFunctionBuilder(
                dataStream,
                new ExtractWatermarkProcessFunction<>(getString(Property.FLINK_APP_METRIC_NAME)));
        // watermark sink
        akamaiLogDumperJob.hdfsSinkBuilder(watermarkDS, EventType.AKAMAI_WATERMARK);


        // submit job
        FlinkEnvUtils.execute(see, getString(Property.FLINK_APP_NAME));
    }
}
