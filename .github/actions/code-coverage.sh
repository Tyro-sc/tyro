#!/usr/bin/env bash

. ${PWD}/.github/actions/logger.sh

curl -L https://codeclimate.com/downloads/test-reporter/test-reporter-latest-linux-amd64 > ./cc-test-reporter
chmod +x ./cc-test-reporter \

MODULES=(core web)
CURRENT_DIR=$PWD

./cc-test-reporter before-build

for module in "${MODULES[@]}"; do
  COVERAGE_FILE="./${module}/target/site/jacoco/jacoco.xml"
  ./cc-test-reporter format-coverage --input-type jacoco --output "codeclimate.${module}.json"
done

./cc-test-reporter sum-coverage--output codeclimate.json codeclimate.*.json
./cc-test-reporter upload-coverage --input codeclimate.json
./cc-test-reporter after-build --coverage-input-type jacoco --exit-code $?

#
#for module in "${MODULES[@]}"; do
#  cp "./${module}/target/site/jacoco/jacoco.xml" "./${module}/src/main/groovy/jacoco.xml"
#  cd "./${module}/src/main/groovy/" || exit
#  ../../../../cc-test-reporter format-coverage -t jacoco -o "codeclimate.${module}.json" jacoco.xml
#  mv "codeclimate.${module}.json" "${CURRENT_DIR}"
#  rm jacoco.xml
#
#  cd "${CURRENT_DIR}" || exit
#done
#
#cd "${CURRENT_DIR}" || exit
#./cc-test-reporter sum-coverage codeclimate.*.json -p 2
#./cc-test-reporter upload-coverage