#!/usr/bin/ksh -e
#------------------------------------------------------------------------------------------------
# Title:
# File Name:    sg_ubi_data.akamai_dump_file_chk.ksh
# Description:  check if is updated in last 20 mins for dump folder
# Location:     $DW_EXE/sg_ubi_data/sg_ubi_data.akamai_dump_file_chk.ksh or Auto-ETL
#
# Revision History:
#
# Name             Date            Description
# ---------------  --------------  ---------------------------------------------------
# Lu Dongfang      2022-10-31      Initial Creation
# Lu Dongfang      2022-12-14      Update loop condition
#------------------------------------------------------------------------------------------------

run_dt=${UOW_FROM:0:8}

export HDP_CMD="/apache/hadoop/bin/hadoop"

current_time=`date +%s`
re_run=0

for i in {1..60}
do
  re_run=0
  current_time=`date +%s`
  current_date=`date +%Y%m%d`
  yesterday_date=`date +"%Y%m%d" -d "1 day ago"`
  current_hour=`date +%H`
  dump_folder="/sys/edw/working/ubi/ubi_w/soj/stg_ubi_akamai_dump_w/dt=${run_dt}"
  current_folder="/sys/edw/working/ubi/ubi_w/soj/stg_ubi_akamai_dump_w/dt=${current_date}"
  yesterday_folder="/sys/edw/working/ubi/ubi_w/soj/stg_ubi_akamai_dump_w/dt=${yesterday_date}"

  set +e
    dt=`${HDP_CMD} fs -ls -R $dump_folder | awk -F" " '{print $6" "$7}' | sort -nr | head -1`
    rcode=$?
    dt_current=`${HDP_CMD} fs -ls -R $current_folder | awk -F" " '{print $6" "$7}' | sort -nr | head -1`
    rcode2=$?
    dt_yesterday=`${HDP_CMD} fs -ls -R $yesterday_folder | awk -F" " '{print $6" "$7}' | sort -nr | head -1`
    rcode3=$?
  set -e

  if [ $rcode != 0 ]
  then
    echo "failed to  list information for folder: $dump_folder"
    re_run=1
    break
  fi


  echo "latest update time for folder:$dump_folder is $dt"
  echo "latest update time for folder:$current_folder is $dt_current"
  echo "latest update time for folder:$yesterday_folder is $dt_yesterday"

  if [ $rcode2 != 0 ] && [ $rcode3 !=0 ]
  then
    echo "failed to  list information for folder: $current_folder and $yesterday_folder"
    re_run=1
    break
  fi

  dt_to_seconds=`date -d "$dt" +%s`
  dt_current_to_second=`date -d "$dt_current" +%s`
  echo "dt_current_to_second: $dt_current_to_second"
  dt_yesterday_to_second=`date -d "$dt_yesterday" +%s`
  echo "dt_yesterday_to_second: $dt_yesterday_to_second"
  if [ -z "$dt_current" ]
  then
    diff_current=$((current_time-dt_yesterday_to_second))
  else
    diff_current=$((current_time-dt_current_to_second))
  fi
  diff=$((current_time-dt_to_seconds))
  if [[ $diff_current -lt 60*10 ]]; then
    echo "dump thread running, current time: ${current_time}, latest update time: ${dt_current_to_second} "
  else
      echo "dump thread not running for more than 10 mins, current time: ${current_time}, latest update time: ${dt_current_to_second} "
      re_run=1
      break
  fi

  if [[ $diff -gt 60*30 ]]; then
    echo "data for this dump folder has not been updated for more than 30 mins, current time: ${current_time}, latest update time: ${dt_to_seconds} "
  else
      echo "data for this dump folder is being updated in 30 mins, current time: ${current_time}, dump update time: ${dt_to_seconds}, latest update time: ${dt_current_to_second} "
      re_run=1
  fi

  if [ $re_run != 0 ]
  then
    echo "dump file checking is not ready, please wait for 3 mins"
    sleep 180
  else
    echo "dump file checking is ready, please going on."
    exit 0
  fi

done

exit -1