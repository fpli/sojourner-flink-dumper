package model;

import lombok.Data;

import java.util.Map;

@Data
@Deprecated
public class Context {
    private String appId;
    private String version;
    private String os;
    private String utcOffset;
    private String clientIp;
    private String userLang;
    private String userAgent;
    private Long screenWidth;
    private Long screenHeight;
    private Long screenDPI;
    private MobileDeviceContext deviceInfo;
    private Map<String,String> others;
}
