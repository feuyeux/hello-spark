#!/bin/bash
line=$(printf "%-60s")
echo "${line// /#}"
echo "/etc/hosts:"
cat /etc/hosts
echo "hostname:"
/bin/bash -c "hostname"
echo "${line// /#}"

CMD=${1:-"exit 0"}
if [[ "$CMD" == "master" ]]; then
  export SPARK_DAEMON_JAVA_OPTS="-Dspark.deploy.recoveryMode=ZOOKEEPER -Dspark.deploy.zookeeper.url=${ZK} -Dspark.deploy.zookeeper.dir=/spark-recovery"
  /bin/bash -c "${SPARK_HOME}/bin/spark-class org.apache.spark.deploy.master.Master -h ${SPARK_MASTER_HOST}"
  /bin/bash -c "tail -f ${SPARK_HOME}/logs/spark--org.apache.spark.deploy.master.Master*"
elif [[ "$CMD" == "worker" ]]; then
  /bin/bash -c "${SPARK_HOME}/bin/spark-class org.apache.spark.deploy.worker.Worker ${SPARK_MASTER_URL}"
  /bin/bash -c "tail -f ${SPARK_HOME}/logs/spark--org.apache.spark.deploy.worker.Worker*"
else
  /bin/bash -c "$*"
fi