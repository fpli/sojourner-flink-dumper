package com.ebay.epic.sojourner.flink.connectors.kafka.builder;

import com.ebay.epic.sojourner.flink.connectors.kafka.config.ConfigManager;
import com.ebay.epic.sojourner.flink.connectors.kafka.config.FlinkKafkaSourceConfigWrapper;
import com.ebay.epic.sojourner.flink.connectors.kafka.config.KafkaConsumerConfig;
import com.ebay.epic.sojourner.flink.connectors.kafka.factory.FlinkKafkaConsumerFactory;
import com.ebay.epic.sojourner.utils.DataCenter;
import com.ebay.epic.sojourner.utils.Property;
import com.google.common.base.Preconditions;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;

import static com.ebay.epic.sojourner.common.constant.Constants.DOMAIN_DEL;
import static com.ebay.epic.sojourner.common.env.FlinkEnvUtils.getInteger;

public class SourceDataStreamBuilder<T> {

    private final StreamExecutionEnvironment environment;
    private DataCenter dc;
    private String operatorName;
    private String uid;
    private String slotGroup;
    private int parallelism = getInteger(Property.DEFAULT_PARALLELISM);

    private int maxParallism = getInteger(Property.MAX_PARALLELISM);
    private int outOfOrderlessInMin;
    private String fromTimestamp = "0";
    private int idleSourceTimeout;
    private boolean rescaled;
    private ConfigManager configManager;
    public SourceDataStreamBuilder(StreamExecutionEnvironment environment,DataCenter dc) {
        this.environment = environment;
        this.dc=dc;
        this.configManager= new ConfigManager(dc,true);
    }

    public SourceDataStreamBuilder<T> operatorName(String operatorName) {
        this.operatorName = configManager.getOPName(operatorName);
        return this;
    }

    public SourceDataStreamBuilder<T> parallelism(String parallelism) {
        this.parallelism = configManager.getParallelism(parallelism);
        return this;
    }

    public SourceDataStreamBuilder<T> uid(String uid) {
        this.uid = configManager.getOPUid(uid)+DOMAIN_DEL+ dc;
        return this;
    }

    public SourceDataStreamBuilder<T> slotGroup(String slotGroup) {
        this.slotGroup = configManager.getSlotSharingGroup(slotGroup);
        return this;
    }

    public SourceDataStreamBuilder<T> rescaled(boolean rescaled) {
        this.rescaled = rescaled;
        return this;
    }

    public SourceDataStreamBuilder<T> outOfOrderlessInMin(String outOfOrderlessInMin) {
        this.outOfOrderlessInMin = configManager.getIntValue(outOfOrderlessInMin);
        return this;
    }

    public SourceDataStreamBuilder<T> fromTimestamp(String fromTimestamp) {
        this.fromTimestamp = configManager.getStrDirect(fromTimestamp);
        return this;
    }

    public SourceDataStreamBuilder<T> idleSourceTimeout(String idleSourceTimeout) {
        this.idleSourceTimeout = configManager.getIntValue(idleSourceTimeout);
        return this;
    }
    public SourceDataStreamBuilder<T> maxParallism(String maxParallism) {
        this.maxParallism = configManager.getParallelism(maxParallism);
        return this;
    }
    public DataStream<T> build(KafkaDeserializationSchema<T> schema) {
        Preconditions.checkNotNull(dc);
        return this.build(schema, dc, operatorName, parallelism, uid, slotGroup, rescaled,false);
    }

    public DataStream<T> build(KafkaDeserializationSchema<T> schema, boolean isSSL) {
        Preconditions.checkNotNull(dc);
        return this.build(schema, dc, operatorName, parallelism, uid, slotGroup, rescaled,isSSL);
    }

    public DataStream<T> buildRescaled(KafkaDeserializationSchema<T> schema) {
        Preconditions.checkNotNull(dc);
        return this.build(schema, dc, operatorName, parallelism, uid, slotGroup, true,false);
    }

    public DataStream<T> build(KafkaDeserializationSchema<T> schema, DataCenter dc,
                               String operatorName, int parallelism, String uid, String slotGroup,
                               boolean rescaled,boolean isSSL) {
        Preconditions.checkNotNull(dc);
        KafkaConsumerConfig config = KafkaConsumerConfig.build(dc,isSSL);
        FlinkKafkaSourceConfigWrapper configWrapper = new FlinkKafkaSourceConfigWrapper(
                config, outOfOrderlessInMin, idleSourceTimeout, fromTimestamp);
        FlinkKafkaConsumerFactory factory = new FlinkKafkaConsumerFactory(configWrapper);

        DataStream<T> dataStream = environment
                .addSource(factory.get(schema))
                .setParallelism(parallelism)
                .slotSharingGroup(slotGroup)
                .name(operatorName)
                .setMaxParallelism(maxParallism)
                .uid(uid);

        if (rescaled) {
            return dataStream.rescale();
        }

        return dataStream;
    }
}