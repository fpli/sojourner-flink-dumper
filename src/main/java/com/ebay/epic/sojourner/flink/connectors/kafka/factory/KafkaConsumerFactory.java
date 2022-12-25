package com.ebay.epic.sojourner.flink.connectors.kafka.factory;

import com.ebay.epic.sojourner.common.env.FlinkEnvUtils;
import com.ebay.epic.sojourner.flink.connectors.kafka.assigner.SojSerializableTimestampAssigner;
import com.ebay.epic.sojourner.flink.connectors.kafka.config.KafkaConsumerConfig;
import com.ebay.epic.sojourner.flink.connectors.kafka.schema.DeserializationSchemaManager;
import com.ebay.epic.sojourner.utils.Property;
import model.MiscEvent;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.RoundRobinAssignor;

import java.time.Duration;
import java.util.Properties;

public class KafkaConsumerFactory {

    private static FlinkKafkaConsumer flinkKafkaConsumer;

    public static <T> FlinkKafkaConsumer<T> getConsumer(KafkaConsumerConfig config, Class<T> tClass) {
        Properties consumerConfig = null;
        if (tClass.isAssignableFrom(MiscEvent.class)) {
            consumerConfig = KafkaConnectorFactory.getKafkaCommonConfig();
        } else {
            consumerConfig = KafkaConnectorFactory.getKafkaCommonConfigPlainText();
        }

        consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getBrokers());
        consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, config.getGroupId());
        consumerConfig.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,
                FlinkEnvUtils.getInteger(Property.MAX_POLL_RECORDS));
        consumerConfig.put(ConsumerConfig.RECEIVE_BUFFER_CONFIG,
                FlinkEnvUtils.getInteger(Property.RECEIVE_BUFFER));
        consumerConfig.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG,
                FlinkEnvUtils.getInteger(Property.FETCH_MAX_BYTES));
        consumerConfig.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG,
                FlinkEnvUtils.getInteger(Property.FETCH_MAX_WAIT_MS));
        consumerConfig.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG,
                FlinkEnvUtils.getInteger(Property.MAX_PARTITIONS_FETCH_BYTES));
        consumerConfig.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG,
                RoundRobinAssignor.class.getName());
        consumerConfig.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                FlinkEnvUtils.getString(Property.AUTO_RESET_OFFSET));

        flinkKafkaConsumer = new FlinkKafkaConsumer<T>(
                config.getTopics(),
                DeserializationSchemaManager.getSchema(tClass),
                consumerConfig);
        flinkKafkaConsumer.assignTimestampsAndWatermarks(
                WatermarkStrategy
                        .forBoundedOutOfOrderness(Duration.ofMinutes(0))
                        .withTimestampAssigner(new SojSerializableTimestampAssigner())
                        .withIdleness(Duration.ofMinutes(10)));
        flinkKafkaConsumer.setStartFromLatest();
        return flinkKafkaConsumer;
    }
}
