package pipeline;

import connectors.hdfs.HdfsConnectorFactory;
import connectors.kafka.SourceDataStreamBuilder;
import env.FlinkEnvUtils;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import utils.Property;

import static utils.DataCenter.*;

public class SojSkewGuidKafkaToHdfsJobForProd {

  public static void main(String[] args) throws Exception {

    final StreamExecutionEnvironment executionEnvironment = FlinkEnvUtils.prepare(args);

    Class<?> deserializeClass = Class.forName(FlinkEnvUtils.getString(Property.HDFS_DUMP_CLASS));
    String hdfsPath = FlinkEnvUtils.getString(Property.HDFS_DUMP_PATH);
    int sinkParallelNum = FlinkEnvUtils.getInteger(Property.SINK_HDFS_PARALLELISM);

    // kafka source
    SourceDataStreamBuilder dataStreamBuilder = new SourceDataStreamBuilder<>(
        executionEnvironment, deserializeClass
    );

    DataStream sourceDataStreamForRNO = dataStreamBuilder.buildOfDC(RNO);
    DataStream sourceDataStreamForLVS = dataStreamBuilder.buildOfDC(LVS);
    DataStream sourceDataStreamForSLC = dataStreamBuilder.buildOfDC(SLC);

    // union all dc traffic
    DataStream sourceDataStream = sourceDataStreamForLVS
            .union(sourceDataStreamForSLC)
            .union(sourceDataStreamForRNO);

    // hdfs sink
    sourceDataStream
        .addSink(HdfsConnectorFactory.createWithParquet(hdfsPath, deserializeClass))
        .setParallelism(sinkParallelNum)
        .name(String.format("Hdfs Sink To Location: %s", hdfsPath))
        // .name(String.format("Hdfs Sink To Location: %s", hdfsPath.substring(29)))
        .uid("sink-id");

    // submit job
    FlinkEnvUtils
        .execute(executionEnvironment, FlinkEnvUtils.getString(Property.FLINK_APP_NAME));
  }
}
