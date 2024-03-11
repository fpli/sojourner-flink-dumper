package com.ebay.epic.sojourner.flink.parser;

import com.ebay.epic.sojourner.common.model.AkamaiLog;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;

import java.net.URLDecoder;

public class CommonFieldsParser implements FieldParser<GenericRecord, AkamaiLog> {

  @Override
  public void init() throws Exception {

  }

  @Override
  public void parse(GenericRecord record, AkamaiLog akamaiLog) throws Exception {

    String country = record.get("country") == null ? "" : ((Utf8) record.get("country")).toString();
    akamaiLog.setCountry("-".equals(country) ? null : country);

    String city = record.get("city") == null ? "" : ((Utf8) record.get("city")).toString();
    akamaiLog.setCity("-".equals(city) ? null : city);

    String state = record.get("state") == null ? "" : ((Utf8) record.get("state")).toString();
    akamaiLog.setState("-".equals(state) ? null : state);

    String cacheStatus = record.get("cacheStatus") == null ? "" : ((Utf8) record.get("cacheStatus")).toString();
    akamaiLog.setCacheStatus("-".equals(cacheStatus) ? null : Integer.parseInt(cacheStatus));

    String customField = record.get("customField") == null ? "" : ((Utf8) record.get("customField")).toString();
    akamaiLog.setCustomField("-".equals(customField) ? null : customField);

    String turnAroundTimeMSec = record.get("turnAroundTimeMSec") == null ? "" : ((Utf8) record.get("turnAroundTimeMSec")).toString();
    akamaiLog.setTurnAroundTimeMSec(
        "-".equals(turnAroundTimeMSec) ? null : Integer.parseInt(turnAroundTimeMSec));

    String transferTimeMSec = record.get("transferTimeMSec") == null ? "" : ((Utf8) record.get("transferTimeMSec")).toString();
    akamaiLog.setTransferTimeMSec(
        "-".equals(transferTimeMSec) ? null : Integer.parseInt(transferTimeMSec));

    String cliIP = record.get("cliIP") == null ? "" : ((Utf8) record.get("cliIP")).toString();
    akamaiLog.setCliIP("-".equals(cliIP) ? null : cliIP);

    String statusCode = record.get("statusCode") == null ? "" : ((Utf8) record.get("statusCode")).toString();
    akamaiLog.setStatusCode("-".equals(statusCode) ? null : Integer.parseInt(statusCode));

    String reqHost = record.get("reqHost") == null ? "" : ((Utf8) record.get("reqHost")).toString();
    akamaiLog.setReqHost("-".equals(reqHost) ? null : reqHost);

    String reqMethod = record.get("reqMethod") == null ? "" : ((Utf8) record.get("reqMethod")).toString();
    akamaiLog.setReqMethod("-".equals(reqMethod) ? null : reqMethod);

    String bytes = record.get("bytes") == null ? "" : ((Utf8) record.get("bytes")).toString();
    akamaiLog.setBytes("-".equals(bytes) ? null : Integer.parseInt(bytes));

    String tlsVersion = record.get("tlsVersion") == null ? "" : ((Utf8) record.get("tlsVersion")).toString();
    akamaiLog.setTlsVersion("-".equals(tlsVersion) ? null : tlsVersion);

    Utf8 ua = (Utf8) record.get("UA");
    String uaStr = null;
    if (ua != null) {
      uaStr = URLDecoder.decode(ua.toString(), "UTF-8");
    }
    akamaiLog.setUA("-".equals(uaStr) ? null : uaStr);

    String queryStr = record.get("queryStr") == null ? "" : ((Utf8) record.get("queryStr")).toString();
    akamaiLog.setQueryStr("-".equals(queryStr) ? null : queryStr);


    String rspContentLen = record.get("rspContentLen") == null ? "" : ((Utf8) record.get("rspContentLen")).toString();
    akamaiLog.setRspContentLen("-".equals(rspContentLen) ? null : Integer.parseInt(rspContentLen));

    String rspContentType = record.get("rspContentType") == null ? "" : ((Utf8) record.get("rspContentType")).toString();
    akamaiLog.setRspContentType("-".equals(rspContentType) ? null : rspContentType);

    String reqPath = record.get("reqPath") == null ? "" : ((Utf8) record.get("reqPath")).toString();
    akamaiLog.setReqPath("-".equals(reqPath) ? null : reqPath);

    String reqPort = record.get("reqPort") == null ? "" : ((Utf8) record.get("reqPort")).toString();
    akamaiLog.setReqPort("-".equals(reqPort) ? null : Integer.parseInt(reqPort));

    String proto = record.get("proto") == null ? "" : ((Utf8) record.get("proto")).toString();
    akamaiLog.setProto("-".equals(proto) ? null : proto);

    String reqTimeSec = record.get("reqTimeSec") == null ? "" : ((Utf8) record.get("reqTimeSec")).toString().replace(".", "");
    akamaiLog.setReqTimeSec("-".equals(reqTimeSec) ? null : Long.parseLong(reqTimeSec));

    String reqTimeMillSec = record.get("reqTimeSec") == null ? "" : ((Utf8) record.get("reqTimeSec")).toString();
    akamaiLog.setReqTimeSecStr("-".equals(reqTimeMillSec) ? null : reqTimeMillSec);

    String cp = record.get("cp") == null ? "" : ((Utf8) record.get("cp")).toString();
    akamaiLog.setCp("-".equals(cp) ? null : Integer.parseInt(cp));

    String reqId = record.get("reqId") == null ? "" : ((Utf8) record.get("reqId")).toString();
    akamaiLog.setReqId("-".equals(reqId) ? null : reqId);

    String tlsOverheadTimeMSec = record.get("tlsOverheadTimeMSec") == null ? "" : ((Utf8) record.get("tlsOverheadTimeMSec")).toString();
    akamaiLog.setTlsOverheadTimeMSec("-".equals(tlsOverheadTimeMSec) ? null : Integer.parseInt(tlsOverheadTimeMSec));

    String objSize = record.get("objSize") == null ? "" : ((Utf8) record.get("objSize")).toString();
    akamaiLog.setObjSize("-".equals(objSize) ? null : Integer.parseInt(objSize));

    String uncompressedSize = record.get("uncompressedSize") == null ? "" : ((Utf8) record.get("uncompressedSize")).toString();
    akamaiLog.setUncompressedSize("-".equals(uncompressedSize) ? null : Integer.parseInt(uncompressedSize));

    String overheadBytes = record.get("overheadBytes") == null ? "" : ((Utf8) record.get("overheadBytes")).toString();
    akamaiLog.setOverheadBytes("-".equals(overheadBytes) ? null : Integer.parseInt(overheadBytes));

    String totalBytes = record.get("totalBytes") == null ? "" : ((Utf8) record.get("totalBytes")).toString();
    akamaiLog.setTotalBytes("-".equals(totalBytes) ? null : Integer.parseInt(totalBytes));

    String accLang = record.get("accLang") == null ? "" : ((Utf8) record.get("accLang")).toString();
    akamaiLog.setAccLang("-".equals(queryStr) ? null : accLang);

    String cookie = record.get("cookie") == null ? "" : ((Utf8) record.get("cookie")).toString();
    akamaiLog.setCookie("-".equals(cookie) ? null : cookie);

    String range = record.get("range") == null ? "" : ((Utf8) record.get("range")).toString();
    akamaiLog.setRange("-".equals(range) ? null : range);

    Utf8 referer = (Utf8) record.get("referer");
    String refererStr = null;
    if (referer != null) {
      refererStr = URLDecoder.decode(referer.toString(), "UTF-8");
    }
    akamaiLog.setReferer("-".equals(refererStr) ? null : refererStr);

    String xForwardedFor = record.get("xForwardedFor") == null ? "" : ((Utf8) record.get("xForwardedFor")).toString();
    akamaiLog.setXForwardedFor("-".equals(xForwardedFor) ? null : xForwardedFor);

    String maxAgeSec = record.get("maxAgeSec") == null ? "" : ((Utf8) record.get("maxAgeSec")).toString();
    akamaiLog.setMaxAgeSec("-".equals(maxAgeSec) ? null : Integer.parseInt(maxAgeSec));

    String reqEndTimeMSec = record.get("reqEndTimeMSec") == null ? "" : ((Utf8) record.get("reqEndTimeMSec")).toString();
    akamaiLog.setReqEndTimeMSec("-".equals(reqEndTimeMSec) ? null : Integer.parseInt(reqEndTimeMSec));

    String errorCode = record.get("errorCode") == null ? "" : ((Utf8) record.get("errorCode")).toString();
    akamaiLog.setErrorCode("-".equals(errorCode) ? null : errorCode);

    String dnsLookupTimeMSec = record.get("dnsLookupTimeMSec") == null ? "" : ((Utf8) record.get("dnsLookupTimeMSec")).toString();
    akamaiLog.setDnsLookupTimeMSec("-".equals(dnsLookupTimeMSec) ? null : Integer.parseInt(dnsLookupTimeMSec));

    String billingRegion = record.get("billingRegion") == null ? "" : ((Utf8) record.get("billingRegion")).toString();
    akamaiLog.setBillingRegion("-".equals(billingRegion) ? null : Integer.parseInt(billingRegion));

    String edgeIP = record.get("edgeIP") == null ? "" : ((Utf8) record.get("edgeIP")).toString();
    akamaiLog.setEdgeIP("-".equals(edgeIP) ? null : edgeIP);

    String securityRules = record.get("securityRules") == null ? "" : ((Utf8) record.get("securityRules")).toString();
    akamaiLog.setSecurityRules("-".equals(securityRules) ? null : securityRules);

    String serverCountry = record.get("serverCountry") == null ? "" : ((Utf8) record.get("serverCountry")).toString();
    akamaiLog.setServerCountry("-".equals(serverCountry) ? null : serverCountry);

    String streamId = ((Utf8) record.get("streamId")).toString();
    akamaiLog.setStreamId("-".equals(streamId) ? null : Integer.parseInt(streamId));

      Utf8 asn = (Utf8) record.get("asn");
      if(asn != null) {
        akamaiLog.setAsn(asn.toString());
    }

  }
}
