package com.ebay.epic.sojourner.flink.connectors.kafka.schema;

import com.ebay.epic.sojourner.common.model.UniSession;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

public class UniSessionDeserializationSchema implements DeserializationSchema<UniSession> {

    private transient DatumReader<UniSession> reader;

    @Override
    public UniSession deserialize(byte[] message) throws IOException {
        if (reader == null) {
            reader = new SpecificDatumReader<>(UniSession.class);
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
    public boolean isEndOfStream(UniSession nextElement) {
        return false;
    }

    @Override
    public TypeInformation<UniSession> getProducedType() {
        return TypeInformation.of(UniSession.class);
    }
}
