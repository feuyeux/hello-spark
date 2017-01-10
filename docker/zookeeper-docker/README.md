## 1 feuyeux/zookeeper-alpine

### build
```
docker build -t zk .
```

### push
```
docker tag zk feuyeux/zookeeper-alpine
docker login --username=feuyeux
docker push feuyeux/zookeeper-alpine
```

## 2 Cluster
### 2.1 run
```
docker run -d \
--name=zk1.io \
--net=host \
-e SERVER_ID=1 \
-e APPEND_1=server.1=zk1.io:2888:3888 \
-e APPEND_2=server.2=zk2.io:2888:3888 \
-e APPEND_3=server.3=zk3.io:2888:3888 \
feuyeux/zookeeper-alpine
```

```
docker run -d \
--name=zk2.io \
--net=host \
-e SERVER_ID=2 \
-e APPEND_1=server.1=zk1.io:2888:3888 \
-e APPEND_2=server.2=zk2.io:2888:3888 \
-e APPEND_3=server.3=zk3.io:2888:3888 \
feuyeux/zookeeper-alpine
```

```
docker run -d \
--name=zk3.io \
--net=host \
-e SERVER_ID=3 \
-e APPEND_1=server.1=zk1.io:2888:3888 \
-e APPEND_2=server.2=zk2.io:2888:3888 \
-e APPEND_3=server.3=zk3.io:2888:3888 \
feuyeux/zookeeper-alpine
```

### 2.2 test
```
docker run --rm -ti feuyeux/zookeeper-alpine zkCli -server zk1.io:2181,zk2.io:2181,zk3.io:2181

ls /
```