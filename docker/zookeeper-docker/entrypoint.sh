#!/bin/sh
ZOO_CFG="/opt/zookeeper/conf/zoo.cfg"
echo "server id (myid): ${SERVER_ID}"
echo "${SERVER_ID}" > /tmp/zookeeper/myid

echo "${APPEND_1}" >> ${ZOO_CFG}
echo "${APPEND_2}" >> ${ZOO_CFG}
echo "${APPEND_3}" >> ${ZOO_CFG}
echo "${APPEND_4}" >> ${ZOO_CFG}
echo "${APPEND_5}" >> ${ZOO_CFG}
echo "${APPEND_6}" >> ${ZOO_CFG}
echo "${APPEND_7}" >> ${ZOO_CFG}
echo "${APPEND_8}" >> ${ZOO_CFG}
echo "${APPEND_9}" >> ${ZOO_CFG}
echo "${APPEND_10}" >> ${ZOO_CFG}
/opt/zookeeper/bin/zkServer.sh start-foreground