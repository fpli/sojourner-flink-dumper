package com.ebay.epic.sojourner.flink.connectors.kafka.builder;

import com.ebay.epic.sojourner.common.constant.EventType;
import com.ebay.epic.sojourner.common.constant.SessionType;
import com.ebay.epic.sojourner.flink.connectors.hdfs.DateTimeBucketAssignerForEventTime;
import com.ebay.epic.sojourner.flink.connectors.hdfs.HdfsConnectorFactory;
import com.ebay.epic.sojourner.flink.connectors.kafka.config.ConfigManager;
import com.ebay.epic.sojourner.utils.DataCenter;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.BasePathBucketAssigner;

import static com.ebay.epic.sojourner.common.env.FlinkEnvUtils.getInteger;
import static com.ebay.epic.sojourner.common.env.FlinkEnvUtils.getString;
import static com.ebay.epic.sojourner.utils.Property.DEFAULT_PARALLELISM;
import static com.ebay.epic.sojourner.utils.Property.MAX_PARALLELISM;

public class FlinkKafkaSinkBuilder<T> {

    private final DataStream<T> dataStream;
    private String operatorName;
    private String uid;
    private String sinkSlotGroup;
    private int parallelism = getInteger(DEFAULT_PARALLELISM);
    private int maxParallism = getInteger(MAX_PARALLELISM);
    private Class<T> className;
    private ConfigManager configManager;
    private String hdfsPath;
    private SessionType sessionType;
    private EventType eventType;

    public FlinkKafkaSinkBuilder(DataStream<T> dataStream, SessionType sessionType) {
        this.dataStream = dataStream;
        this.sessionType = sessionType;
        this.configManager = new ConfigManager();
    }

    public FlinkKafkaSinkBuilder(DataStream<T> dataStream, EventType eventType) {
        this.dataStream = dataStream;
        this.eventType = eventType;
        this.configManager = new ConfigManager();
    }

    public FlinkKafkaSinkBuilder<T> operatorName(String operatorName) {
        this.operatorName = String.join(configManager.DEL_SPACE,
                configManager.getOPName(operatorName), getName());
        return this;
    }

    public FlinkKafkaSinkBuilder<T> parallelism(String parallelism) {
        this.parallelism = configManager.getParallelism(
                String.join(configManager.DEL_POINT,parallelism,getName()));
        return this;
    }

    public FlinkKafkaSinkBuilder<T> uid(String uid) {
        this.uid = String.join(configManager.DEL_LINE, configManager.getOPUid(uid), getName());
        return this;
    }

    public FlinkKafkaSinkBuilder<T> path(String path) {
        this.hdfsPath = configManager.getPath(path,getName());
        return this;
    }

    public FlinkKafkaSinkBuilder<T> slotGroup(String slotGroup) {
        this.sinkSlotGroup = configManager.getSlotSharingGroup(slotGroup);
        return this;
    }

    public FlinkKafkaSinkBuilder<T> className(Class<T> tClass) {
        this.className = tClass;
        return this;
    }

    public FlinkKafkaSinkBuilder<T> maxParallism(String maxParallism) {
        this.maxParallism = configManager.getParallelism(maxParallism);
        return this;
    }

    public void build() {
        dataStream
                .addSink(HdfsConnectorFactory.createWithParquet(hdfsPath,
                        dataStream.getType().getTypeClass(),
                        new DateTimeBucketAssignerForEventTime<>()))
                .setParallelism(parallelism)
                .slotSharingGroup(sinkSlotGroup)
                .name(operatorName)
                .uid(uid)
                .getTransformation()
                .setMaxParallelism(maxParallism);;
    }

    private String getName(){
        if(eventType!=null){
            return eventType.name().toLowerCase();
        }else{
            return sessionType.name().toLowerCase();
        }
    }

}
