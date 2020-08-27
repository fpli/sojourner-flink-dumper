package utils;

public class Property {

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
  public static final String KAFKA_CONSUMER_GROUP_ID = "kafka.consumer.group-id";

  // kafka producer
  public static final String BATCH_SIZE = "kafka.producer.batch-size";
  public static final String REQUEST_TIMEOUT_MS = "kafka.producer.request-timeout-ms";
  public static final String REQUEST_RETRIES = "kafka.producer.retries";
  public static final String LINGER_MS = "kafka.producer.linger-ms";
  public static final String BUFFER_MEMORY = "kafka.producer.buffer-memory";
  public static final String ACKS = "kafka.producer.acks";
  public static final String COMPRESSION_TYPE = "kafka.producer.compression-type";
  public static final String BEHAVIOR_MESSAGE_KEY_SESSION = "kafka.producer.message-key.session";
  public static final String BEHAVIOR_MESSAGE_KEY_EVENT_DEFAULT = "kafka.producer.message-key.event.default";
  public static final String BEHAVIOR_MESSAGE_KEY_EVENT_KEY1 = "kafka.producer.message-key.event.key1";
  public static final String BEHAVIOR_MESSAGE_KEY_EVENT_KEY2 = "kafka.producer.message-key.event.key2";
  public static final String BEHAVIOR_MESSAGE_KEY_SIGNATURE_IP = "kafka.producer.message-key.signature.ip";
  public static final String BEHAVIOR_MESSAGE_KEY_SIGNATURE_GUID = "kafka.producer.message-key.signature.guid";
  public static final String BEHAVIOR_MESSAGE_KEY_SIGNATURE_AGENT = "kafka.producer.message-key.signature.agent";
  public static final String BEHAVIOR_MESSAGE_KEY_SIGNATURE_AGENT_IP = "kafka.producer.message-key.signature.agent-ip";
  public static final String KAFKA_PRODUCER_BOOTSTRAP_SERVERS_RNO = "kafka.producer.bootstrap-servers.rno";
  public static final String KAFKA_PRODUCER_BOOTSTRAP_SERVERS_LVS = "kafka.producer.bootstrap-servers.lvs";
  public static final String KAFKA_PRODUCER_TOPIC = "kafka.producer.topic";

  // rheos
  public static final String RHEOS_KAFKA_REGISTRY_URL = "rheos.registry-url";
  public static final String RHEOS_CLIENT_ID = "rheos.client.id";
  public static final String RHEOS_CLIENT_IAF_SECRET = "rheos.client.iaf.secret";
  public static final String RHEOS_CLIENT_IAF_ENV = "rheos.client.iaf.env";
  public static final String RHEOS_SCHEMA_NAME_EVENT = "rheos.schema-name.event";
  public static final String RHEOS_SCHEMA_NAME_SESSION = "rheos.schema-name.session";
  public static final String RHEOS_SCHEMA_NAME_ATTRIBUTE = "rheos.schema-name.attribute";

  // flink checkpoint
  public static final String CHECKPOINT_DATA_DIR = "flink.app.checkpoint.data-dir";
  public static final String CHECKPOINT_INTERVAL_MS = "flink.app.checkpoint.interval-ms";
  public static final String CHECKPOINT_TIMEOUT_MS = "flink.app.checkpoint.timeout-ms";
  public static final String CHECKPOINT_MIN_PAUSE_BETWEEN_MS = "flink.app.checkpoint.min-pause-between-ms";
  public static final String CHECKPOINT_MAX_CONCURRENT = "flink.app.checkpoint.max-concurrent";
  public static final String TOLERATE_FAILURE_CHECKPOINT_NUMBER = "flink.app.checkpoint.tolerate-failure-number";

  // flink - parallelism and slot share group
  public static final String DEFAULT_PARALLELISM = "flink.app.parallelism.default";
  public static final String SOURCE_PARALLELISM = "flink.app.parallelism.source";
  public static final String EVENT_PARALLELISM = "flink.app.parallelism.event";
  public static final String SESSION_PARALLELISM = "flink.app.parallelism.session";
  public static final String PRE_AGENT_IP_PARALLELISM = "flink.app.parallelism.pre-agent-ip";
  public static final String AGENT_IP_PARALLELISM = "flink.app.parallelism.agent-ip";
  public static final String AGENT_PARALLELISM = "flink.app.parallelism.agent";
  public static final String IP_PARALLELISM = "flink.app.parallelism.ip";
  public static final String GUID_PARALLELISM = "flink.app.parallelism.guid";
  public static final String BROADCAST_PARALLELISM = "flink.app.parallelism.broadcast";
  public static final String METRICS_PARALLELISM = "flink.app.parallelism.metrics";

  public static final String DEFAULT_SLOT_SHARE_GROUP = "flink.app.slot-sharing-group.default";
  public static final String SOURCE_EVENT_SLOT_SHARE_GROUP = "flink.app.slot-sharing-group.source-event";
  public static final String SOURCE_EVENT_LVS_SLOT_SHARE_GROUP = "flink.app.slot-sharing-group.source-event-lvs";
  public static final String SOURCE_EVENT_SLC_SLOT_SHARE_GROUP = "flink.app.slot-sharing-group.source-event-slc";
  public static final String SOURCE_EVENT_RNO_SLOT_SHARE_GROUP = "flink.app.slot-sharing-group.source-event-rno";
  public static final String SESSION_SLOT_SHARE_GROUP = "flink.app.slot-sharing-group.session";
  public static final String CROSS_SESSION_SLOT_SHARE_GROUP = "flink.app.slot-sharing-group.cross-session";
  public static final String BROADCAST_SLOT_SHARE_GROUP = "flink.app.slot-sharing-group.broadcast";
  public static final String PRE_AGENT_IP_SLOT_SHARE_GROUP = "flink.app.slot-sharing-group.pre-agent-ip";
  public static final String AGENT_IP_SLOT_SHARE_GROUP = "flink.app.slot-sharing-group.agent-ip";
  public static final String AGENT_SLOT_SHARE_GROUP = "flink.app.slot-sharing-group.agent";
  public static final String IP_SLOT_SHARE_GROUP = "flink.app.slot-sharing-group.ip";
  public static final String GUID_SLOT_SHARE_GROUP = "flink.app.slot-sharing-group.guid";

  // flink - app name
  public static final String FLINK_APP_NAME = "flink.app.name";

  // ------------------------- batch pipeline common property ---------------------------
  // parallelism config
  public static final String SINK_HDFS_PARALLELISM = "flink.app.parallelism.sink.hdfs";
  public static final String SINK_KAFKA_PARALLELISM = "flink.app.parallelism.sink.kafka";

  // hdfs
  public static final String HDFS_DUMP_PATH = "hdfs.dump.path";
  public static final String HDFS_SAME_DAY_SESSION_DUMP_PATH = "hdfs.dump.path.same-day";
  public static final String HDFS_CROSS_DAY_SESSION_DUMP_PATH = "hdfs.dump.path.cross-day";
  public static final String HDFS_DUMP_CLASS = "hdfs.dump.class-name";
  public static final String HDFS_DUMP_WATERMARK_PATH = "hdfs.dump.watermark-path";

  // ------------------------- rt pipeline property ------------------------
  // kafka producer
  public static final String KAFKA_TOPIC_SESSION_BOT = "kafka.producer.topic.session.bot";
  public static final String KAFKA_TOPIC_SESSION_NON_BOT = "kafka.producer.topic.session.non-bot";
  public static final String KAFKA_TOPIC_INTERMEDIATE_SESSION = "kafka.producer.topic.intermediate-session";
  public static final String KAFKA_TOPIC_EVENT_BOT = "kafka.producer.topic.event.bot";
  public static final String KAFKA_TOPIC_EVENT_NON_BOT = "kafka.producer.topic.event.non-bot";
  public static final String KAFKA_TOPIC_SIGNATURE_AGENT_IP = "kafka.producer.topic.signature.agent-ip";
  public static final String KAFKA_TOPIC_SIGNATURE_AGENT = "kafka.producer.topic.signature.agent";
  public static final String KAFKA_TOPIC_SIGNATURE_IP = "kafka.producer.topic.signature.ip";
  public static final String KAFKA_TOPIC_SIGNATURE_GUID = "kafka.producer.topic.signature.guid";

  // hdfs
  public static final String HDFS_PATH_PARENT = "hdfs.path.parent";
  public static final String HDFS_PATH_EVENT_NON_BOT = "hdfs.path.event.non-bot";
  public static final String HDFS_PATH_SESSION_NON_BOT = "hdfs.path.session.non-bot";
  public static final String HDFS_PATH_INTERMEDIATE_SESSION = "hdfs.path.intermediate-session";
  public static final String HDFS_PATH_SIGNATURES = "hdfs.path.signatures";

}
