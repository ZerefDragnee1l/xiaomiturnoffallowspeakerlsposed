#!/usr/bin/env sh
##############################################################################
# Gradle start up script generated for Unix
##############################################################################
APP_BASE_NAME=`basename "$0"`
APP_HOME=`dirname "$0"`
# Resolve symlinks
while [ -h "$0" ] ; do
  ls=`ls -ld "$0"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    APP_HOME=`dirname "$link"`
  else
    APP_HOME=`dirname "$0"`/`dirname "$link"`
  fi
  0=`basename "$link"`
done
CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar
JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8"
exec "$JAVA_HOME/bin/java" $JAVA_OPTS -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
