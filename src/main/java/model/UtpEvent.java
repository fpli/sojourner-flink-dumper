package model;

import lombok.*;

import java.util.Map;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Deprecated
public class UtpEvent {
    private final long eventCreateTimestamp;
    private final String guid;
    private @NonNull String globalSessionId;
    private final String eventId;
    private final String producerEventId;
    private final long eventTs;
    private final long producerEventTs;
    private final String rlogId;
    private  final String trackingId;
    private final Long userId;
    private final String publicUserId;
    private Long encryptedUserId;
    private String idfa;
    private String gadid;
    private String deviceId;
    private String channelType;
    private String actionType;
    private String partner;
    private String campaignId;
    private String rotationId;
    private Integer siteId;
    private String url;
    private String referer;
    private String userAgent;
    private String deviceFamily;
    private String deviceType;
    private String browserFamily;
    private String browserVersion;
    private String osFamily;
    private String osVersion;
    private String appVersion;
    private String appId;
    private String service;
    private String server;
    private String remoteIp;
    private Integer pageId;
    private Integer geoId;
    private boolean isBot;
    private @NonNull Map<String,String> payload;


}
