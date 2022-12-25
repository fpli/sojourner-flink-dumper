package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Deprecated
public class SkewGuid {

  private long eventCreateTimestamp;
  private String guid;
  private String cguid;
  private String username;
  private String userId;
  private String eventType;
  private Long counter;

}
