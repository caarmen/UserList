#!/usr/bin/env bash
if [ ! -d "${ANDROID_SDK_ROOT}" ]
then
  echo "ANDROID_SDK_ROOT doesn't point to a directory"
  exit 1
fi

mkdir appcompat
pushd appcompat || (echo "Couldn't find appcompat" && exit)
tar xf ../appcompat-v7-18.0.0.aar
mkdir libs
mv classes.jar libs/
"${ANDROID_SDK_ROOT}/tools/android" create lib-project --name appcompat --path . --package android.support.v7.appcompat --target android-14
ant debug
ant release

# eclipse looks for the library in appcompat.jar.
cp appcompat/lib/classes.jar appcompat/bin/appcompat.jar


popd || exit
