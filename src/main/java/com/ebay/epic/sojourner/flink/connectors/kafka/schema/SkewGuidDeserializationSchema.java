package com.ebay.epic.sojourner.flink.connectors.kafka.schema;

import com.ebay.epic.sojourner.flink.connectors.kafka.factory.RheosEventSerdeFactory;
import io.ebay.rheos.schema.event.RheosEvent;
import model.SkewGuid;
import org.apache.avro.generic.GenericRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

public class SkewGuidDeserializationSchema implements DeserializationSchema<SkewGuid> {

    @Override
    public SkewGuid deserialize(byte[] message) throws IOException {

        RheosEvent rheosEvent =
                RheosEventSerdeFactory.getRheosEventHeaderDeserializer().deserialize(null, message);
        GenericRecord genericRecord =
                RheosEventSerdeFactory.getRheosEventDeserializer().decode(rheosEvent);

        long eventCreateTimestamp = rheosEvent.getEventCreateTimestamp();
        String guid = getString(genericRecord.get("guid"));
        String cguid = getString(genericRecord.get("cguid"));
        String username = getString(genericRecord.get("username"));
        String userId = getString(genericRecord.get("userId"));
        String eventType = getString(genericRecord.get("eventType"));
        Long counter = getLong(genericRecord.get("counter"));

        return new SkewGuid(eventCreateTimestamp, guid, cguid, username, userId, eventType, counter);
    }

    @Override
    public boolean isEndOfStream(SkewGuid nextElement) {
        return false;
    }

    @Override
    public TypeInformation<SkewGuid> getProducedType() {
        return TypeInformation.of(SkewGuid.class);
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
