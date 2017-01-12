package org.feuyeux.bigdata.spark.dstream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * https://spark.apache.org/docs/latest/streaming-programming-guide.html
 * nc -lk 9333
 */
object HelloScalaStreaming {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local[*]").setAppName("HelloScalaStreaming")
    val ssc = new StreamingContext(conf, Seconds(5))
    val lines = ssc.socketTextStream("localhost", 9333)
    val words = lines.flatMap(line => line.split(" "))
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKey((pre, after) => pre + after)
    /*output operations*/
    wordCounts.print()
    ssc.start()
    ssc.awaitTermination()
  }
}