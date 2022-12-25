package com.ebay.epic.sojourner.flink.pipeline;

import com.ebay.epic.sojourner.flink.connectors.hdfs.DateTimeBucketAssignerForEventTime;
import com.ebay.epic.sojourner.flink.connectors.hdfs.HdfsConnectorFactory;
import com.ebay.epic.sojourner.flink.connectors.kafka.builder.SourceDataStreamBuilderLegacy;
import com.ebay.epic.sojourner.common.env.FlinkEnvUtils;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import com.ebay.epic.sojourner.utils.Property;

import static com.ebay.epic.sojourner.utils.DataCenter.*;

@Deprecated
public class SojSkewGuidKafkaToHdfsJob {

  public static void main(String[] args) throws Exception {

    final StreamExecutionEnvironment executionEnvironment = FlinkEnvUtils.prepare(args);

    Class<?> deserializeClass = Class.forName(FlinkEnvUtils.getString(Property.HDFS_DUMP_CLASS));
    String hdfsPath = FlinkEnvUtils.getString(Property.HDFS_DUMP_PATH);
    int sinkParallelNum = FlinkEnvUtils.getInteger(Property.SINK_HDFS_PARALLELISM);

    // kafka source
    SourceDataStreamBuilderLegacy dataStreamBuilder = new SourceDataStreamBuilderLegacy<>(
        executionEnvironment, deserializeClass
    );

    DataStream sourceDataStream = dataStreamBuilder.buildOfDC(RNO);

    // hdfs sink
    sourceDataStream
        .addSink(HdfsConnectorFactory.createWithParquet(hdfsPath, deserializeClass,
                new DateTimeBucketAssignerForEventTime<>()))
        .setParallelism(sinkParallelNum)
        .name(String.format("Hdfs Sink To Location: %s", hdfsPath))
        // .name(String.format("Hdfs Sink To Location: %s", hdfsPath.substring(29)))
        .uid("sink-id");

    // submit job
    FlinkEnvUtils
        .execute(executionEnvironment, FlinkEnvUtils.getString(Property.FLINK_APP_NAME));
  }
}
