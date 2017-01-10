FROM java:openjdk-8u111-jre-alpine
MAINTAINER Eric Han "feuyeux@gmail.com"

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories
RUN apk add --no-cache bash

ENV ZK_VERSION=3.4.9

ADD entrypoint.sh /opt/entrypoint.sh

RUN wget http://apache.fayea.com/zookeeper/zookeeper-${ZK_VERSION}/zookeeper-${ZK_VERSION}.tar.gz -P /tmp \
&& tar -xzf /tmp/zookeeper-${ZK_VERSION}.tar.gz -C /opt \
&& cp /opt/zookeeper-${ZK_VERSION}/conf/zoo_sample.cfg /opt/zookeeper-${ZK_VERSION}/conf/zoo.cfg \
&& mv /opt/zookeeper-${ZK_VERSION} /opt/zookeeper \
&& chmod 777 /opt/entrypoint.sh \
&& rm -f /tmp/zookeeper-${ZK_VERSION}.tar.gz

EXPOSE 2181 2888 3888
WORKDIR /opt/zookeeper
VOLUME ["/opt/zookeeper/conf", "/tmp/zookeeper"]
CMD ["/opt/entrypoint.sh"]