package com.ebay.epic.sojourner.flink.parser;

import com.ebay.epic.sojourner.common.model.AkamaiLog;
import org.apache.avro.generic.GenericRecord;

public class AkamaiLogParser extends RecordParser<GenericRecord, AkamaiLog> {


  public AkamaiLogParser() throws Exception {
    initFieldParsers();
    init();
  }


  @Override
  public void initFieldParsers() {
    if (this.fieldParsers.isEmpty()) {
      addFieldParser(new VersionParser());
      addFieldParser(new EwUsageInfoParser());
      addFieldParser(new EwExecutionInfoParser());
      addFieldParser(new CommonFieldsParser());
    }
  }
}
