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

### 2.0 Clean
```
sudo docker kill $(sudo docker ps -aq)
sudo docker rm $(sudo docker ps -aq)
sudo docker images|grep none|awk '{print $3 }'|xargs sudo docker rmi
```

### 2.1 Env Variable
```
zk1=10.101.xx.aa 
skw2=10.101.xx.aa
zk2=10.101.xx.bb
skw3=10.101.xx.bb
zk3=10.101.xx.cc
skw4=10.101.xx.cc
skm1=10.101.xx.dd
skw5=10.101.xx.dd
skm2=10.101.xx.ee
skw6=10.101.xx.ee
skw1=10.101.xx.ff
```

### 2.2 Spark Master
```
sudo docker run -d \
--name skm1 \
--net=host \
-e SPARK_MASTER_HOST=$skm1 \
-e ZK=$zk1:2181,$zk2:2181,$zk3:2181 \
feuyeux/spark-alpine master
```

```
sudo docker run -d \
--name skm2 \
--net=host \
-e SPARK_MASTER_HOST=$skm2 \
-e ZK=$zk1:2181,$zk2:2181,$zk3:2181 \
feuyeux/spark-alpine master
```

### 2.3 Spark Worker

```
sudo docker run -d \
--name skw1 \
--net=host \
-e SPARK_MASTER_URL=spark://$skm1:7077,$skm2:7077 \
feuyeux/spark-alpine worker
```

```
sudo docker run -d \
--name skw2 \
--net=host \
-e SPARK_MASTER_URL=spark://$skm1:7077,$skm2:7077 \
feuyeux/spark-alpine worker
```

```
sudo docker run -d \
--name skw3 \
--net=host \
-e SPARK_MASTER_URL=spark://$skm1:7077,$skm2:7077 \
feuyeux/spark-alpine worker
```

```
sudo docker run -d \
--name skw4 \
--net=host \
-e SPARK_MASTER_URL=spark://$skm1:7077,$skm2:7077 \
feuyeux/spark-alpine worker
```

```
sudo docker run -d \
--name skw5 \
--net=host \
-e SPARK_MASTER_URL=spark://$skm1:7077,$skm2:7077 \
feuyeux/spark-alpine worker
```

```
sudo docker run -d \
--name skw6 \
--net=host \
-e SPARK_MASTER_URL=spark://$skm1:7077,$skm2:7077 \
feuyeux/spark-alpine worker
```

### 2.4 查看zookeeper上的spark状态

```
sudo docker run --rm -ti feuyeux/zookeeper-alpine bin/zkCli.sh -server $zk1:2181,$zk2:2181,$zk3:2181
```

```
ls /spark-recovery/leader_election

ls /spark-recovery/master_status
```

### 2.5 计算测试
```
sudo docker run --rm -ti feuyeux/spark-alpine \
bin/spark-submit \
--master spark://$skm1:7077,$skm2:7077 \
examples/src/main/python/pi.py 13
```

```
sudo docker run --rm -ti feuyeux/spark-alpine \
bin/spark-submit \
--class org.apache.spark.examples.SparkPi \
--master spark://$skm1:7077,$skm2:7077 \
--driver-memory 1g \
--executor-memory 4g \
--executor-cores 4 \
examples/jars/spark-examples_2.11-2.1.0.jar 13
```
