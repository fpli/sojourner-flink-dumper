package com.ebay.epic.sojourner.flink.parser;

import com.ebay.epic.sojourner.common.model.AkamaiLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;

import java.util.HashMap;
import java.util.Map;


@Slf4j
public class EwUsageInfoParser implements FieldParser<GenericRecord, AkamaiLog> {

  private static Map<Integer, String> ewEventPrefixLookup = new HashMap<>();

  @Override
  public void init() throws Exception {
    ewEventPrefixLookup.put(1, "ewOnClientRequest");
    ewEventPrefixLookup.put(2, "ewOnOriginRequest");
    ewEventPrefixLookup.put(3, "ewOnOriginResponse");
    ewEventPrefixLookup.put(4, "ewOnClientResponse");
    ewEventPrefixLookup.put(5, "ewResponseProvider");
    ewEventPrefixLookup.put(-1, "ewNoEvent");
  }

  @Override
  public void parse(GenericRecord record, AkamaiLog akamaiLog) throws Exception {
    Map<String, String> ewUsageInfo = new HashMap<>();
    String rawData = ((Utf8) record.get("ewUsageInfo")).toString();
    log.trace("previous ewUsageInfo:" + rawData);

    String[] parts = rawData.split("//");

    if (parts != null && parts.length > 1) {
      for (int i = 1; i < parts.length; i++) {
        String[] part = parts[i].split("/");
        if (part.length < 5) {
          log.warn("current ewUsageInfo {} is invalid!", rawData);
        } else {
          ewUsageInfo.put("ewId", part[0]);
          ewUsageInfo.put("ewVersion", part[1]);
          if (part.length < 7) {
            if(part[2] != null && part[2].equals("-")) {
              String eventPrefix = ewEventPrefixLookup.get(-1);
              ewUsageInfo.put(eventPrefix + "OffReason", part[3]);
              ewUsageInfo.put(eventPrefix + "LogicExecuted", part[4]);
              String status = "";
              if(part.length == 6) status = part[5];
              ewUsageInfo.put(eventPrefix + "Status", status);
            }
          } else{
            if (part[2] != null && ewEventPrefixLookup.containsKey(Integer.parseInt(part[2]))) {
              String eventPrefix = ewEventPrefixLookup.get(Integer.parseInt(part[2]));
              ewUsageInfo.put(eventPrefix + "OffReason", part[3]);
              ewUsageInfo.put(eventPrefix + "LogicExecuted", part[4]);
              ewUsageInfo.put(eventPrefix + "Status", part[5]);
            }
          }
        }
      }
    }

    log.trace("new ewUsageInfo:" + ewUsageInfo);
    akamaiLog.setEwUsageInfo(ewUsageInfo);
  }
}
