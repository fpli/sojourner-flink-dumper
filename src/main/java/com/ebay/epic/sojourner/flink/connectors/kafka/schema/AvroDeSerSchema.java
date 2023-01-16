package com.ebay.epic.sojourner.flink.connectors.kafka.schema;

import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

public class AvroDeSerSchema<T> implements DeserializationSchema<T> {

    private transient DatumReader<T> reader;
    private Class<T> tClass;

    public AvroDeSerSchema(Class<T> tClass){
        this.tClass=tClass;
    }
    @Override
    public T deserialize(byte[] message) throws IOException {
        if (reader == null) {
            reader = new SpecificDatumReader<>(tClass);
        }
        Decoder decoder = null;
        try {
            decoder = DecoderFactory.get().binaryDecoder(message, null);
            return reader.read(null, decoder);
        } catch (IOException e) {
            throw new RuntimeException("Deserialize SojEvent error", e);
        }
    }

    @Override
    public boolean isEndOfStream(T nextElement) {
        return false;
    }

    @Override
    public TypeInformation<T> getProducedType() {
        return TypeInformation.of(tClass);
    }
}
