package com.ebay.epic.sojourner.flink.parser;

import com.ebay.epic.sojourner.common.model.AkamaiLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class EwExecutionInfoParser implements FieldParser<GenericRecord, AkamaiLog> {

  @Override
  public void init() throws Exception {

  }

  @Override
  public void parse(GenericRecord record, AkamaiLog akamaiLog) throws Exception {
    if (record.get("ewExecutionInfo") == null) {
      log.warn(record.toString());
      return;
    }

    Map<String, String> ewExecutionInfoMap = new HashMap<>();
    String rawData = ((Utf8) record.get("ewExecutionInfo")).toString();
    ewExecutionInfoMap.put("ewExecutionInfo", rawData);
    akamaiLog.setEwExecutionInfo(ewExecutionInfoMap);
  }
}
