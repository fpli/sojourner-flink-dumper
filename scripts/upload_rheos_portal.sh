#!/usr/bin/env bash

working_dir=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P )
cd ${working_dir}/..

API_KEY=9ce5764c30224ce59bb5eb4c95afaf02
API_SECRET=K2h42hFcxWpNV33EgTluY7oekgrBmTl26u6HMWrTVnQgzaj6Vld4QGFUAiN8RUsz


# find artifact in target folder
if ls target/*.jar 1> /dev/null 2>&1; then
  # if it's in CI server env, BUILD_NUM will be set by Jenkins
  BUILD_NUM=${BUILD_NUMBER:-$(date '+%Y%m%d.%H%M%S')}
  for i in $(ls target/*.jar); do
    if [[ "${i%.jar}" == *-SNAPSHOT ]]; then
      mv "$i" "`echo $i | sed "s/-SNAPSHOT/.${BUILD_NUM}/"`";
    elif [[ "$(cat ./pomVersion)" == *-SNAPSHOT  ]]; then
      # build num already generated
      BUILD_NUM=${BUILD_NUMBER:-${i:(-19):15}}
    fi
  done
  echo "Build number is: $BUILD_NUM"

  JAR_NAME=$(mvn help:evaluate -Dexpression=project.artifactId -q -DforceStdout)
  JAR_TAG=$(cat ./pomVersion | sed "s/-SNAPSHOT/.${BUILD_NUM}/")

  echo "==================== Uploading jar to Rheos Portal ===================="
  echo "==================== Uploading jar is : ${JAR_NAME}:${JAR_TAG}"
  mvn job-uploader:upload \
    -Dusername=${API_KEY} \
    -Dpassword=${API_SECRET} \
    -Dnamespace=sojourner-ubd \
    -DjobJarName=${JAR_NAME} \
    -DjobJarTag=${JAR_TAG}
else
  echo "Cannot find job jar file"
  exit 1
fi