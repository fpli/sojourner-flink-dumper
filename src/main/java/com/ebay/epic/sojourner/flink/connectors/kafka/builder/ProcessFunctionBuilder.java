package com.ebay.epic.sojourner.flink.connectors.kafka.builder;

import com.ebay.epic.sojourner.flink.connectors.kafka.config.ConfigManager;
import com.ebay.epic.sojourner.flink.connectors.kafka.config.FlinkKafkaSourceConfigWrapper;
import com.ebay.epic.sojourner.flink.connectors.kafka.config.KafkaConsumerConfig;
import com.ebay.epic.sojourner.flink.connectors.kafka.factory.FlinkKafkaConsumerFactory;
import com.ebay.epic.sojourner.utils.DataCenter;
import com.ebay.epic.sojourner.utils.Property;
import com.google.common.base.Preconditions;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;

import static com.ebay.epic.sojourner.common.env.FlinkEnvUtils.getInteger;
import static com.ebay.epic.sojourner.utils.Property.DEFAULT_PARALLELISM;

public class ProcessFunctionBuilder<IN, OUT> {

    private final DataStream<IN> dataStream;
    private DataCenter dc;
    private String operatorName;
    private String uid;
    private String processSlotGroup;
    private int parallelism = getInteger(DEFAULT_PARALLELISM);
    private ConfigManager configManager;
    private ProcessFunction<IN, OUT> processFunction;

    public ProcessFunctionBuilder(DataStream<IN> dataStream, Boolean isDrived) {
        this.dataStream = dataStream;
        this.configManager = new ConfigManager();
        this.configManager.setDrived(isDrived);
    }

    public ProcessFunctionBuilder<IN, OUT> operatorName(String operatorName) {
        this.operatorName = configManager.getOPName(operatorName);
        return this;
    }

    public ProcessFunctionBuilder<IN, OUT> parallelism(String parallelism) {
        this.parallelism = configManager.getParallelism(parallelism);
        return this;
    }

    public ProcessFunctionBuilder<IN, OUT> uid(String uid) {
        this.uid = configManager.getOPUid(uid);
        return this;
    }

    public ProcessFunctionBuilder<IN, OUT> slotGroup(String slotGroup) {
        this.processSlotGroup = configManager.getSlotSharingGroup(slotGroup);
        return this;
    }

    public ProcessFunctionBuilder<IN, OUT> process(ProcessFunction<IN, OUT> processFunction) {
        this.processFunction = processFunction;
        return this;
    }

    public SingleOutputStreamOperator<OUT> build() {
        return dataStream.process(this.processFunction)
                .setParallelism(parallelism)
                .name(operatorName)
                .uid(uid)
                .slotSharingGroup(this.processSlotGroup);
    }
}