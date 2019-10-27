package com.clay.keyAndNLine;

import com.clay.mapreduce01.WordcountDriver;
import com.clay.mapreduce01.WordcountMapper;
import com.clay.mapreduce01.WordcountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class keyAndNLineDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        // 1 获取配置信息以及封装任务
        Configuration configuration = new Configuration();

        // 1.1设置切割符
//        configuration.set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR, " ");


        Job job = Job.getInstance(configuration);


        // 2 设置jar加载路径
        job.setJarByClass(WordcountDriver.class);

        //
        // 如果不设置InputFormat，它默认用的是TextInputFormat.class、

        //2.1小文件使用CombineTextInputFormat类来处理
        //job.setInputFormatClass(CombineTextInputFormat.class);
        //虚拟存储切片最大值设置4m
        //CombineTextInputFormat.setMaxInputSplitSize(job, 4194304);

        //2.2使用keyvalue
//        job.setInputFormatClass(KeyValueTextInputFormat.class);
        NLineInputFormat.setNumLinesPerSplit(job,3);
        job.setInputFormatClass(NLineInputFormat.class);


        // 3 设置map和reduce类
        job.setMapperClass(keyAndNLineMapper.class);
        job.setReducerClass(keyAndNlineReducer.class);

        // 4 设置map输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 5 设置最终输出kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 6 设置输入和输出路径
        FileInputFormat.setInputPaths(job,new Path("E:\\input\\keyValue"));
        FileOutputFormat.setOutputPath(job, new Path("E:\\output\\keyValue"));

        // 7 提交
        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : 1);
    }
}
