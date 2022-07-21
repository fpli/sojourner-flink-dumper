package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SketchEvent {

  private long eventCreateTimestamp;
  private long eventSentTimestamp;
  private  int schemaId;
  private String eventId;
  private String producerId;
  private String guid;
  private String pageId;

}
