package connectors.kafka;

import connectors.kafka.config.KafkaConsumerConfig;
import connectors.kafka.factory.KafkaConnectorFactory;
import connectors.kafka.factory.KafkaConsumerFactory;
import env.FlinkEnvUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import utils.DataCenter;
import utils.Property;

@Data
@AllArgsConstructor
public class SourceDataStreamBuilder<T> {

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
        .slotSharingGroup(slotGroup)
        .name(String.format("Rheos Kafka Consumer From DC: %s, Topic: %s",
            dc, kafkaConsumerConfig.getTopic()))
        .uid(String.format("source-%s-%s-id", dc, kafkaConsumerConfig.getTopic()));
  }
}
