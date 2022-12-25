package com.ebay.epic.sojourner.flink.pipeline;

import com.ebay.epic.sojourner.common.constant.SessionType;
import com.ebay.epic.sojourner.common.env.FlinkEnvUtils;
import com.ebay.epic.sojourner.common.model.SojWatermark;
import com.ebay.epic.sojourner.flink.connectors.kafka.builder.FlinkKafkaSinkBuilder;
import com.ebay.epic.sojourner.flink.connectors.kafka.builder.ProcessFunctionBuilder;
import com.ebay.epic.sojourner.flink.connectors.kafka.builder.SourceDataStreamBuilder;
import com.ebay.epic.sojourner.flink.function.ExtractWatermarkProcessFunction;
import com.ebay.epic.sojourner.utils.DataCenter;
import com.ebay.epic.sojourner.utils.Property;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;

import static com.ebay.epic.sojourner.common.env.FlinkEnvUtils.getString;
import static com.ebay.epic.sojourner.utils.Property.*;

public class KafkaToHdfsBaseJob {

    public static StreamExecutionEnvironment streamExecutionEnvironmentBuilder(String[] args) {
        // 0.0 Prepare execution environment
        // 0.1 UBI configuration
        // 0.2 Flink configuration
        StreamExecutionEnvironment executionEnvironment = FlinkEnvUtils.prepare(args);
        return executionEnvironment;
    }

    public <T> DataStream<T> consumerBuilder(StreamExecutionEnvironment see,
                                             DataCenter dc, KafkaDeserializationSchema<T> kds) {
        SourceDataStreamBuilder<T> dataStreamBuilder = new SourceDataStreamBuilder<>(see, dc);
        // 1. Rheos Consumer
        // 1.1 Consume RawEvent from Rheos PathFinder topic
        // 1.2 Assign timestamps and emit watermarks.
        DataStream<T> rawEventDataStream = dataStreamBuilder
                .operatorName(SOURCE_OPERATOR_NAME_BASE)
                .uid(SOURCE_UID_BASE)
                .slotGroup(SOURCE_SLOT_SHARE_GROUP_BASE)
                .outOfOrderlessInMin(FLINK_APP_SOURCE_OFO_BASE)
                .fromTimestamp(FLINK_APP_SOURCE_FROM_TS_BASE)
                .parallelism(SOURCE_PARALLELISM)
                .idleSourceTimeout(FLINK_APP_SOURCE_TIM_BASE)
                .build(kds);
        return rawEventDataStream;
    }

    public <IN> SingleOutputStreamOperator<SojWatermark> processFunctionBuilder(DataStream<IN> dataStream) {
        ProcessFunctionBuilder<IN,SojWatermark> processFunctionBuilder =
                new ProcessFunctionBuilder<>(dataStream, false);

        // generate the watermark
        SingleOutputStreamOperator<SojWatermark> processDS = processFunctionBuilder
                .operatorName(PROCESS_OP_NAME)
                .uid(PPROCESS_UID)
                .slotGroup(PROCESS_SLOT_SHARE_GROUP)
                .parallelism(PROCESS_PARALLELISM)
                .process(new ExtractWatermarkProcessFunction<>(getString(Property.FLINK_APP_METRIC_NAME)))
                .build();
        return processDS;
    }

    public <T> void hdfsSinkBuilder(DataStream<T> dataStream, SessionType sessionType) {
        FlinkKafkaSinkBuilder<T> flinkKafkaSinkBuilder =
                new FlinkKafkaSinkBuilder<>(dataStream, sessionType);
        flinkKafkaSinkBuilder
                .parallelism(SINK_HDFS_PARALLELISM)
                .operatorName(SINK_OPERATOR_NAME_BASE)
                .uid(SINK_UID_BASE)
                .slotGroup(SINK_SLOT_SHARE_GROUP_BASE)
                .path(HDFS_DUMP_PATH)
                .build();
    }


}
