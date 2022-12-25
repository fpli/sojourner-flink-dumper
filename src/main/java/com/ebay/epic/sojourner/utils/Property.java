package com.ebay.epic.sojourner.utils;

public class Property {

    // --------------------- Filter Name List Property ------------------------------
    public static final String DISABLED_FILTER_NAMES = "disabled.filter.names";

    // ---------------------- LOG Property -----------------------------------------
    public static final String LOG_LEVEL = "log.level";
    public static final String DEFAULT_LOG_LEVEL = "INFO";

    // ---------------------- Track and Monitor -----------------------------------------
    public static final String TASK_TRACK_PERIOD = "task.track.period";

    // ---------------------- Enable com.ebay.sojourner.ubd.common.util.Test
    // -----------------------------------------
    public static final String IS_TEST_ENABLE = "enable.test";

    public static final String LKP_PATH = "lkpPath";

    // --------------------- common config property ------------------------------
    // kafka consumer
    public static final String FLINK_APP_SOURCE_OUT_OF_ORDERLESS_IN_MIN = "flink.app.source.out-of-orderless-in-min";
    public static final String KAFKA_CONSUMER_BOOTSTRAP_SERVERS_BASE = "kafka.consumer.bootstrap-servers";
    public static final String KAFKA_CONSUMER_GROUP_ID = "kafka.consumer.group-id";
    public static final String PARTITION_DISCOVERY_INTERVAL_MS_BASE = "kafka.consumer.partition-discovery-interval-ms";
    public static final String MAX_POLL_RECORDS_BASE = "kafka.consumer.max-poll-records";
    public static final String RECEIVE_BUFFER_BASE = "kafka.consumer.receive-buffer";
    public static final String FETCH_MAX_BYTES_BASE = "kafka.consumer.fetch-max-bytes";
    public static final String FETCH_MAX_WAIT_MS_BASE = "kafka.consumer.fetch-max-wait-ms";
    public static final String MAX_PARTITIONS_FETCH_BYTES_BASE = "kafka.consumer.max-partitions-fetch-bytes";
    public static final String AUTO_RESET_OFFSET_BASE = "kafka.consumer.auto-offset-reset";
    public static final String KAFKA_CONSUMER_TOPIC_BASE = "kafka.consumer.topic";
    public static final String FLINK_APP_SOURCE_FROM_TIMESTAMP = "flink.app.source.from-timestamp";
    // kafka producer
    public static final String BATCH_SIZE = "kafka.producer.batch-size";
    public static final String REQUEST_TIMEOUT_MS = "kafka.producer.request-timeout-ms";
    public static final String DELIVERY_TIMEOUT_MS = "kafka.producer.delivery-timeout-ms";
    public static final String REQUEST_RETRIES = "kafka.producer.retries";
    public static final String LINGER_MS = "kafka.producer.linger-ms";
    public static final String BUFFER_MEMORY = "kafka.producer.buffer-memory";
    public static final String ACKS = "kafka.producer.acks";
    public static final String COMPRESSION_TYPE = "kafka.producer.compression-type";
    public static final String KAFKA_PRODUCER_BOOTSTRAP_SERVERS_BASE = "kafka.producer.bootstrap-servers";
    public static final String PRODUCER_ID = "kafka.producer.producerId";
    public static final String MAX_REQUEST_SIZE = "kafka.producer.max-request-size";

    // rheos
    public static final String RHEOS_KAFKA_REGISTRY_URL = "rheos.registry-url";
    public static final String RHEOS_CLIENT_ID = "rheos.client.id";
    public static final String RHEOS_CLIENT_IAF_SECRET = "rheos.client.iaf.secret";
    public static final String RHEOS_CLIENT_IAF_ENV = "rheos.client.iaf.env";

    // flink - app name
    public static final String FLINK_APP_NAME = "flink.app.name";

    // flink - debug mode
    public static final String DEBUG_MODE = "flink.app.debug-mode";

    // flink metric window size
    public static final String METRIC_WINDOW_SIZE = "flink.app.metric.window-size";

    // flink source
    public static final String FLINK_APP_SOURCE_FROM_TS_BASE="flink.app.source.from-timestamp";
    public static final String FLINK_APP_SOURCE_OFO_BASE="flink.app.source.out-of-orderless-in-min";
    public static final String FLINK_APP_SOURCE_TIM_BASE="flink.app.source.idle-source-timeout-in-min";

    // flink checkpoint
    public static final String CHECKPOINT_DATA_DIR = "flink.app.checkpoint.data-dir";
    public static final String CHECKPOINT_INTERVAL_MS = "flink.app.checkpoint.interval-ms";
    public static final String CHECKPOINT_TIMEOUT_MS = "flink.app.checkpoint.timeout-ms";
    public static final String CHECKPOINT_MIN_PAUSE_BETWEEN_MS = "flink.app.checkpoint.min-pause-between-ms";
    public static final String CHECKPOINT_MAX_CONCURRENT = "flink.app.checkpoint.max-concurrent";
    public static final String TOLERATE_FAILURE_CHECKPOINT_NUMBER = "flink.app.checkpoint.tolerate-failure-number";

    // flink - parallelism
    public static final String DEFAULT_PARALLELISM = "flink.app.parallelism.default";
    public static final String SOURCE_PARALLELISM = "flink.app.parallelism.source";
    public static final String PROCESS_PARALLELISM = "flink.app.parallelism.process";
    public static final String SINK_HDFS_PARALLELISM = "flink.app.parallelism.sink.hdfs";
    // ----- flink - operator name and uid------

    // -----operator name-------

    // -----source-----

    public static final String SOURCE_OPERATOR_NAME_BASE="flink.app.operator-name.source";

    // ----process function----
    public static final String PROCESS_OP_NAME="flink.app.operator-name.process";

    // -----sink-----

    public static final String SINK_OPERATOR_NAME_BASE="flink.app.operator-name.sink";

    // -------uid-----------

    // -----source-----
    public static final String SOURCE_UID_BASE="flink.app.uid.source";

    // ----process----
    public static final String PPROCESS_UID= "flink.app.uid.process";

    // -----sink-----
    public static final String  SINK_UID_BASE="flink.app.uid.sink";

    // -------slotsharinggroup-----------

    // -----source-----
    public static final String SOURCE_SLOT_SHARE_GROUP_BASE="flink.app.slot-sharing-group.source";

    // ----process----
    public static final String PROCESS_SLOT_SHARE_GROUP= "flink.app.slot-sharing-group.process";

    // -----sink-----
    public static final String SINK_SLOT_SHARE_GROUP_BASE="flink.app.slot-sharing-group.sink";

    //Track schemaid for uach
    public static final String TRACKING_SCHEMAID = "tracking.schemaid";
    // ------------------------- batch pipeline common property ---------------------------


    // configuration base name
    public static final String BASE_CONFIG = "application";
    // Yaml Ext
    public static final String YML_EXT = ".yml";
    public static final String YAML_EXT = ".yaml";
    public static final String PROFILE = "profile";

    // --------------------- common config property ------------------------------
    // kafka consumer
    public static final String MAX_POLL_RECORDS = "kafka.consumer.max-poll-records";
    public static final String RECEIVE_BUFFER = "kafka.consumer.receive-buffer";
    public static final String FETCH_MAX_BYTES = "kafka.consumer.fetch-max-bytes";
    public static final String FETCH_MAX_WAIT_MS = "kafka.consumer.fetch-max-wait-ms";
    public static final String MAX_PARTITIONS_FETCH_BYTES = "kafka.consumer.max-partitions-fetch-bytes";
    public static final String AUTO_RESET_OFFSET = "kafka.consumer.auto-offset-reset";

    public static final String KAFKA_CONSUMER_BOOTSTRAP_SERVERS = "kafka.consumer.bootstrap-servers";
    public static final String KAFKA_CONSUMER_TOPIC = "kafka.consumer.topic";

    // ------------------------- batch pipeline common property ---------------------------
    // parallelism config

    public static final String SINK_KAFKA_PARALLELISM = "flink.app.parallelism.sink.kafka";

    // hdfs
    public static final String HDFS_DUMP_PATH = "hdfs.dump.path";
    public static final String HDFS_SAME_DAY_SESSION_DUMP_PATH = "hdfs.dump.path.same-day";
    public static final String HDFS_CROSS_DAY_SESSION_DUMP_PATH = "hdfs.dump.path.cross-day";
    public static final String HDFS_DUMP_CLASS = "hdfs.dump.class-name";
    public static final String HDFS_DUMP_WATERMARK_PATH = "hdfs.dump.watermark-path";

    // hdfs
    public static final String FLINK_APP_SINK_HDFS_WATERMARK_PATH = "flink.app.sink.hdfs.watermark-path";

    // flink - operator name and uid
    public static final String TIMESTAMP_EXTRACT_OPERATOR_NAME = "flink.app.operator-name.extract-watermark";
    public static final String TIMESTAMP_EXTRACT_UID = "flink.app.uid.extract-watermark";
    public static final String SINK_OPERATOR_NAME_WATERMARK = "flink.app.operator-name.sink.watermark";
    public static final String SINK_UID_WATERMARK = "flink.app.uid.sink.watermark";


    // flink - metric name
    public static final String FLINK_APP_METRIC_NAME = "flink.app.metric.watermark-process-progress";
}
