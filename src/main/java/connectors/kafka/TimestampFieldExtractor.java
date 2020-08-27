package connectors.kafka;

import model.MiscEvent;

public class TimestampFieldExtractor {

  public static <T> long getField(T t) {

    if (t instanceof MiscEvent) {
      MiscEvent miscEvent = (MiscEvent) t;
      return miscEvent.getEventCreateTimestamp();
    } else {
      throw new IllegalStateException("Cannot extract timestamp filed for generate watermark");
    }
  }
}
