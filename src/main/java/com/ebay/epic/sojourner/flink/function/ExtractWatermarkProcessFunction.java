package com.ebay.epic.sojourner.flink.function;

import com.ebay.epic.sojourner.common.constant.OutputTagConstants;
import com.ebay.epic.sojourner.common.model.SojWatermark;
import com.ebay.epic.sojourner.common.model.UniSession;
import com.ebay.epic.sojourner.utils.SojTimestamp;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ExtractWatermarkProcessFunction<T> extends ProcessFunction<T, SojWatermark> {

    private AtomicInteger atomicInteger;
    private transient Long watermarkDelayTime;
    private String metricName;
    private int subtaskIndex;
    private static final String DATE_FORMAT = "yyyyMMdd";
    private static final String DEFAULT_DATE = "19700101";
    private DateTimeFormatter dateTimeFormatter;

    public ExtractWatermarkProcessFunction(String metricName) {
        this.metricName = metricName;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        atomicInteger = new AtomicInteger(0);
        getRuntimeContext().getMetricGroup()
                .gauge(metricName, () -> watermarkDelayTime);
        subtaskIndex = getRuntimeContext().getIndexOfThisSubtask();
        dateTimeFormatter = dateTimeFormatter.ofPattern(DATE_FORMAT).withZone(ZoneId.systemDefault());
    }

    @Override
    public void processElement(T value, Context ctx, Collector<SojWatermark> out)
            throws Exception {
        watermarkDelayTime = System.currentTimeMillis() - ctx.timestamp();
        int andIncrement = atomicInteger.getAndIncrement();
        if (andIncrement % 1000 == 0) {
            out.collect(new SojWatermark(ctx.timestamp(), subtaskIndex));
        }
        if (value instanceof UniSession) {
            UniSession uniSession = (UniSession) value;
            Long sessionEndTimestamp = System.currentTimeMillis();
            Long sessionStartDt = System.currentTimeMillis();

            try {
                sessionEndTimestamp = SojTimestamp
                        .getSojTimestampToUnixTimestamp(uniSession.getAbsEndTimestamp());
                sessionStartDt = SojTimestamp
                        .getSojTimestampToUnixTimestamp(uniSession.getSessionStartDt());
            } catch (Exception e) {
                log.warn("session end time is null: " + sessionEndTimestamp);
                log.warn("session start time is null: " + sessionStartDt);
            }

            String sessionEndTimeString = transferLongToDateString(sessionEndTimestamp);
            String sessionStartTimeString = transferLongToDateString(sessionStartDt);

            if (uniSession.getIsOpen()) {
                ctx.output(OutputTagConstants.openSessionOutputTag, uniSession);
            } else if (sessionStartTimeString.equals(sessionEndTimeString)) {
                ctx.output(OutputTagConstants.sameDaySessionOutputTag, uniSession);
            } else {
                ctx.output(OutputTagConstants.crossDaySessionOutputTag, uniSession);
            }
        }
    }

    private String transferLongToDateString(Long time) {

        if (time > 0) {
            String defaultTsStr = dateTimeFormatter.format(Instant.ofEpochMilli(time));
            return defaultTsStr.substring(0, 8);
        } else {
            return DEFAULT_DATE;
        }
    }
}
