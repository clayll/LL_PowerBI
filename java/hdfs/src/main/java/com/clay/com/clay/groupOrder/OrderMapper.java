package com.clay.com.clay.groupOrder;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class OrderMapper extends Mapper<LongWritable,Text,OrderBean,NullWritable> {
    OrderBean order = new OrderBean();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] feilds = value.toString().split("\t");
        order.setOrder_id(Integer.parseInt(feilds[0]));
        order.setPrice(Double.parseDouble(feilds[2]));
        context.write(order, NullWritable.get());
    }
}
