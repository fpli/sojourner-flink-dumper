package com.ebay.epic.sojourner.flink.parser;

import com.ebay.epic.sojourner.common.model.AkamaiLog;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;

import java.util.HashMap;
import java.util.Map;

public class EwExecutionInfoParser implements FieldParser<GenericRecord, AkamaiLog> {

  @Override
  public void init() throws Exception {

  }

  @Override
  public void parse(GenericRecord record, AkamaiLog akamaiLog) throws Exception {
    Map<String, String> ewExecutionInfoMap = new HashMap<>();
    String rawData = ((Utf8) record.get("ewExecutionInfo")).toString();
    ewExecutionInfoMap.put("ewExecutionInfo", rawData);
    akamaiLog.setEwExecutionInfo(ewExecutionInfoMap);
  }
}
