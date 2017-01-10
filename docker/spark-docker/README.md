## 1 feuyeux/spark-alpine

### 1.1 build
```
docker build -t spark .
```

### 1.2 run
```
docker run --rm -ti spark ./bin/run-example SparkPi
```

### 1.3 push
```
docker tag spark feuyeux/spark-alpine
docker login --username=feuyeux
docker push feuyeux/spark-alpine
```

## 2 Cluster

### 2.1 /etc/hosts
```
10.101.xx.xx zk1.io
10.101.xx.xx zk2.io
10.101.xx.xx zk3.io
10.101.xx.xx skm1.io
10.101.xx.xx skm2.io
10.101.xx.xx skw1.io
10.101.xx.xx skw2.io
10.101.xx.xx skw3.io
```

### 2.2 Spark Master
```
sudo docker run -d \
--name skm1.io \
--net=host \
-e SPARK_MASTER_HOST=skm1.io \
-e ZK=zk1.io:2181,zk2.io:2181,zk3.io:2181 \
feuyeux/spark-alpine master
```

```
sudo docker run -d \
--name skm2.io \
--net=host \
-e SPARK_MASTER_HOST=skm2.io \
-e ZK=zk1.io:2181,zk2.io:2181,zk3.io:2181 \
feuyeux/spark-alpine master
```

### 2.3 Spark Worker

```
sudo docker run -d \
--name skw1.io \
--net=host \
-e SPARK_MASTER_URL=spark://skm1.io:7077,skm2.io:7077 \
feuyeux/spark-alpine master
```

```
sudo docker run -d \
--name skw2.io \
--net=host \
-e SPARK_MASTER_URL=spark://skm1.io:7077,skm2.io:7077 \
feuyeux/spark-alpine master
```

```
sudo docker run -d \
--name skw3.io \
--net=host \
-e SPARK_MASTER_URL=spark://skm1.io:7077,skm2.io:7077 \
feuyeux/spark-alpine master
```

### 2.4 查看zookeeper上的spark状态

```
docker run --rm -ti feuyeux/zookeeper-alpine zkCli -server zk1.io:2181,zk2.io:2181,zk3.io:2181
```

```
ls /spark-recovery/leader_election

ls /spark-recovery/master_status
```

### 2.5 计算测试
```
docker run --rm -ti feuyeux/spark-alpine \
bin/spark-submit \
--master spark://skm1.io:7077,skm2.io:7077 \
examples/src/main/python/pi.py 13
```

```
docker run --rm -ti feuyeux/spark-alpine \
bin/spark-submit \
--class org.apache.spark.examples.SparkPi \
--master spark://skm1.io:7077,skm2.io:7077 \
--driver-memory 1g \
--executor-memory 4g \
--executor-cores 4 \
examples/jars/spark-examples_2.11-2.1.0.jar 13
```
