package com.ebay.epic.sojourner.flink.connectors.kafka.factory;

import com.ebay.epic.sojourner.common.env.FlinkEnvUtils;
import io.ebay.rheos.kafka.client.StreamConnectorConfig;
import io.ebay.rheos.schema.avro.GenericRecordDomainDataDecoder;
import io.ebay.rheos.schema.avro.RheosEventDeserializer;
import java.util.HashMap;
import java.util.Map;
import com.ebay.epic.sojourner.utils.Property;

public class RheosEventSerdeFactory {

  private static final String RHEOS_SERVICES_URL = FlinkEnvUtils
      .getString(Property.RHEOS_KAFKA_REGISTRY_URL);
  private static RheosEventDeserializer rheosEventHeaderDeserializer;
  private static GenericRecordDomainDataDecoder rheosEventDeserializer;

  public static RheosEventDeserializer getRheosEventHeaderDeserializer() {
    if (rheosEventHeaderDeserializer == null) {
      rheosEventHeaderDeserializer = new RheosEventDeserializer();
    }
    return rheosEventHeaderDeserializer;
  }

  public static GenericRecordDomainDataDecoder getRheosEventDeserializer() {
    if (rheosEventDeserializer == null) {
      Map<String, Object> config = new HashMap<>();
      config.put(StreamConnectorConfig.RHEOS_SERVICES_URLS, RHEOS_SERVICES_URL);
      rheosEventDeserializer = new GenericRecordDomainDataDecoder(config);
    }
    return rheosEventDeserializer;
  }
}
