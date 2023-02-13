package com.ebay.epic.sojourner.flink.connectors.kafka.schema;

import com.ebay.epic.sojourner.common.model.SojEvent;
import com.google.common.base.Charsets;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;

import java.util.Iterator;

public class UniSojEventKafkaDeSerSchemaWrapper implements
        KafkaDeserializationSchema<SojEvent> {

    private final AvroDeSerSchema<SojEvent> rawEventDeserializationSchema;
    public static final String FIELD_NAME="globalSessionId";
    public UniSojEventKafkaDeSerSchemaWrapper(
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
        Headers headers = record.headers();
        String globalSessionId=null;
        Iterator<Header> iterator = headers.iterator();
        while(iterator.hasNext())
        {
            Header next = iterator.next();
            if(next.key().equals(FIELD_NAME)){
                globalSessionId=new String(next.value(), Charsets.UTF_8);
            }
        }
        sojEvent.getApplicationPayload().put(FIELD_NAME,globalSessionId);
        return sojEvent;
    }

    @Override
    public TypeInformation<SojEvent> getProducedType() {
        return TypeInformation.of(SojEvent.class);
    }
}