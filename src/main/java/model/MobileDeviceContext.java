package model;

import lombok.Data;

@Data
@Deprecated
public class MobileDeviceContext {
    private FormFactor formFactor;
    private String manufacturer;
    private String osVersion;
    private String model;
    private String networkCarrier;
    private String networkConnectionType;
    private String theme;
    private String countryId;
}
