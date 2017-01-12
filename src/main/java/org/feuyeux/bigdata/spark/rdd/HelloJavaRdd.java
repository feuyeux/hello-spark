package org.feuyeux.bigdata.spark.rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/**
 * https://spark.apache.org/docs/latest/programming-guide.html
 */
public class HelloJavaRdd {
    private static final Pattern COMMA = Pattern.compile(",");
    private static final Pattern SPACE = Pattern.compile(" ");
    private static final String localMaster = "local[*]";
    private static final String standAloneMaster = "spark://skm1.io:7077,skm2.io:7077";

    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setMaster(localMaster)
                .setAppName("HelloJavaRdd");

        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> lines = sc.textFile("file:/tmp/108").cache();
        System.out.println(lines.count());
        JavaPairRDD<String, String> words = lines.flatMapToPair((PairFlatMapFunction<String, String, String>) line -> {
            System.out.println(line);
            String[] wordsInLine = COMMA.split(line);
            String[] names = SPACE.split(wordsInLine[2]);
            List<Tuple2<String, String>> results = new ArrayList<>();
            for (String word : wordsInLine) {
                results.add(new Tuple2<>(names[0], word));
            }
            return results.iterator();
        });

        words.groupByKey().foreach(t -> {
            System.out.println(t._1() + ":");
            AtomicInteger i = new AtomicInteger(1);
            t._2().forEach(w -> {
                System.out.print(i.getAndIncrement() + "." + w + ",");
            });
            System.out.println();
        });
    }
}
