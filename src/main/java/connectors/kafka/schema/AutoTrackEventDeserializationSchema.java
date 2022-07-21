package connectors.kafka.schema;

import connectors.kafka.factory.RheosEventSerdeFactory;
import io.ebay.rheos.schema.event.RheosEvent;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import model.*;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AutoTrackEventDeserializationSchema implements DeserializationSchema<AutoTrackEvent> {

    @Override
    public AutoTrackEvent deserialize(byte[] message) throws IOException {

        RheosEvent rheosEvent =
                RheosEventSerdeFactory.getRheosEventHeaderDeserializer().deserialize(null, message);
        GenericRecord genericRecord =
                RheosEventSerdeFactory.getRheosEventDeserializer().decode(rheosEvent);

        long eventCreateTimestamp = rheosEvent.getEventCreateTimestamp();
        String guid = getString(genericRecord.get("guid"));
        Long sessionId = getLong(genericRecord.get("sessionId"));
        String sessionStartDt = getString(genericRecord.get("sessionStartDt"));
        Long userId = getLong(genericRecord.get("userId"));
        Integer siteId = getInteger(genericRecord.get("siteId"));
        String agentVersion = getString(genericRecord.get("agentVersion"));
        GenericRecord activityRecord= (GenericRecord)genericRecord.get("activity");
        Activity activity = new Activity();
        activity.setTimestamp(getLong(activityRecord.get("timestamp")));
        activity.setDuration(getLong(activityRecord.get("duration")));
        activity.setRatio(getDouble(activityRecord.get("ratio")));
        activity.setViewportWidth(getLong(activityRecord.get("viewportWidth")));
        activity.setViewportHeight(getLong(activityRecord.get("viewportHeight")));
        activity.setCategory(ActivityCategory.valueOf(getString(activityRecord.get("category"))));
        activity.setType(getString(activityRecord.get("type")));
        activity.setReferer(getString(activityRecord.get("referer")));
        Map<String, String> details = new HashMap();
        Map<String, String> detailsTemp =  (Map<String, String>)activityRecord.get("details");
//        Map<Utf8, Utf8> detailsUTF = (Map<Utf8, Utf8>) activityRecord.get("details");
        if(MapUtils.isNotEmpty(detailsTemp)) {
//            log.error("details length:{}", detailsTemp.size());
//            log.error("details values:{}", detailsTemp);
            for(Map.Entry<String,String> entry:detailsTemp.entrySet()){
                if(StringUtils.isBlank(entry.getKey())){
                    if(StringUtils.isBlank(entry.getValue())){
                    }else{
                        details.put("",entry.getValue());
                    }
                }else{
                    if(StringUtils.isBlank(entry.getValue())){
                        details.put(entry.getKey(),"");
                    }else{
                        details.put(entry.getKey(),entry.getValue());
                    }
                }
            }
        }
        activity.setDetails(details);
        GenericRecord contextRecord= (GenericRecord)genericRecord.get("context");
        Context context = new Context();
        if(contextRecord!=null) {
            context.setClientIp(getString(contextRecord.get("clientIp")));
            context.setAppId(getString(contextRecord.get("appId")));
            context.setDeviceInfo(null);
            context.setOs(getString(contextRecord.get("os")));
            context.setVersion(getString(contextRecord.get("version")));
            context.setScreenDPI(getLong(contextRecord.get("screenDPI")));
            context.setScreenHeight(getLong(contextRecord.get("screenHeight")));
            context.setScreenWidth(getLong(contextRecord.get("screenWidth")));
            context.setUserAgent(getString(contextRecord.get("userAgent")));
            context.setUserLang(getString(contextRecord.get("userLang")));

            context.setUtcOffset(getString(contextRecord.get("utcOffset")));
            context.setUserLang(getString(contextRecord.get("userLang")));

            Map<String, String> others = new HashMap();
//            Map<Utf8, Utf8> othersUTF = (Map<Utf8, Utf8>) contextRecord.get("others");
            Map<String, String> othersTemp =  (Map<String, String>) contextRecord.get("others");
//        Map<Utf8, Utf8> detailsUTF = (Map<Utf8, Utf8>) activityRecord.get("details");
            if(MapUtils.isNotEmpty(othersTemp)) {
//                log.error("others length:{}", othersTemp.size());
//                log.error("others values:{}", othersTemp);
                for(Map.Entry<String,String> entry:othersTemp.entrySet()){
                    if(StringUtils.isBlank(entry.getKey())){
                        if(StringUtils.isBlank(entry.getValue())){
                        }else{
                            others.put("",entry.getValue());
                        }
                    }else{
                        if(StringUtils.isBlank(entry.getValue())){
                            others.put(entry.getKey(),"");
                        }else{
                            others.put(entry.getKey(),entry.getValue());
                        }
                    }
                }
            }
            context.setOthers(others);
            context.setOthers(others);
        }
        GenericRecord trackableRecord= (GenericRecord)genericRecord.get("trackable");
        Trackable trackable = new Trackable();
        if(trackableRecord!=null) {
            trackable.setTrackableId(getString(trackableRecord.get("trackableId")));
            trackable.setParentTrackableId(getString(trackableRecord.get("parentTrackableId")));
            trackable.setEntityId(getString(trackableRecord.get("entityId")));
            trackable.setEntityType(getString(trackableRecord.get("entityType")));
            trackable.setInstanceId(getString(trackableRecord.get("instanceId")));
            trackable.setDescription(getString(trackableRecord.get("description")));
            trackable.setCorrelationId(getString(trackableRecord.get("correlationId")));
            trackable.setScreenId(getString(trackableRecord.get("screenId")));
        }
        return new AutoTrackEvent(eventCreateTimestamp, guid, "dummyId", sessionId, sessionStartDt,
                userId,siteId,agentVersion,context,trackable,activity);
    }

    @Override
    public boolean isEndOfStream(AutoTrackEvent nextElement) {
        return false;
    }

    @Override
    public TypeInformation<AutoTrackEvent> getProducedType() {
        return TypeInformation.of(AutoTrackEvent.class);
    }

    private Integer getInteger(Object o) {
        if (StringUtils.isEmpty(getString(o))) {
            return null;
        } else {
            return Integer.valueOf(getString(o));
        }
    }

    private boolean getBoolean(Object o) {
        if (StringUtils.isEmpty(getString(o))) {
            return false;
        } else {
            return Boolean.valueOf(getString(o));
        }
    }

    private Long getLong(Object o) {
        if (StringUtils.isEmpty(getString(o))) {
            return null;
        } else {
            return Long.valueOf(getString(o));
        }
    }

    private Double getDouble(Object o) {
        if (StringUtils.isEmpty(getString(o))) {
            return null;
        } else {
            return Double.valueOf(getString(o));
        }
    }
    private String getString(Object o) {
        return (o != null && !"null".equals(o.toString())) ? o.toString() : null;
    }
}
