package connectors.kafka.schema;

import connectors.kafka.factory.RheosEventSerdeFactory;
import io.ebay.rheos.schema.event.RheosEvent;
import model.SkewGuid;
import model.UniSession;
import org.apache.avro.generic.GenericRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

public class UniSessionDeserializationSchema implements DeserializationSchema<UniSession> {

    @Override
    public UniSession deserialize(byte[] message) throws IOException {

        RheosEvent rheosEvent =
                RheosEventSerdeFactory.getRheosEventHeaderDeserializer().deserialize(null, message);
        GenericRecord genericRecord =
                RheosEventSerdeFactory.getRheosEventDeserializer().decode(rheosEvent);

        long eventCreateTimestamp = rheosEvent.getEventCreateTimestamp();
        String guid = getString(genericRecord.get("guid"));
        String globalSessionId = getString(genericRecord.get("globalSessionId"));
        Long absStartTimestamp = getLong(genericRecord.get("absStartTimestamp"));
        Long absEndTimestamp = getLong(genericRecord.get("absEndTimestamp"));
        Long sessionStartDt = getLong(genericRecord.get("sessionStartDt"));


        return new UniSession(eventCreateTimestamp, guid, globalSessionId, absStartTimestamp, absEndTimestamp, sessionStartDt);
    }

    @Override
    public boolean isEndOfStream(UniSession nextElement) {
        return false;
    }

    @Override
    public TypeInformation<UniSession> getProducedType() {
        return TypeInformation.of(UniSession.class);
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
