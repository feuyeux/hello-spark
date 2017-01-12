# hello-spark
Using simplest Java and Scala code to demonstrate spark's world

### Spark world
1. **RDD**:{[Java](src/main/java/org/feuyeux/bigdata/spark/rdd)} {[Scala](src/main/scala/org/feuyeux/bigdata/spark/rdd)}
2. **DStream**:{[Java](src/main/java/org/feuyeux/bigdata/spark/dstream)} {[Scala](src/main/scala/org/feuyeux/bigdata/spark/dstream)}
3. **DataFrame/DataSet**:{[Java](src/main/java/org/feuyeux/bigdata/spark/dfds)} {[Scala](src/main/scala/org/feuyeux/bigdata/spark/dfds)}
4. **Structured Streaming**:{[Java](src/main/java/org/feuyeux/bigdata/spark/sstram)} {[Scala](src/main/scala/org/feuyeux/bigdata/spark/sstram)}
5. **ML with DataFrame-based**:{[Java](src/main/java/org/feuyeux/bigdata/spark/mlstream)} {[Scala](src/main/scala/org/feuyeux/bigdata/spark/mlstream)}
6. **ML with RDD-based**:{[Java](src/main/java/org/feuyeux/bigdata/spark/mlrdd)} {[Scala](src/main/scala/org/feuyeux/bigdata/spark/mlrdd)}
7. **GraphX**:{[Java](src/main/java/org/feuyeux/bigdata/spark/graphx)} {[Scala](src/main/scala/org/feuyeux/bigdata/spark/graphx)}

### Spark with the world
1. spark with **Message-Driven** — [Kafka](http://kafka.apache.org/) and [RabbitMQ](https://www.rabbitmq.com/)
2. spark with **Search Engine** — [ElasticSearch](https://www.elastic.co/products/elasticsearch)
3. spark with **Deep learning** — [DL4J](https://deeplearning4j.org/) and [BigDL](https://github.com/intel-analytics/BigDL)
4. spark with **Big Data**
   - **Key-value Stores** — [Redis](redis.io)
   - **Wide Column Stores** — [Cassandra]( cassandra.apache.org) and [HBase](hbase.apache.org)
   - **Document Stores** — [MongoDB](https://www.mongodb.com/) and [Amazon DynamoDB](aws.amazon.com/dynamodb)
5. spark with **Application** — [Spring Cloud Data Flow](http://cloud.spring.io/spring-cloud-dataflow/)
6. spark with **[Docker](https://www.docker.com/)**: {[spark-docker](docker/spark-docker)} {[zookeeper-docker](docker/zookeeper-docker)}

### Spark papers
1. [Spark: Cluster Computing with Working Sets](paper/1.hotcloud_spark.pdf) June 2010
2. [RDDs](paper/2.nsdi_spark.pdf) April 2012
3. [shark demo](0.sigmod_shark_demo.pdf) May 2012
4. [DStream: An Efficient and Fault-Tolerant Model for Stream Processing on Large Clusters](paper/3.hotcloud_spark_streaming.pdf) June 2012
5. [DStream: A Fault-Tolerant Model for Scalable Stream Processing](paper/3.EECS-2012-259.pdf) December 2012
6. [Shark](paper/0.sigmod_shark.pdf) June 2013
7. [DStream: Fault-Tolerant Streaming Computation at Scale](paper/4.sosp_spark_streaming.pdf) November 2013
8. [GraphX](paper/5.graphx.pdf) October 2014
9. [Spark SQL](paper/6.sigmod_spark_sql.pdf) June 2015
10. [MLlib](paper/7.15-237.pdf) 2016

### Other streaming frameworks
- [Google Dataflow](https://cloud.google.com/dataflow/) and [Apache Beam](https://beam.apache.org/)
- [Apache Flink](http://flink.apache.org/)
- [Storm](http://storm.apache.org/) and [Heron](https://twitter.github.io/heron/)
- [Amazon Kinesis](https://aws.amazon.com/kinesis/streams/)
