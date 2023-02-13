package com.ebay.epic.sojourner.common.constant;

import com.ebay.epic.sojourner.common.model.UniSession;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.util.OutputTag;

public class OutputTagConstants {

  public static OutputTag<UniSession> crossDaySessionOutputTag =
      new OutputTag<>("crossday-session-output-tag", TypeInformation.of(UniSession.class));

  public static OutputTag<UniSession> openSessionOutputTag =
      new OutputTag<>("open-session-output-tag", TypeInformation.of(UniSession.class));

  public static OutputTag<UniSession> sameDaySessionOutputTag =
          new OutputTag<>("sameday-session-output-tag", TypeInformation.of(UniSession.class));
}
