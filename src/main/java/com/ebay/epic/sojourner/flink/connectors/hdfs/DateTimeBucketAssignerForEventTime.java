package com.ebay.epic.sojourner.flink.connectors.hdfs;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.apache.flink.core.io.SimpleVersionedSerializer;
import org.apache.flink.streaming.api.functions.sink.filesystem.BucketAssigner;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.SimpleVersionedStringSerializer;
import org.apache.flink.util.Preconditions;

public class DateTimeBucketAssignerForEventTime<IN> implements BucketAssigner<IN, String> {

  private static final long serialVersionUID = 1L;

  private static final String DEFAULT_FORMAT_STRING = "yyyyMMdd/HH";

  private final String formatString;

  private final ZoneId zoneId;

  private transient DateTimeFormatter dateTimeFormatter;

  public DateTimeBucketAssignerForEventTime() {
    this(DEFAULT_FORMAT_STRING);
  }

  public DateTimeBucketAssignerForEventTime(String formatString) {
    this(formatString, ZoneId.systemDefault());
  }

  public DateTimeBucketAssignerForEventTime(ZoneId zoneId) {
    this(DEFAULT_FORMAT_STRING, zoneId);
  }

  public DateTimeBucketAssignerForEventTime(String formatString, ZoneId zoneId) {
    this.formatString = Preconditions.checkNotNull(formatString);
    this.zoneId = Preconditions.checkNotNull(zoneId);
  }

  @Override
  public String getBucketId(IN element, Context context) {
    if (dateTimeFormatter == null) {
      dateTimeFormatter = DateTimeFormatter.ofPattern(formatString).withZone(zoneId);
    }
    String defaultTsStr = dateTimeFormatter.format(Instant.ofEpochMilli(context.timestamp()));
    StringBuilder customTsBuilder = new StringBuilder();
    customTsBuilder
        .append("dt=").append(defaultTsStr.substring(0, 8))
        .append("/hr=").append(defaultTsStr.substring(9));
    return customTsBuilder.toString();

  }

  @Override
  public SimpleVersionedSerializer<String> getSerializer() {
    return SimpleVersionedStringSerializer.INSTANCE;
  }

  @Override
  public String toString() {
    return "DateTimeBucketAssignerForEventTime{"
        + "formatString='"
        + formatString
        + '\''
        + ", zoneId="
        + zoneId
        + '}';
  }
}
