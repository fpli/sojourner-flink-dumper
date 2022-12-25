package com.ebay.epic.sojourner.flink.connectors.kafka.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlinkKafkaSourceConfigWrapper {
  private KafkaConsumerConfig kafkaConsumerConfig;
  private int outOfOrderlessInMin;
  private int idleSourceTimeout;
  private String fromTimestamp;
}
