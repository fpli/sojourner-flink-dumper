package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SojEvent {

  private final long eventCreateTimestamp;
  private final String guid;
  private @NonNull String globalSessionId;
  private final long eventTimestamp;
  /** dataClassification=Internal|isEncrypted=false|description=mobile specific, map from mtsts, used for mobile case */
  private  Long eventCaptureTime;
  /** dataClassification=Internal|isEncrypted=false|description=request correlation id, map from tag rq */
  private  String requestCorrelationId;
  /** dataClassification=Internal|isEncrypted=false|description=correlation guid, map from tag n */
  private  String cguid;
  /** dataClassification=Internal|isEncrypted=false|description=Sojourner key for source id */
  private  String sid;
  /** dataClassification=Internal|isEncrypted=false|description=map from tag p */
  private  final Integer pageId;
  private  String pageName;
  /** dataClassification=Internal|isEncrypted=false|description=map from tag pgf */
  private  String pageFamily;
  /** dataClassification=Internal|isEncrypted=false|description=map from efam of pulsar event, event family */
  private  String eventFamily;
  /** dataClassification=Internal|isEncrypted=false|description=map from eactn of pulsar event, event action */
  private  String eventAction;
  /** dataClassification=Internal|isEncrypted=false|description=map from tag u/bu */
  private  String userId;
  /** dataClassification=Internal|isEncrypted=false|description=Click Id, map from tag c */
  private final String clickId;
  /** dataClassification=Internal|isEncrypted=false|description=Site ID, map from tag t */
  private  String siteId;
  /** dataClassification=Internal|isEncrypted=false|description=map from tag snid */
  private final String sessionId;
  /** dataClassification=Internal|isEncrypted=false|description=map from tag snsq */
  private final String seqNum;
  private final String ciid;
  private final String siid;
  /** dataClassification=Internal|isEncrypted=false|description=1 indicates that the command redirected to another URL */
  private final Integer rdt;
  /** dataClassification=Internal|isEncrypted=false|description=indicating that this is a registered user, map from tag regU */
  private  Integer regu;
  /** dataClassification=Internal|isEncrypted=false|description=map from tag _ifrm */
  private final Boolean iframe;
  /** dataClassification=Internal|isEncrypted=false|description=map from tag r */
  private  String refererHash;
  /** dataClassification=Internal|isEncrypted=false|description=search keyword, map from tag sQr */
  private  String sqr;
  /** dataClassification=Internal|isEncrypted=false|description=map from tag itm/item */
  private  String itemId;
  /** dataClassification=Internal|isEncrypted=false|description=map from tag flgs */
  private  String flags;
  /** dataClassification=Internal|isEncrypted=false|description=map from tag Referer */
  private  String urlQueryString;
  /** dataClassification=Internal|isEncrypted=false|description=web server, map from Server */
  private  String webServer;
  /** dataClassification=Internal|isEncrypted=false|description=map from tag ck/C */
  private  String cookies;
  /** dataClassification=Internal|isEncrypted=false|description=map from tag bott */
  private  Integer bot;
  private  String clientIP;
  /** dataClassification=Internal|isEncrypted=false|description=map from tag RemoteIP */
  private  String remoteIP;
  /** dataClassification=Internal|isEncrypted=false|description=map from tag Agent */
  private  String agentInfo;
  /** dataClassification=Internal|isEncrypted=false|description=application ID, map from tag app */
  private  String appId;
  /** dataClassification=Internal|isEncrypted=false|description=application version, map from mav */
  private  String appVersion;
  /** dataClassification=Internal|isEncrypted=false|description=operation system version, map from osv */
  private  String osVersion;
  /** dataClassification=Internal|isEncrypted=false|description=map from trffc_src */
  private  String trafficSource;
  /** dataClassification=Internal|isEncrypted=false|description=map from cbrnd */
  private  String cobrand;
  /** dataClassification=Internal|isEncrypted=false|description=map from dd_d */
  private  String deviceFamily;
  /** dataClassification=Internal|isEncrypted=false|description=map from dd_dc */
  private  String deviceType;
  /** dataClassification=Internal|isEncrypted=false|description=map from dd_bv */
  private  String browserVersion;
  /** dataClassification=Internal|isEncrypted=false|description=map from dd_bf */
  private  String browserFamily;
  /** dataClassification=Internal|isEncrypted=false|description=map from dd_os */
  private  String osFamily;
  /** dataClassification=Internal|isEncrypted=false|description=map from dd_osv */
  private  String enrichedOsVersion;
  private final Map< String, String> applicationPayload;
  /** CAL request log id */
  private  String rlogid;
  /** Includes ForwardFor, ContentLength, Script, Server, Encoding, TMachine, TStamp, TName, TStatus, TDuration, TPayload */
  private final Map< String, String> clientData;
  private  Long ingestTime;
  private final Long sessionSkey;
  private final Long sessionStartDt;
  private  Long sojDataDt;
  private  Integer version;
  private  Integer staticPageType;
  private  Integer reservedForFuture;
  private  String eventAttr;
  private final Long currentImprId;
  private final Long sourceImprId;
  private  Long oldSessionSkey;
  private  Boolean partialValidPage;
  private List< Integer> botFlags;
  private  Long icfBinary;
  private  Long eventCnt;
  private  String referrer;
  private  String forwardedFor;
  private  Boolean rv;
}
