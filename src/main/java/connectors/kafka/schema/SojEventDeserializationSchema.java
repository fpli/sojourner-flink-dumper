package connectors.kafka.schema;

import connectors.kafka.factory.RheosEventSerdeFactory;
import io.ebay.rheos.schema.event.RheosEvent;
import model.SojEvent;
import model.UtpEvent;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SojEventDeserializationSchema implements DeserializationSchema<SojEvent> {

    @Override
    public SojEvent deserialize(byte[] message) throws IOException {

        RheosEvent rheosEvent =
                RheosEventSerdeFactory.getRheosEventHeaderDeserializer().deserialize(null, message);
        GenericRecord genericRecord =
                RheosEventSerdeFactory.getRheosEventDeserializer().decode(rheosEvent);

        long eventCreateTimestamp = rheosEvent.getEventCreateTimestamp();
        String guid = getString(genericRecord.get("guid"));
        Long eventTimestamp = getLong(genericRecord.get("eventTimestamp"));
        Integer pageId = getInteger(genericRecord.get("pageId"));

        String clickId = getString(genericRecord.get("clickId"));
        String sessionId = getString(genericRecord.get("sessionId"));
        String seqNum = getString(genericRecord.get("seqNum"));
        String ciid = getString(genericRecord.get("ciid"));
        String siid = getString(genericRecord.get("siid"));
        Integer rdt = getInteger(genericRecord.get("rdt"));
        Boolean iframe = getBoolean(genericRecord.get("iframe"));
        Map< String, String> applicationPayload = new HashMap<>();
        Map<Utf8, Utf8> applicationPayloadUTF = (Map<Utf8, Utf8>) genericRecord.get("applicationPayload");
        if (applicationPayloadUTF != null) {
            for (Map.Entry<Utf8, Utf8> entry : applicationPayloadUTF.entrySet()) {
                applicationPayload.put(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        Map< String, String> clientData = new HashMap<>();
        Map<Utf8, Utf8> clientDataUTF = (Map<Utf8, Utf8>) genericRecord.get("clientData");
        if (clientDataUTF != null) {
            for (Map.Entry<Utf8, Utf8> entry : clientDataUTF.entrySet()) {
                clientData.put(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        Long sessionSkey = getLong(genericRecord.get("sessionSkey"));
        Long sessionStartDt = getLong(genericRecord.get("sessionStartDt"));
        Long currentImprId = getLong(genericRecord.get("currentImprId"));
        Long sourceImprId = getLong(genericRecord.get("sourceImprId"));

        Map<String,String> payload=(Map<String, String>) genericRecord.get("payload");
        return new SojEvent(eventCreateTimestamp, guid, "dummyId", eventTimestamp, pageId,
                clickId,sessionId,seqNum,ciid,siid,rdt,iframe,applicationPayload,clientData,sessionSkey,sessionStartDt,
                currentImprId,sourceImprId);
    }

    @Override
    public boolean isEndOfStream(SojEvent nextElement) {
        return false;
    }

    @Override
    public TypeInformation<SojEvent> getProducedType() {
        return TypeInformation.of(SojEvent.class);
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
