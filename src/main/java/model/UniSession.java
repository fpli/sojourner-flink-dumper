package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UniSession {

  private long eventCreateTimestamp;
  private String guid;
  private String globalSessionId;
  private Long absStartTimestamp;
  private Long absEndTimestamp;
  private Long sessionStartDt;

}
