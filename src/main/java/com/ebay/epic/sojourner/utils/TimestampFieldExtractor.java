package com.ebay.epic.sojourner.utils;

import com.ebay.epic.sojourner.common.model.AkamaiLog;
import com.ebay.epic.sojourner.common.model.UniSession;
import model.*;

public class TimestampFieldExtractor {

  public static <T> long getField(T t) {

    if (t instanceof MiscEvent) {
      MiscEvent miscEvent = (MiscEvent) t;
      return miscEvent.getEventCreateTimestamp();
    } else if (t instanceof SkewGuid) {
      SkewGuid skewGuid = (SkewGuid) t;
      return skewGuid.getEventCreateTimestamp();
    }else if (t instanceof SketchEvent) {
      SketchEvent sketchEvent = (SketchEvent) t;
      return sketchEvent.getEventCreateTimestamp();
    }else if (t instanceof SojEvent) {
      SojEvent sojEvent = (SojEvent) t;
      return sojEvent.getEventTimestamp();
    } else if (t instanceof UtpEvent) {
      UtpEvent utpEvent = (UtpEvent) t;
      return utpEvent.getProducerEventTs();
    }else if (t instanceof AutoTrackEvent) {
      AutoTrackEvent autoTrackEvent = (AutoTrackEvent) t;
      return autoTrackEvent.getActivity().getTimestamp();
    } else if (t instanceof UniSession) {
      UniSession uniSession = (UniSession) t;
      return uniSession.getSessionStartDt();
    } else if (t instanceof AkamaiLog) {
      AkamaiLog akamaiLog = (AkamaiLog) t;
      return akamaiLog.getReqTimeSec();
    } else {
      throw new IllegalStateException("Cannot extract timestamp filed for generate watermark");
    }
  }
}
