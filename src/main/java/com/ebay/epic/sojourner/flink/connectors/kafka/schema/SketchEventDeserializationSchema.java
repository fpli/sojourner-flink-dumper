package com.ebay.epic.sojourner.flink.connectors.kafka.schema;

import com.ebay.epic.sojourner.flink.connectors.kafka.factory.RheosEventSerdeFactory;
import io.ebay.rheos.schema.event.RheosEvent;
import model.SketchEvent;
import org.apache.avro.generic.GenericRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

public class SketchEventDeserializationSchema implements DeserializationSchema<SketchEvent> {

    @Override
    public SketchEvent deserialize(byte[] message) throws IOException {

        RheosEvent rheosEvent =
                RheosEventSerdeFactory.getRheosEventHeaderDeserializer().deserialize(null, message);
        GenericRecord genericRecord =
                RheosEventSerdeFactory.getRheosEventDeserializer().decode(rheosEvent);

        long eventCreateTimestamp = rheosEvent.getEventCreateTimestamp();
        long eventSentTimestamp = rheosEvent.getEventSentTimestamp();
        String eventId = rheosEvent.getEventId();
        String producerId = rheosEvent.getProducerId();
        int schemaId = rheosEvent.getSchemaId();
        String guid = getString(genericRecord.get("guid"));
        String pageId = getString(genericRecord.get("pageId"));


        return new SketchEvent(eventCreateTimestamp,eventSentTimestamp,schemaId,eventId,producerId, guid, pageId);
    }

    @Override
    public boolean isEndOfStream(SketchEvent nextElement) {
        return false;
    }

    @Override
    public TypeInformation<SketchEvent> getProducedType() {
        return TypeInformation.of(SketchEvent.class);
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
