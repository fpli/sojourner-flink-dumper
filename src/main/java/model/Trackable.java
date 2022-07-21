package model;

import lombok.Data;

@Data
public class Trackable {
    private String trackableId;
    private String parentTrackableId;
    private String entityId;
    private String instanceId;
    private String description;
    private String correlationId;
    private String screenId;
    private String entityType;
}
