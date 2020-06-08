#!/usr/bin/env bash

curl -L https://codeclimate.com/downloads/test-reporter/test-reporter-latest-linux-amd64 > ./cc-test-reporter
chmod +x ./cc-test-reporter \

cp ./core/target/site/jacoco/jacoco.xml ./core/src/main/groovy/jacoco.xml
cp ./web/target/site/jacoco/jacoco.xml ./web/src/main/groovy/jacoco.xml

CURRENT_DIR=$PWD

cd ./core/src/main/groovy/
../../../../cc-test-reporter format-coverage -t jacoco -o codeclimate.core.json jacoco.xml
mv codeclimate.core.json ${CURRENT_DIR}
rm jacoco.xml

cd ${CURRENT_DIR}
cd ./web/src/main/groovy/
../../../../cc-test-reporter format-coverage -t jacoco -o codeclimate.web.json jacoco.xml
mv codeclimate.web.json ${CURRENT_DIR}
rm jacoco.xml

cd ${CURRENT_DIR}
./cc-test-reporter sum-coverage codeclimate.*.json -p 2
./cc-test-reporter upload-coverage -r