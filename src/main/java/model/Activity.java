package model;

import lombok.Data;

import java.util.Map;

@Data
public class Activity {
    private long timestamp;
    private Long duration;
    private Double ratio;
    private Long viewportWidth;
    private Long viewportHeight;
    private ActivityCategory category;
    private String type;
    private String referer;
    private Map<String,String> details;
}
