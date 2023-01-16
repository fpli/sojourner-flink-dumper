package com.ebay.epic.sojourner.flink.connectors.kafka.schema;

import com.ebay.epic.sojourner.common.model.SojEvent;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class UbiLateEventKafkaDeSerSchemaWrapper implements
        KafkaDeserializationSchema<SojEvent> {

    private final AvroDeSerSchema<SojEvent> rawEventDeserializationSchema;

    public UbiLateEventKafkaDeSerSchemaWrapper(
            AvroDeSerSchema<SojEvent> rawEventDeserializationSchema) {
        this.rawEventDeserializationSchema = rawEventDeserializationSchema;
    }

    @Override
    public void open(DeserializationSchema.InitializationContext context) throws Exception {
        rawEventDeserializationSchema.open(context);
    }

    @Override
    public boolean isEndOfStream(SojEvent nextElement) {
        return false;
    }

    @Override
    public SojEvent deserialize(ConsumerRecord<byte[], byte[]> record) throws Exception {
        Long produceTimestamp = record.timestamp();
        SojEvent sojEvent = rawEventDeserializationSchema.deserialize(record.value());
        return sojEvent;
    }

    @Override
    public TypeInformation<SojEvent> getProducedType() {
        return TypeInformation.of(SojEvent.class);
    }
}