package com.ebay.epic.sojourner.flink.connectors.kafka.schema;

import com.ebay.epic.sojourner.common.model.UniSession;
import model.*;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DeserializationSchemaManager {

    private static Map<Class, DeserializationSchema> deserializationSchemaMap = new ConcurrentHashMap<>();

    static {
        deserializationSchemaMap.put(MiscEvent.class, new MiscEventDeserializationSchema());
        deserializationSchemaMap.put(SkewGuid.class, new SkewGuidDeserializationSchema());
        deserializationSchemaMap.put(SketchEvent.class, new SketchEventDeserializationSchema());
        deserializationSchemaMap.put(UtpEvent.class, new UtpEventDeserializationSchema());
        deserializationSchemaMap.put(SojEvent.class, new SojEventDeserializationSchema());
        deserializationSchemaMap.put(AutoTrackEvent.class, new AutoTrackEventDeserializationSchema());
        deserializationSchemaMap.put(UniSession.class, new UniSessionDeserializationSchema());
    }

    public static <T> KafkaDeserializationSchema<T> getSchema(Class<T> clazz) {
        DeserializationSchema deserializationSchema = deserializationSchemaMap.get(clazz);
        if (deserializationSchema == null) {
            throw new IllegalStateException("Cannot find deserialization schema");
        } else {
            return new KafkaDeserializationSchemaWapper<T>(deserializationSchema, clazz);
        }
    }
}
