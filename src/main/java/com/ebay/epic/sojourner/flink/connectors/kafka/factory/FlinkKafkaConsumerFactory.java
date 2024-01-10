package com.ebay.epic.sojourner.flink.connectors.kafka.factory;

import com.ebay.epic.sojourner.flink.connectors.kafka.assigner.SojSerializableTimestampAssigner;
import com.ebay.epic.sojourner.flink.connectors.kafka.config.FlinkKafkaSourceConfigWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;

import java.time.Duration;

@Slf4j
public class FlinkKafkaConsumerFactory {

    private final FlinkKafkaSourceConfigWrapper configWrapper;

    public FlinkKafkaConsumerFactory(FlinkKafkaSourceConfigWrapper configWrapper) {
        this.configWrapper = configWrapper;
    }

    public <T> FlinkKafkaConsumer<T> get(KafkaDeserializationSchema<T> deserializer) {
        FlinkKafkaConsumer<T> flinkKafkaConsumer = new FlinkKafkaConsumer<>(
                configWrapper.getKafkaConsumerConfig().getTopics(),
                deserializer,
                configWrapper.getKafkaConsumerConfig().getProperties());
        if (configWrapper.getOutOfOrderlessInMin() > 0) {
            log.warn("if init timestampand watermarks:{}", configWrapper.getOutOfOrderlessInMin());
        } else {
            log.warn("else init timestamp and watermarks:{}", configWrapper.getOutOfOrderlessInMin());
        }
        flinkKafkaConsumer.assignTimestampsAndWatermarks(
                WatermarkStrategy
                        .<T>forBoundedOutOfOrderness(Duration.ofMinutes(configWrapper.getOutOfOrderlessInMin()))
                        .withTimestampAssigner(new SojSerializableTimestampAssigner<>())
                        .withIdleness(Duration.ofMinutes(configWrapper.getIdleSourceTimeout())));
        String fromTimestamp = configWrapper.getFromTimestamp();
        if (fromTimestamp.equalsIgnoreCase("earliest")) {
            flinkKafkaConsumer.setStartFromEarliest();
        } else if (Long.parseLong(fromTimestamp) == 0) {
            flinkKafkaConsumer.setStartFromLatest();
        } else if (Long.parseLong(fromTimestamp) > 0) {
            flinkKafkaConsumer.setStartFromTimestamp(Long.parseLong(fromTimestamp));
        } else {
            throw new IllegalArgumentException("Cannot parse fromTimestamp value");
        }

        return flinkKafkaConsumer;
    }
}
