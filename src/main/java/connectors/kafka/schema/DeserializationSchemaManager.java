package connectors.kafka.schema;

import model.*;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;

public class DeserializationSchemaManager {

  public static <T> KafkaDeserializationSchema<?> getSchema(Class<T> clazz) {

    if (clazz.isAssignableFrom(MiscEvent.class)) {
      return new KafkaDeserializationSchemaWapper(new MiscEventDeserializationSchema(),MiscEvent.class);
    }else if(clazz.isAssignableFrom(SkewGuid.class)){
      return new KafkaDeserializationSchemaWapper(new SkewGuidDeserializationSchema(),SkewGuid.class);
    }else if(clazz.isAssignableFrom(SketchEvent.class)){
      return new KafkaDeserializationSchemaWapper(new SketchEventDeserializationSchema(),SketchEvent.class);
    }else if(clazz.isAssignableFrom(UtpEvent.class)){
      return new KafkaDeserializationSchemaWapper(new UtpEventDeserializationSchema(),UtpEvent.class);
    }else if(clazz.isAssignableFrom(SojEvent.class)){
      return new KafkaDeserializationSchemaWapper(new SojEventDeserializationSchema(),SojEvent.class);
    }else if(clazz.isAssignableFrom(AutoTrackEvent.class)){
      return new KafkaDeserializationSchemaWapper(new AutoTrackEventDeserializationSchema(),AutoTrackEvent.class);
    }else if(clazz.isAssignableFrom(UniSession.class)){
      return new KafkaDeserializationSchemaWapper(new UniSessionDeserializationSchema(),UniSession.class);
    }else {
      throw new IllegalStateException("Cannot find deserialization schema");
    }
  }
}
