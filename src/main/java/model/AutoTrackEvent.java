package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Deprecated
public class AutoTrackEvent {

  private final long eventCreateTimestamp;
  private final String guid;
  private @NonNull String globalSessionId;
  private final long sessionId;
  private final String sessionStartDt;
  private final Long userId;
  private final Integer siteId;
  private final String agentVersion;
  private final Context context;
  private Trackable trackable;
  private final Activity activity;

}
