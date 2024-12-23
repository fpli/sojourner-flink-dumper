ALTER TABLE ubi_w.stg_event_akamai_bot_w RECOVER PARTITIONS;
refresh table ubi_w.stg_event_akamai_bot_w;

CREATE OR REPLACE TEMPORARY VIEW akamai_dedup_v as
SELECT
    version
     , ewUsageInfo
     , ewExecutionInfo
     , country
     , city
     , state
     , cacheStatus
     , customField
     , turnAroundTimeMSec
     , transferTimeMSec
     , cliIP
     , statusCode
     , reqHost
     , reqMethod
     , bytes
     , tlsVersion
     , UA
     , queryStr
     , rspContentLen
     , rspContentType
     , reqPath
     , reqPort
     , proto
     , reqTimeSec
     , cp
     , reqId
     , tlsOverheadTimeMSec
     , objSize
     , uncompressedSize
     , overheadBytes
     , totalBytes
     , accLang
     , cookie
     , `range`
     , referer
     , xForwardedFor
     , maxAgeSec
     , reqEndTimeMSec
     , errorCode
     , dnsLookupTimeMSec
     , billingRegion
     , edgeIP
     , securityRules
     , serverCountry
     , streamId
     , asn
     , breadcrumbs
     , row_number() OVER (PARTITION BY
    version
    , sojlib.soj_map_to_str(ewUsageInfo)
    , sojlib.soj_map_to_str(ewExecutionInfo)
    , country
    , city
    , state
    , cacheStatus
    , customField
    , turnAroundTimeMSec
    , transferTimeMSec
    , cliIP
    , statusCode
    , reqHost
    , reqMethod
    , bytes
    , tlsVersion
    , UA
    , queryStr
    , rspContentLen
    , rspContentType
    , reqPath
    , reqPort
    , proto
    , reqTimeSec
    , cp
    , reqId
    , tlsOverheadTimeMSec
    , objSize
    , uncompressedSize
    , overheadBytes
    , totalBytes
    , accLang
    , cookie
    , `range`
    , referer
    , xForwardedFor
    , maxAgeSec
    , reqEndTimeMSec
    , errorCode
    , dnsLookupTimeMSec
    , billingRegion
    , edgeIP
    , securityRules
    , serverCountry
    , streamId
    , asn
    , breadcrumbs
    ORDER BY reqTimeSec DESC) AS row_number
FROM ubi_w.stg_event_akamai_bot_w
WHERE dt = '${UOW_FROM_DATE}'
;


CREATE OR REPLACE TEMPORARY VIEW akamai_rt_dedup_v AS
SELECT
    version
     , ewUsageInfo
     , ewExecutionInfo
     , country
     , city
     , state
     , cacheStatus
     , customField
     , turnAroundTimeMSec
     , transferTimeMSec
     , cliIP
     , statusCode
     , reqHost
     , reqMethod
     , bytes
     , tlsVersion
     , UA
     , queryStr
     , rspContentLen
     , rspContentType
     , reqPath
     , reqPort
     , proto
     , reqTimeSec
     , cp
     , reqId
     , tlsOverheadTimeMSec
     , objSize
     , uncompressedSize
     , overheadBytes
     , totalBytes
     , accLang
     , cookie
     , `range`
     , referer
     , xForwardedFor
     , maxAgeSec
     , reqEndTimeMSec
     , errorCode
     , dnsLookupTimeMSec
     , billingRegion
     , edgeIP
     , securityRules
     , serverCountry
     , streamId
     , asn
     , breadcrumbs
FROM akamai_dedup_v e
WHERE row_number = 1
;

INSERT OVERWRITE TABLE ubi_t.akamai_event_bot_ods PARTITION(dt='${UOW_FROM_DATE}')
SELECT
    version
     , ewUsageInfo
     , ewExecutionInfo
     , country
     , city
     , state
     , cacheStatus
     , customField
     , turnAroundTimeMSec
     , transferTimeMSec
     , cliIP
     , statusCode
     , reqHost
     , reqMethod
     , bytes
     , tlsVersion
     , UA
     , queryStr
     , rspContentLen
     , rspContentType
     , reqPath
     , reqPort
     , proto
     , reqTimeSec
     , cp
     , reqId
     , tlsOverheadTimeMSec
     , objSize
     , uncompressedSize
     , overheadBytes
     , totalBytes
     , accLang
     , cookie
     , range
     , referer
     , xForwardedFor
     , maxAgeSec
     , reqEndTimeMSec
     , errorCode
     , dnsLookupTimeMSec
     , billingRegion
     , edgeIP
     , securityRules
     , serverCountry
     , streamId
     , asn
     , breadcrumbs
FROM akamai_rt_dedup_v
    CLUSTER BY reqTimeSec, reqId
;


DROP VIEW IF EXISTS UBI_V.AKAMAI_EVENT_BOT;
create view UBI_V.AKAMAI_EVENT_BOT as
select
  version,
  ewUsageInfo,
  ewExecutionInfo,
  country,
  city,
  state,
  cacheStatus,
  customField,
  turnaroundTimeMSec,
  transferTimeMSec,
  cliIp,
  statusCode,
  reqHost,
  reqMethod,
  bytes,
  tlsVersion,
  UA,
  queryStr,
  rspContentLen,
  rspContentType,
  reqPath,
  reqPort,
  proto,
  reqTimeSec,
  to_timestamp(
    concat(
      from_unixtime(reqTimeSec / 1000, 'yyyy-MM-dd HH:mm:ss'),
      '.',
      substr(reqTimeSec, -3)
    )
  ) as reqTime,
  cp,
  reqId,
  tlsOverheadTimeMSec,
  objSize,
  uncompressedSize,
  overheadBytes,
  totalBytes,
  accLang,
  cookie,
  range,
  referer,
  xForwardedFor,
  maxAgeSec,
  reqEndTimeMSec,
  errorCode,
  dnsLookupTimeMSec,
  billingRegion,
  edgeip,
  securityRules,
  serverCountry,
  streamId,
  asn,
  breadcrumbs,
  dt
from
  UBI_T.AKAMAI_EVENT_BOT_ODS;