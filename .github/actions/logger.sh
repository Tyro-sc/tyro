#!/usr/bin/env bash

info() {
    id=$1
    message=$2
    log  " 🔵️ ️INFO" "${id}" "${message}"
}

warn() {
    id=$1
    message=$2
    log  " ⚠️ WARN" "${id}" "${message}"
}

error() {
    id=$1
    message=$2
    log " 🔴 ERROR" "${id}" "${message}"
}

log() {
    level=$1
    id=$2
    message=$3
    echo " 🔥 [Tyro CI][${level}](${id}) ${message}"
}
