package connectors.kafka.schema;

import com.google.common.base.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;

import java.lang.reflect.Field;
import java.util.Iterator;

@Slf4j
public class KafkaDeserializationSchemaWapper<T> implements KafkaDeserializationSchema<T> {

    private DeserializationSchema<T> deserializationSchema;
    public static final String FIELD_NAME="globalSessionId";
    private Class<T> clazz;
    public KafkaDeserializationSchemaWapper(DeserializationSchema deserializationSchema,Class<T> tClass){
        this.deserializationSchema=deserializationSchema;
        this.clazz=tClass;
    }
    @Override
    public boolean isEndOfStream(Object nextElement) {
        return false;
    }

    @Override
    public T deserialize(ConsumerRecord<byte[], byte[]> record) throws Exception {
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
        Field field=null;
        T element = deserializationSchema.deserialize(record.value());
        try {
            field = element.getClass().getDeclaredField(FIELD_NAME);
            field.setAccessible(true);
            field.set(element,globalSessionId);
        } catch (Exception e) {
            log.error("dumper uni-sess Get field[{}] value error", FIELD_NAME, e);
        }

        return element;
    }

    @Override
    public TypeInformation getProducedType() {
        return TypeInformation.of(this.clazz);
    }
}
