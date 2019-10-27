package com.clay.keyAndNLine;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class keyAndNLineMapper extends Mapper<LongWritable,Text,Text,IntWritable>{

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        System.out.println("key :"+key+" value:"+value);

        String[] values = value.toString().split(" ");

        Text t = new Text(values[0]);
        IntWritable number  = new IntWritable(1);

        context.write(t,number);



    }
}
