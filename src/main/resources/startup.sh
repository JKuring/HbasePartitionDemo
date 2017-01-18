#!/usr/bin/env bash
TOOLKIT_HOME=$(dirname "$0")
EXE_JAR=${TOOLKIT_HOME}/../*.jar

JAR_PATH=lib
#CLASSPATH=
#if [ -d "${TOOLKIT_HOME}$JAR_PATH" ]; then
#        for i in ${TOOLKIT_HOME}$JAR_PATH/*.jar;
#                do
#      CLASSPATH="$CLASSPATH":"$i"
#    done
#fi
VM_ARGS="-Dauth.config.path=conf/hbase/auth/ -Dzookeeper.sasl.clientconfig=client -Dzookeeper.server.principal=zookeeper/hadoop.hadoop_b.com"

MAIN_CLASS=com.example.DemoApplication
echo $JAR_PATH $VM_ARGS $EXE_JAR $MAIN_CLASS "$@"
#java -classpath $CLASSPATH $VM_ARGS $MAIN_CLASS "$@" &
#java -cp $CLASSPATH $VM_ARGS -jar $EXE_JAR $MAIN_CLASS "$@"
java -Djava.ext.dirs=$JAR_PATH $VM_ARGS -jar $EXE_JAR $MAIN_CLASS "$@"
#java  -Djava.ext.dirs=lib -Dauth.config.path=conf\hbase\auth\ -Dzookeeper.sasl.clientconfig=client -Dzookeeper.server.principal=zookeeper/hadoop.hadoop_b.com -jar HBasePartitionDemo-1.0-SNAPSHOT.jar com.example.DemoApplication