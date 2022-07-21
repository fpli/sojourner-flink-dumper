package env;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import state.StateBackendFactory;
import utils.Property;

@Slf4j
public class FlinkEnvUtils {

  private static Map<String, String> config = Maps.newHashMap();

  private static void load(String[] args) {
    ParameterTool parameterTool = ParameterTool.fromArgs(args);
    String profile = parameterTool.get("profile");
    if (StringUtils.isNotBlank(profile)) {
      config.put("profile", profile);
      EnvironmentUtils.activateProfile(profile);
    }
    EnvironmentUtils.fromProperties(parameterTool.getProperties());
    // load git.properties file if exists
    try (InputStream input = FlinkEnvUtils.class.getClassLoader()
            .getResourceAsStream("git.properties")) {
      Properties prop = new Properties();

      if (input == null) {
        log.info("Not found git.properties file");
        return;
      }

      //load git.properties and put props into CONFIG map
      prop.load(input);
      for (String key : prop.stringPropertyNames()) {
        config.put(key, prop.getProperty(key));
      }
    } catch (IOException e) {
      log.error("Error when loading git.properties file", e);
    }
  }

  public static StreamExecutionEnvironment prepare(String[] args) throws Exception {

    FlinkEnvUtils.load(args);
    final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.getConfig().disableSysoutLogging();
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

    /*
     * checkpoint
     */
    env.enableCheckpointing(FlinkEnvUtils
        .getInteger(Property.CHECKPOINT_INTERVAL_MS)); // create a checkpoint every 5 minutes
    CheckpointConfig checkpointConf = env.getCheckpointConfig();
    checkpointConf.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);

    checkpointConf.setMinPauseBetweenCheckpoints(
        FlinkEnvUtils.getInteger(Property.CHECKPOINT_MIN_PAUSE_BETWEEN_MS));//2min

    checkpointConf.setCheckpointTimeout(
        FlinkEnvUtils.getInteger(Property.CHECKPOINT_TIMEOUT_MS));//15min

    checkpointConf.setMaxConcurrentCheckpoints(
        FlinkEnvUtils.getInteger(Property.CHECKPOINT_MAX_CONCURRENT));

    checkpointConf.setTolerableCheckpointFailureNumber(
        FlinkEnvUtils.getInteger(Property.TOLERATE_FAILURE_CHECKPOINT_NUMBER));

    /*
     * StateBackend
     */
    env.setStateBackend(StateBackendFactory.getStateBackend(StateBackendFactory.ROCKSDB));

    return env;
  }

  public static void execute(StreamExecutionEnvironment env, String jobName) throws Exception {

    ParameterTool parameterTool = ParameterTool.fromMap(config);
    // make parameters available in the web interface
    env.getConfig().setGlobalJobParameters(parameterTool);
    env.execute(jobName);
  }

  public static String getString(String key) {

    String value = EnvironmentUtils.get(key);
    config.put(key, value);
    return value;
  }

  public static Integer getInteger(String key) {
    String value = EnvironmentUtils.get(key);
    config.put(key, value);
    return Integer.valueOf(value);
  }

  public static Boolean getBoolean(String key) {
    String value = EnvironmentUtils.get(key);
    config.put(key, value);
    return Boolean.getBoolean(value);
  }

  public static String getListString(String key) {

    List<String> list = EnvironmentUtils.get(key, List.class);
    String value = String.join(",", list);
    config.put(key, value);
    return value;
  }

  public static Set<String> getSet(String key) {

    List<String> list = EnvironmentUtils.get(key, List.class);
    String value = String.join(",", list);
    config.put(key, value);
    return new HashSet<>(list);
  }
}
