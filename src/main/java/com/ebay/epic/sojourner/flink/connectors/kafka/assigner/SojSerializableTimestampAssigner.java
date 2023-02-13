package com.ebay.epic.sojourner.flink.connectors.kafka.assigner;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import com.ebay.epic.sojourner.utils.TimestampFieldExtractor;

@Slf4j
public class SojSerializableTimestampAssigner<T> implements SerializableTimestampAssigner<T> {

    @Override
    public long extractTimestamp(T element, long recordTimestamp) {
        long field = System.currentTimeMillis();
        try {
            field = TimestampFieldExtractor.getField(element); //TimestampFieldExtractor.getField(element);
        } catch (Exception e) {
            log.warn("extract timestamp failed" + field);
        }

        return field;
    }
}
