package com.clay.keyAndNLine;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class keyAndNlineReducer extends  Reducer<Text, IntWritable, Text,IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        IntWritable v = new IntWritable(0);
        int sum = 0;
        for(IntWritable value : values){
            sum += value.get();
        }
        v.set(sum);
        context.write(key,v);
    }
}
