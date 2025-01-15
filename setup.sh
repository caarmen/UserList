#!/usr/bin/env bash
if [ ! -d "${ANDROID_SDK_ROOT}" ]
then
  echo "ANDROID_SDK_ROOT doesn't point to a directory"
  exit 1
fi

git clone git@github.com:JakeWharton/ActionBarSherlock.git -b 3.5.1
pushd ActionBarSherlock/library || (echo "Couldn't find ActionBarSherlock" && exit)

"${ANDROID_SDK_ROOT}/tools/android" update project --path . --target android-13
ant debug
# build the jar file
ant release
# eclipse looks for the library in actionbarsherlock.jar (ant looks for it in classes.jar).
cp ActionBarSherlock/library/bin/classes.jar ActionBarSherlock/library/bin/actionbarsherlock.jar
popd || exit
