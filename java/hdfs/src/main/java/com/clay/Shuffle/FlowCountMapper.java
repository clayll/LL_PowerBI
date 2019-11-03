package com.clay.Shuffle;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowCountMapper extends Mapper<LongWritable,Text,FlowBean,Text> {

    FlowBean bean = new FlowBean();
    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context)	throws IOException, InterruptedException {

        // 1 获取一行
        String line = value.toString();

        // 2 截取
        String[] fields = line.split("\t");

        // 3 封装对象
        String phoneNbr = fields[1];
        System.out.println("number:"+phoneNbr);
        long upFlow = Long.parseLong(fields[4]);
        long downFlow = Long.parseLong(fields[5]);

        bean.set(upFlow, downFlow);
        v.set(phoneNbr);

        // 4 输出
        context.write(bean, v);
    }



}
