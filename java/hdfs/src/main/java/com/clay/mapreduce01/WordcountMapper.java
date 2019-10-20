package com.clay.mapreduce01;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordcountMapper extends Mapper<LongWritable,Text,Text,IntWritable> {

    Text k = new Text();
    IntWritable v = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1 获取第一行
        String line = value.toString();
        System.out.println("map行的："+line);

        String[] words = line.split(" ");

        for(String word: words){

            k.set(word);
            context.write(k,v);
        }
    }
}
