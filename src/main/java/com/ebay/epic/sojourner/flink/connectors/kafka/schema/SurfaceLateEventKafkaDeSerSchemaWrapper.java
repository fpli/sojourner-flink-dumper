package com.ebay.epic.sojourner.flink.connectors.kafka.schema;

import com.ebay.epic.sojourner.common.model.SessionizedEvent;
import com.ebay.epic.sojourner.common.model.SurfaceTrackingEvent;
import com.ebay.epic.sojourner.common.model.UniSession;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class SurfaceLateEventKafkaDeSerSchemaWrapper implements
        KafkaDeserializationSchema<SessionizedEvent> {

    private final AvroDeSerSchema<SessionizedEvent> rawEventDeserializationSchema;

    public SurfaceLateEventKafkaDeSerSchemaWrapper(
            AvroDeSerSchema<SessionizedEvent> rawEventDeserializationSchema) {
        this.rawEventDeserializationSchema = rawEventDeserializationSchema;
    }

    @Override
    public void open(DeserializationSchema.InitializationContext context) throws Exception {
        rawEventDeserializationSchema.open(context);
    }

    @Override
    public boolean isEndOfStream(SessionizedEvent nextElement) {
        return false;
    }

    @Override
    public SessionizedEvent deserialize(ConsumerRecord<byte[], byte[]> record) throws Exception {
        Long produceTimestamp = record.timestamp();
        SessionizedEvent sessionizedEvent = rawEventDeserializationSchema.deserialize(record.value());
        return sessionizedEvent;
    }

    @Override
    public TypeInformation<SessionizedEvent> getProducedType() {
        return TypeInformation.of(SessionizedEvent.class);
    }
}