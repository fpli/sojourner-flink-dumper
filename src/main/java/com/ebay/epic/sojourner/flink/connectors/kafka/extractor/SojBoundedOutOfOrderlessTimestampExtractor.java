package com.ebay.epic.sojourner.flink.connectors.kafka.extractor;

import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.time.Time;
import com.ebay.epic.sojourner.utils.TimestampFieldExtractor;

public class SojBoundedOutOfOrderlessTimestampExtractor<T> extends
    BoundedOutOfOrdernessTimestampExtractor<T> {

  private static final long serialVersionUID = -1902049986991898214L;

  public SojBoundedOutOfOrderlessTimestampExtractor(
      Time maxOutOfOrderless) {
    super(maxOutOfOrderless);
  }

  @Override
  public long extractTimestamp(T t) {

    long filed = TimestampFieldExtractor.getField(t);
    return filed;
  }
}
