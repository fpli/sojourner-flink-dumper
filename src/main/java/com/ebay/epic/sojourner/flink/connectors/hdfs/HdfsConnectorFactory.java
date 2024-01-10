package com.ebay.epic.sojourner.flink.connectors.hdfs;

import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.functions.sink.filesystem.BucketAssigner;
import org.apache.flink.streaming.api.functions.sink.filesystem.OutputFileConfig;
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink;

public class HdfsConnectorFactory {

  public static <T> StreamingFileSink<T> createWithParquet(String sinkPath, Class<T> sinkClass,
                                                           BucketAssigner<T, String> bucketAssigner) {
    return StreamingFileSink.forBulkFormat(
        new Path(sinkPath), RichParquetAvroWriters.forAllowNullReflectRecord(sinkClass))
        .withBucketAssigner(bucketAssigner)
        .build();
  }
}
