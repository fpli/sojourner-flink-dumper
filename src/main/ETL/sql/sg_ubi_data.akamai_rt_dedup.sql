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
    ORDER BY reqTimeSec DESC) AS row_number
FROM ubi_w.stg_event_akamai_bot_w
WHERE dt = ${uow_from_date}
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
FROM akamai_dedup_v e
WHERE row_number = 1
;


select count(*) from akamai_dedup_v;
select count(*) from akamai_rt_dedup_v;
select * from akamai_rt_dedup_v;


-- INSERT OVERWRITE TABLE ubi_w.akamai_event_bot PARTITION(dt='${uow_from_date}')
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
FROM akamai_rt_dedup_v
    CLUSTER BY reqTimeSec, reqId
;