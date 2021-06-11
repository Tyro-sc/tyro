#!/usr/bin/env bash

. ${PWD}/.github/actions/logger.sh

#GITHUB_WORKSPACE=~/Projects/Tyro-sc/tyro

cd "${GITHUB_WORKSPACE}" || exit

curl -L https://codeclimate.com/downloads/test-reporter/test-reporter-latest-linux-amd64 > ./cc-test-reporter
chmod +x ./cc-test-reporter \

./cc-test-reporter before-build

cp "${GITHUB_WORKSPACE}/core/target/site/jacoco/jacoco.xml" "${GITHUB_WORKSPACE}/core/src/main/groovy/jacoco.xml"
cd "${GITHUB_WORKSPACE}/core/src/main/groovy" || exit
../../../../cc-test-reporter format-coverage -d --input-type jacoco --output "../../../../codeclimate.core.json"

cp "${GITHUB_WORKSPACE}/web/target/site/jacoco/jacoco.xml" "${GITHUB_WORKSPACE}/web/src/main/groovy/jacoco.xml"
cd "${GITHUB_WORKSPACE}/web/src/main/groovy" || exit
../../../../cc-test-reporter format-coverage -d --input-type jacoco --output "../../../../codeclimate.web.json"

cd "${GITHUB_WORKSPACE}" || exit
./cc-test-reporter sum-coverage codeclimate.*.json --parts 2 --output codeclimate.json
./cc-test-reporter upload-coverage --input codeclimate.json