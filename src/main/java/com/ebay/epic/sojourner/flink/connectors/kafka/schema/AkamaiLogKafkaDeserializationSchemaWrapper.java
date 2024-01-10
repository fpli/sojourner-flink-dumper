package com.ebay.epic.sojourner.flink.connectors.kafka.schema;

import com.ebay.epic.sojourner.common.model.AkamaiLog;
import org.apache.flink.api.common.serialization.DeserializationSchema.InitializationContext;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class AkamaiLogKafkaDeserializationSchemaWrapper implements
        KafkaDeserializationSchema<AkamaiLog> {


  private AkamaiLogDeserializationSchema akamaiDeserializationSchema;

  public AkamaiLogKafkaDeserializationSchemaWrapper(
      AkamaiLogDeserializationSchema akamaiDeserializationSchema) {
    this.akamaiDeserializationSchema = akamaiDeserializationSchema;
  }

  @Override
  public void open(InitializationContext context) throws Exception {
    akamaiDeserializationSchema.open(context);
  }

  @Override
  public boolean isEndOfStream(AkamaiLog nextElement) {
    return false;
  }

  @Override
  public AkamaiLog deserialize(ConsumerRecord<byte[], byte[]> record) throws Exception {

    AkamaiLog akamaiEvent = akamaiDeserializationSchema.deserialize(record.value());
    return akamaiEvent;
  }

  @Override
  public TypeInformation<AkamaiLog> getProducedType() {
    return TypeInformation.of(AkamaiLog.class);
  }
}
