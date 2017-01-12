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

### 2.2 Run
```
sudo docker run -d \
--name=zk1 \
--net=host \
-e SERVER_ID=1 \
-e APPEND_1=server.1=$zk1:2888:3888 \
-e APPEND_2=server.2=$zk2:2888:3888 \
-e APPEND_3=server.3=$zk3:2888:3888 \
feuyeux/zookeeper-alpine
```

```
sudo docker run -d \
--name=zk2 \
--net=host \
-e SERVER_ID=2 \
-e APPEND_1=server.1=$zk1:2888:3888 \
-e APPEND_2=server.2=$zk2:2888:3888 \
-e APPEND_3=server.3=$zk3:2888:3888 \
feuyeux/zookeeper-alpine
```

```
sudo docker run -d \
--name=zk3 \
--net=host \
-e SERVER_ID=3 \
-e APPEND_1=server.1=$zk1:2888:3888 \
-e APPEND_2=server.2=$zk2:2888:3888 \
-e APPEND_3=server.3=$zk3:2888:3888 \
feuyeux/zookeeper-alpine
```

### 2.3 Test
```
sudo docker run --rm -ti feuyeux/zookeeper-alpine bin/zkCli.sh -server $zk1:2181,$zk2:2181,$zk3:2181

ls /
```