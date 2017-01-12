package org.feuyeux.bigdata.spark.rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * https://spark.apache.org/docs/latest/programming-guide.html
 */
public class HelloJavaRdd {
    private static final Pattern COMMA = Pattern.compile(",");
    private static final String localMaster = "local[*]";
    private static final String standAloneMaster = "spark://skm1.io:7077,skm2.io:7077";

    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setMaster(localMaster)
                .setAppName("HelloJavaRdd");

        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> lines = sc.textFile("file:/tmp/108").cache();
        System.out.println(lines.count());
        JavaRDD<String> words = lines.flatMap((FlatMapFunction<String, String>) line -> {
            System.out.println(line);
            return Arrays.asList(COMMA.split(line)).iterator();
        });
        System.out.println(words.count());
    }
}
