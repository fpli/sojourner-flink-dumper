package com.ebay.epic.sojourner.flink.connectors.kafka.schema;

import com.ebay.epic.sojourner.common.model.AkamaiLog;
import com.ebay.epic.sojourner.flink.parser.AkamaiLogParser;
import io.ebay.rheos.kafka.client.StreamConnectorConfig;
import io.ebay.rheos.schema.avro.GenericRecordDomainDataDecoder;
import io.ebay.rheos.schema.avro.RheosEventDeserializer;
import io.ebay.rheos.schema.event.RheosEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AkamaiLogDeserializationSchema implements DeserializationSchema<AkamaiLog> {

  private String schemaRegistryUrl = null;
  private static AkamaiLogParser parser;
  private static RheosEventDeserializer rheosEventHeaderDeserializer;
  private static GenericRecordDomainDataDecoder rheosEventDeserializer;

  public AkamaiLogDeserializationSchema(String schemaRegistryUrl) {
    this.schemaRegistryUrl = schemaRegistryUrl;
  }

  @SneakyThrows
  @Override
  public AkamaiLog deserialize(byte[] message) throws IOException {
    log.trace("current message size: " + message.length);

    if (rheosEventHeaderDeserializer == null) {
      rheosEventHeaderDeserializer = new RheosEventDeserializer();
    }
    log.trace(rheosEventHeaderDeserializer == null ? "rheosEventHeaderDeserializer is null"
        : "rheosEventHeaderDeserializer is not null");

    RheosEvent rheosEvent = rheosEventHeaderDeserializer.deserialize(null, message);
    GenericRecord rawAkamaiLog = null;


    if (rheosEventDeserializer == null) {
      Map<String, Object> config = new HashMap<>();
      if (schemaRegistryUrl != null) {
        config.put(StreamConnectorConfig.RHEOS_SERVICES_URLS, schemaRegistryUrl);
      } else {
        config.put(StreamConnectorConfig.RHEOS_SERVICES_URLS, schemaRegistryUrl);
      }
      rheosEventDeserializer = new GenericRecordDomainDataDecoder(config);
    }

    try {
      rawAkamaiLog = rheosEventDeserializer.decode(rheosEvent);
    } catch (Exception e) {
      log.error("Error when deserializing AkamaiLog, schemaId: {}, timestamp: {}",
          rheosEvent.getSchemaId(), rheosEvent.getEventCreateTimestamp(), e);
      return null;
    }

    log.trace("Before parse, AkamaiLog:" + rawAkamaiLog.toString());

    AkamaiLog akamaiLog = new AkamaiLog();
    if (parser == null) {
      parser = new AkamaiLogParser();
    }
    try {
      parser.parse(rawAkamaiLog, akamaiLog);
    } catch (NumberFormatException ne) {
      log.error("Number format parsing error: {}", rawAkamaiLog, ne);
      return null;
    }

    log.trace("After parse, AkamaiLog: " + akamaiLog.toString());

    return akamaiLog;
  }

  @Override
  public boolean isEndOfStream(AkamaiLog nextElement) {
    return false;
  }

  @Override
  public TypeInformation<AkamaiLog> getProducedType() {
    return TypeInformation.of(AkamaiLog.class);
  }

}
