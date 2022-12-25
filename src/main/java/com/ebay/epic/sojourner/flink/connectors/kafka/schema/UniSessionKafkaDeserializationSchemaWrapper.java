package com.ebay.epic.sojourner.flink.connectors.kafka.schema;

import com.ebay.epic.sojourner.common.model.UniSession;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class UniSessionKafkaDeserializationSchemaWrapper implements
        KafkaDeserializationSchema<UniSession> {

    private final UniSessionDeserializationSchema rawEventDeserializationSchema;

    public UniSessionKafkaDeserializationSchemaWrapper(
            UniSessionDeserializationSchema rawEventDeserializationSchema) {
        this.rawEventDeserializationSchema = rawEventDeserializationSchema;
    }

    @Override
    public void open(DeserializationSchema.InitializationContext context) throws Exception {
        rawEventDeserializationSchema.open(context);
    }

    @Override
    public boolean isEndOfStream(UniSession nextElement) {
        return false;
    }

    @Override
    public UniSession deserialize(ConsumerRecord<byte[], byte[]> record) throws Exception {
        Long produceTimestamp = record.timestamp();
        UniSession uniSession = rawEventDeserializationSchema.deserialize(record.value());
        return uniSession;
    }

    @Override
    public TypeInformation<UniSession> getProducedType() {
        return TypeInformation.of(UniSession.class);
    }
}