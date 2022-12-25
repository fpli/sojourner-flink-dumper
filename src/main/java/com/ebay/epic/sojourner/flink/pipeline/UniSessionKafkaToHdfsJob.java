package com.ebay.epic.sojourner.flink.pipeline;

import com.ebay.epic.sojourner.flink.function.ExtractWatermarkProcessFunction;
import com.ebay.epic.sojourner.flink.connectors.hdfs.DateTimeBucketAssignerForEventTime;
import com.ebay.epic.sojourner.flink.connectors.hdfs.HdfsConnectorFactory;
import com.ebay.epic.sojourner.flink.connectors.kafka.builder.SourceDataStreamBuilderLegacy;
import com.ebay.epic.sojourner.common.env.FlinkEnvUtils;
import model.SojWatermark;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import com.ebay.epic.sojourner.utils.Property;

import static com.ebay.epic.sojourner.common.env.FlinkEnvUtils.getInteger;
import static com.ebay.epic.sojourner.common.env.FlinkEnvUtils.getString;
import static com.ebay.epic.sojourner.utils.DataCenter.RNO;

@Deprecated
public class UniSessionKafkaToHdfsJob {

  public static void main(String[] args) throws Exception {

    final StreamExecutionEnvironment executionEnvironment = FlinkEnvUtils.prepare(args);

    Class<?> deserializeClass = Class.forName(FlinkEnvUtils.getString(Property.HDFS_DUMP_CLASS));
    String hdfsPath = FlinkEnvUtils.getString(Property.HDFS_DUMP_PATH);
    int sinkParallelNum = getInteger(Property.SINK_HDFS_PARALLELISM);

    // kafka source
    SourceDataStreamBuilderLegacy dataStreamBuilder =
            new SourceDataStreamBuilderLegacy<>(executionEnvironment, deserializeClass);

    DataStream sourceDataStream = dataStreamBuilder.buildOfDC(RNO);

    // extract timestamp
    SingleOutputStreamOperator<SojWatermark> sojEventWatermarkStream = sourceDataStream
            .process(new ExtractWatermarkProcessFunction<>(
                    getString(Property.FLINK_APP_METRIC_NAME)))
            .setParallelism(sinkParallelNum)
            .name(getString(Property.TIMESTAMP_EXTRACT_OPERATOR_NAME))
            .uid(getString(Property.TIMESTAMP_EXTRACT_UID));

    // sink timestamp to hdfs
    sojEventWatermarkStream
            .addSink(HdfsConnectorFactory.createWithParquet(
                    getString(Property.FLINK_APP_SINK_HDFS_WATERMARK_PATH), SojWatermark.class,
                    new DateTimeBucketAssignerForEventTime<>()))
            .setParallelism(getInteger(Property.SINK_HDFS_PARALLELISM))
            .name(getString(Property.SINK_OPERATOR_NAME_WATERMARK))
            .uid(getString(Property.SINK_UID_WATERMARK));

    // hdfs sink
    sourceDataStream
        .addSink(HdfsConnectorFactory.createWithParquet(hdfsPath,
                deserializeClass,
                new DateTimeBucketAssignerForEventTime<>()))
        .setParallelism(sinkParallelNum)
        .name(String.format("Hdfs Sink To Location: %s", hdfsPath))
        // .name(String.format("Hdfs Sink To Location: %s", hdfsPath.substring(29)))
        .uid("data-sink-id");

    // submit job
    FlinkEnvUtils
        .execute(executionEnvironment, FlinkEnvUtils.getString(Property.FLINK_APP_NAME));
  }
}
