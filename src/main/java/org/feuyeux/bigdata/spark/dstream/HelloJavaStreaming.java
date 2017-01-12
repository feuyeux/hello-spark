package org.feuyeux.bigdata.spark.dstream;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/**
 * https://spark.apache.org/docs/latest/streaming-programming-guide.html
 * nc -lk 9333
 */
public class HelloJavaStreaming {
    public static void main(String[] args) throws InterruptedException {
        SparkConf conf = new SparkConf()
                .setMaster("local[*]")
                .setAppName("HelloJavaStreaming");

        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(5));
        JavaReceiverInputDStream<String> lines = jssc.socketTextStream("localhost", 9333);
        JavaDStream<String> words = lines.flatMap((line -> {
            System.out.println(line);
            String[] wordsOneLine = line.split(" ");
            List<String> wordList = Arrays.asList(wordsOneLine);
            Iterator<String> wordIterator = wordList.iterator();
            return wordIterator;
            //return Arrays.asList(line.split(" ")).iterator();
        }));

        JavaPairDStream<String, Integer> pairs = words.mapToPair(word -> new Tuple2<>(word, 1));
        JavaPairDStream<String, Integer> wordCounts = pairs.reduceByKey((pre, after) -> pre + after);
        /*output operations*/
        wordCounts.print();
        jssc.start();
        jssc.awaitTermination();
    }
}