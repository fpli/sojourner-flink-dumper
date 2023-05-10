package com.ebay.epic.sojourner.flink.connectors.kafka.schema;

import com.ebay.epic.sojourner.common.model.MiscEvent;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class MiscEventKafkaDeSerSchemaWrapper implements
        KafkaDeserializationSchema<MiscEvent> {

    private final MiscEventDeserializationSchema miscEventDeserializationSchema;

    public MiscEventKafkaDeSerSchemaWrapper(
            MiscEventDeserializationSchema miscEventDeserializationSchema) {
        this.miscEventDeserializationSchema = miscEventDeserializationSchema;
    }

    @Override
    public void open(DeserializationSchema.InitializationContext context) throws Exception {
        miscEventDeserializationSchema.open(context);
    }

    @Override
    public boolean isEndOfStream(MiscEvent nextElement) {
        return false;
    }

    @Override
    public MiscEvent deserialize(ConsumerRecord<byte[], byte[]> record) throws Exception {
        Long produceTimestamp = record.timestamp();
        MiscEvent miscEvent = miscEventDeserializationSchema.deserialize(record.value());
        return miscEvent;
    }

    @Override
    public TypeInformation<MiscEvent> getProducedType() {
        return TypeInformation.of(MiscEvent.class);
    }
}