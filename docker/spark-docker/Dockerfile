FROM java:openjdk-8u111-jre-alpine
MAINTAINER Eric Han "feuyeux@gmail.com"

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories
RUN apk add --no-cache bash

ENV SPARK_VERSION=2.1.0
ENV HADOOP_VERSION=2.7

ADD bootstrap.sh /usr/local/bootstrap.sh

COPY spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz /tmp
#RUN wget http://d3kbcqa49mib13.cloudfront.net/spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz -P /tmp \

RUN tar -xzf /tmp/spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz -C /usr/local/ \
&& cd /usr/local && ln -s spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION} spark \
&& chmod 777 /usr/local/bootstrap.sh \
&& rm -f /tmp/spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz

ENV SPARK_HOME /usr/local/spark
WORKDIR /usr/local/spark

ENTRYPOINT ["/usr/local/bootstrap.sh"]