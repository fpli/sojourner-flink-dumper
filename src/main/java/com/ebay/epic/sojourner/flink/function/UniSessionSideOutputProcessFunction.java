package com.ebay.epic.sojourner.flink.function;

import com.ebay.epic.sojourner.common.constant.OutputTagConstants;
import com.ebay.epic.sojourner.common.model.SojWatermark;
import com.ebay.epic.sojourner.common.model.UniSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
public class UniSessionSideOutputProcessFunction<T> extends ExtractWatermarkProcessFunction<T> {

    private static final String DEFAULT_DATE = "19700101";
    private static final String DATE_FORMAT = "yyyyMMdd";
    private DateTimeFormatter dateTimeFormatter;

    public UniSessionSideOutputProcessFunction(String metricName) {
        super(metricName);
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        dateTimeFormatter = dateTimeFormatter.ofPattern(DATE_FORMAT).withZone(ZoneId.systemDefault());
    }

    @Override
    public void processElement(T value, Context ctx, Collector<SojWatermark> out)
            throws Exception {
        super.processElement(value, ctx, out);
        if (value instanceof UniSession) {
            UniSession uniSession = (UniSession) value;
            Long sessionEndTimestamp = System.currentTimeMillis();
            Long sessionStartDt = System.currentTimeMillis();

            try {
                sessionEndTimestamp = uniSession.getAbsEndTimestamp();
                sessionStartDt = uniSession.getSessionStartDt();
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

    @Override
    public void close() throws Exception {
        super.close();
        dateTimeFormatter = null;
    }

}
