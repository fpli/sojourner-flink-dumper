package com.ebay.epic.sojourner.flink.connectors.kafka.factory;

import com.ebay.epic.sojourner.common.env.FlinkEnvUtils;
import com.ebay.epic.sojourner.flink.connectors.kafka.config.KafkaConsumerConfig;
import com.ebay.epic.sojourner.utils.DataCenter;
import com.ebay.epic.sojourner.utils.Property;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;

import java.util.Properties;

import static com.ebay.epic.sojourner.utils.Property.KAFKA_CONSUMER_BOOTSTRAP_SERVERS;

public class KafkaConnectorFactory {

  public static Properties getKafkaCommonConfig() {
    Properties props = new Properties();
    props.put("sasl.mechanism", "IAF");
    // props.put("security.protocol", "SASL_PLAINTEXT");
    props.put("security.protocol", "SASL_SSL");

    final String saslJaasConfig =
        String.format(
            "io.ebay.rheos.kafka.security.iaf.IAFLoginModule required iafConsumerId="
                + "\"urn:ebay-marketplace-consumerid:68a97ac2-013b-4915-9ed7-d6ae2ff01618\" "
                + "iafSecret=\"%s\" iafEnv=\"%s\";",
            FlinkEnvUtils.getString(Property.RHEOS_CLIENT_IAF_SECRET),
            FlinkEnvUtils.getString(Property.RHEOS_CLIENT_IAF_ENV));

    props.put(SaslConfigs.SASL_JAAS_CONFIG, saslJaasConfig);
    props.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, "");
    return props;
  }

  public static Properties getKafkaCommonConfigPlainText() {
    Properties props = new Properties();
    props.put("sasl.mechanism", "IAF");
    props.put("security.protocol", "SASL_PLAINTEXT");
//    props.put("security.protocol", "SASL_SSL");

    final String saslJaasConfig =
            String.format(
                    "io.ebay.rheos.kafka.security.iaf.IAFLoginModule required iafConsumerId="
                            + "\"urn:ebay-marketplace-consumerid:68a97ac2-013b-4915-9ed7-d6ae2ff01618\" "
                            + "iafSecret=\"%s\" iafEnv=\"%s\";",
                    FlinkEnvUtils.getString(Property.RHEOS_CLIENT_IAF_SECRET),
                    FlinkEnvUtils.getString(Property.RHEOS_CLIENT_IAF_ENV));

    props.put(SaslConfigs.SASL_JAAS_CONFIG, saslJaasConfig);
//    props.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, "");
    return props;
  }

  public static KafkaConsumerConfig getKafkaConsumerConfig(DataCenter dataCenter) {
    final String topic = FlinkEnvUtils.getString(Property.KAFKA_CONSUMER_TOPIC);
    final String groupId = FlinkEnvUtils.getString(Property.KAFKA_CONSUMER_GROUP_ID);
    KafkaConsumerConfig kafkaConsumerConfig = KafkaConsumerConfig.build(dataCenter);
    return kafkaConsumerConfig;
  }

  private static String getBrokersForDC(DataCenter dc) {
    String propKey = KAFKA_CONSUMER_BOOTSTRAP_SERVERS + "." + dc.getValue().toLowerCase();
    return FlinkEnvUtils.getListString(propKey);
  }
}
