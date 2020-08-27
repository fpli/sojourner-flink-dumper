package connectors.kafka;

import connectors.kafka.schema.MiscEventDeserializationSchema;
import model.MiscEvent;
import org.apache.flink.api.common.serialization.DeserializationSchema;

public class DeserializationSchemaManager {

  public static <T> DeserializationSchema<?> getSchema(Class<T> clazz) {

    if (clazz.isAssignableFrom(MiscEvent.class)) {
      return new MiscEventDeserializationSchema();
    }

    throw new IllegalStateException("Cannot find deserialization schema");
  }
}
