package connectors.kafka.assigner;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import utils.TimestampFieldExtractor;

import java.util.Date;

@Slf4j
public class SojSerializableTimestampAssigner<T> implements SerializableTimestampAssigner<T> {

  @Override
  public long extractTimestamp(T element, long recordTimestamp) {

    long field = System.currentTimeMillis();
    try {
      field = TimestampFieldExtractor.getField(element); //TimestampFieldExtractor.getField(element);
    } catch (Exception e) {
      log.warn("extract timestamp failed" + field);
    }

    return field;
  }
}
