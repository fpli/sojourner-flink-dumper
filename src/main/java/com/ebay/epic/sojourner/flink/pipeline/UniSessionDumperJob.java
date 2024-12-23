package com.ebay.epic.sojourner.flink.pipeline;

import com.ebay.epic.sojourner.common.constant.OutputTagConstants;
import com.ebay.epic.sojourner.common.constant.SessionType;
import com.ebay.epic.sojourner.common.env.FlinkEnvUtils;
import com.ebay.epic.sojourner.common.model.SojWatermark;
import com.ebay.epic.sojourner.common.model.UniSession;
import com.ebay.epic.sojourner.flink.connectors.kafka.schema.AvroDeSerSchema;
import com.ebay.epic.sojourner.flink.connectors.kafka.schema.UniSessionKafkaDeserializationSchemaWrapper;
import com.ebay.epic.sojourner.flink.function.UniSessionSideOutputProcessFunction;
import com.ebay.epic.sojourner.utils.DataCenter;
import com.ebay.epic.sojourner.utils.Property;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import static com.ebay.epic.sojourner.common.env.FlinkEnvUtils.getString;
import static com.ebay.epic.sojourner.utils.Property.FLINK_APP_SOURCE_DC_BASE;

public class UniSessionDumperJob extends KafkaToHdfsBaseJob {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment see = streamExecutionEnvironmentBuilder(args);
        UniSessionDumperJob uniSessionDumperJob = new UniSessionDumperJob();
        // consumer
        DataStream<UniSession> sourceDs = uniSessionDumperJob.consumerBuilder(see,
                DataCenter.valueOf(getString(FLINK_APP_SOURCE_DC_BASE).toUpperCase()),
                new UniSessionKafkaDeserializationSchemaWrapper(
                        new AvroDeSerSchema(UniSession.class)));
        SingleOutputStreamOperator<SojWatermark> sojWatermarkDataStream = uniSessionDumperJob
                .processFunctionBuilder(sourceDs,
                        new UniSessionSideOutputProcessFunction<>(getString(Property.FLINK_APP_METRIC_NAME)));
        DataStream<UniSession> sameDayDataStream = sojWatermarkDataStream
                .getSideOutput(OutputTagConstants.sameDaySessionOutputTag);
        DataStream<UniSession> crossDayDataStream = sojWatermarkDataStream
                .getSideOutput(OutputTagConstants.crossDaySessionOutputTag);
        DataStream<UniSession> openDataStream = sojWatermarkDataStream
                .getSideOutput(OutputTagConstants.openSessionOutputTag);
        uniSessionDumperJob.hdfsSinkBuilder(sojWatermarkDataStream, SessionType.WATERMARK_NONBOT);
        uniSessionDumperJob.hdfsSinkBuilder(openDataStream, SessionType.OPEN_NONBOT);
        uniSessionDumperJob.hdfsSinkBuilder(sameDayDataStream, SessionType.SAMEDAY_NONBOT);
        uniSessionDumperJob.hdfsSinkBuilder(crossDayDataStream, SessionType.CROSSDAY_NONBOT);
        FlinkEnvUtils.execute(see, getString(Property.FLINK_APP_NAME));
    }
}
