#!/usr/bin/env bash
if [ ! -d "${ANDROID_SDK_ROOT}" ]
then
  echo "ANDROID_SDK_ROOT doesn't point to a directory"
  exit 1
fi

function import_aar() {
  folder=$1
  aarfile=$2
  package=$3
  echo "setting up $folder..."
  rm -rf "${folder}"
  if [ ! -e "${aarfile}" ]
  then
    echo "${aarfile} doesn't exist"
    exit 1
  fi
  mkdir "${folder}"
  pushd "${folder}" || (echo "Couldn't find ${folder}" && exit)
  tar xf "${aarfile}"
  mkdir libs
  mv classes.jar libs/
  "${ANDROID_SDK_ROOT}/tools/android" create lib-project --name "${folder}" --path . --package "${package}" --target android-21
  ant debug
  ant release

  # eclipse looks for the library in the jar file of the project name
  cp "libs/classes.jar" "bin/${folder}.jar"
  popd || exit
}

import_aar appcompat "${ANDROID_SDK_ROOT}/extras/android/m2repository/com/android/support/appcompat-v7/21.0.3/appcompat-v7-21.0.3.aar" android.support.v7.appcompat
import_aar supportv4 "${ANDROID_SDK_ROOT}/extras/android/m2repository/com/android/support/support-v4/21.0.3/support-v4-21.0.3.aar"  android.support.v4


