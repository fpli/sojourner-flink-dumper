package connectors.kafka.schema;

import connectors.kafka.factory.RheosEventSerdeFactory;
import io.ebay.rheos.schema.event.RheosEvent;
import model.UniSession;
import model.UtpEvent;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import sun.security.action.GetIntegerAction;
import sun.security.jca.GetInstance;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UtpEventDeserializationSchema implements DeserializationSchema<UtpEvent> {

    @Override
    public UtpEvent deserialize(byte[] message) throws IOException {

        RheosEvent rheosEvent =
                RheosEventSerdeFactory.getRheosEventHeaderDeserializer().deserialize(null, message);
        GenericRecord genericRecord =
                RheosEventSerdeFactory.getRheosEventDeserializer().decode(rheosEvent);

        long eventCreateTimestamp = rheosEvent.getEventCreateTimestamp();
        String guid = getString(genericRecord.get("guid"));
        String eventId = getString(genericRecord.get("eventId"));
        String producerEventId = getString(genericRecord.get("producerEventId"));
        Long eventTs = getLong(genericRecord.get("eventTs"));
        Long producerEventTs = getLong(genericRecord.get("producerEventTs"));
        String rlogId = getString(genericRecord.get("rlogId"));
        String trackingId = getString(genericRecord.get("trackingId"));
        Long userId = getLong(genericRecord.get("userId"));
        String publicUserId = getString(genericRecord.get("publicUserId"));
        Long encryptedUserId = getLong(genericRecord.get("encryptedUserId"));

        String idfa = getString(genericRecord.get("idfa"));
        String gadid = getString(genericRecord.get("gadid"));
        String deviceId = getString(genericRecord.get("deviceId"));
        String channelType = getString(genericRecord.get("channelType"));
        String actionType = getString(genericRecord.get("actionType"));
        String partner = getString(genericRecord.get("partner"));
        String campaignId = getString(genericRecord.get("campaignId"));
        String rotationId = getString(genericRecord.get("rotationId"));
        Integer siteId = getInteger(genericRecord.get("siteId"));
        String url = getString(genericRecord.get("url"));
        String referer = getString(genericRecord.get("referer"));
        String userAgent = getString(genericRecord.get("userAgent"));
        String deviceFamily = getString(genericRecord.get("deviceFamily"));
        String deviceType = getString(genericRecord.get("deviceType"));
        String browserFamily = getString(genericRecord.get("browserFamily"));

        String browserVersion = getString(genericRecord.get("browserVersion"));
        String osFamily = getString(genericRecord.get("osFamily"));
        String osVersion = getString(genericRecord.get("osVersion"));
        String appVersion = getString(genericRecord.get("appVersion"));
        String appId = getString(genericRecord.get("appId"));
        String service = getString(genericRecord.get("service"));

        String server = getString(genericRecord.get("server"));
        String remoteIp = getString(genericRecord.get("remoteIp"));
        Integer pageId = getInteger(genericRecord.get("pageId"));
        Integer geoId = getInteger(genericRecord.get("geoId"));
        Boolean isBot = getBoolean(genericRecord.get("isBot"));
        Map< String, String> payload = new HashMap<>();
        Map<Utf8, Utf8> payloadUTF = (Map<Utf8, Utf8>) genericRecord.get("payload");
        if (payloadUTF != null) {
            for (Map.Entry<Utf8, Utf8> entry : payloadUTF.entrySet()) {
                payload.put(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        return new UtpEvent(eventCreateTimestamp, guid, "dummyId", eventId, producerEventId,
                eventTs,producerEventTs,rlogId,trackingId,userId,publicUserId,
                encryptedUserId,idfa,gadid,deviceId,channelType,actionType,partner,campaignId,rotationId,siteId,url,
                referer,userAgent,deviceFamily,deviceType,browserFamily,browserVersion,osFamily,osVersion,
                appVersion,appId,service,server,remoteIp,pageId,geoId,isBot,payload);
    }

    @Override
    public boolean isEndOfStream(UtpEvent nextElement) {
        return false;
    }

    @Override
    public TypeInformation<UtpEvent> getProducedType() {
        return TypeInformation.of(UtpEvent.class);
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

    private String getString(Object o) {
        return (o != null && !"null".equals(o.toString())) ? o.toString() : null;
    }
}
