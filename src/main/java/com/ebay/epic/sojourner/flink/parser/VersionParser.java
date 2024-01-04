package com.ebay.epic.sojourner.flink.parser;

import com.ebay.epic.sojourner.common.model.AkamaiLog;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;

public class VersionParser implements FieldParser<GenericRecord, AkamaiLog> {

  @Override
  public void init() throws Exception {

  }

  @Override
  public void parse(GenericRecord record, AkamaiLog akamaiLog) throws Exception {

    Integer result = null;
    String version = ((Utf8) record.get("version")).toString();
    if (!"-".equals(version)) {
      result = Integer.parseInt(version);
    }
    akamaiLog.setVersion(result);
  }
}
