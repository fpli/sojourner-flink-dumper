package com.ebay.epic.sojourner.flink.connectors.kafka.builder;

import com.ebay.epic.sojourner.common.env.FlinkEnvUtils;
import com.ebay.epic.sojourner.flink.connectors.kafka.assigner.SojSerializableTimestampAssigner;
import com.ebay.epic.sojourner.flink.connectors.kafka.config.KafkaConsumerConfig;
import com.ebay.epic.sojourner.flink.connectors.kafka.factory.KafkaConnectorFactory;
import com.ebay.epic.sojourner.flink.connectors.kafka.factory.KafkaConsumerFactory;
import com.ebay.epic.sojourner.utils.DataCenter;
import com.ebay.epic.sojourner.utils.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.time.Duration;

@Data
@AllArgsConstructor
public class SourceDataStreamBuilderLegacy<T> {

  private StreamExecutionEnvironment environment;
  private Class<T> tClass;

  public DataStream<T> buildOfDC(DataCenter dc) {
    return this.buildOfDC(dc, null);
  }

  public DataStream<T> buildOfDC(DataCenter dc, String slotGroup) {

    KafkaConsumerConfig kafkaConsumerConfig = KafkaConnectorFactory.getKafkaConsumerConfig(dc);
    return environment
        .addSource(KafkaConsumerFactory.getConsumer(kafkaConsumerConfig, tClass))
        .setParallelism(FlinkEnvUtils.getInteger(Property.SOURCE_PARALLELISM))
            .assignTimestampsAndWatermarks(
                    WatermarkStrategy
                            .<T>forBoundedOutOfOrderness(Duration.ofMinutes(
                                    FlinkEnvUtils.getInteger(Property.FLINK_APP_SOURCE_OUT_OF_ORDERLESS_IN_MIN)))
                            .withTimestampAssigner(new SojSerializableTimestampAssigner<>()))
        .slotSharingGroup(slotGroup)
        .name(String.format("Rheos Kafka Consumer From DC: %s, Topic: %s",
            dc, kafkaConsumerConfig.getTopics()))
        .uid(String.format("source-%s-%s-id", dc, kafkaConsumerConfig.getTopics()));
  }
}
