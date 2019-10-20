package com.clay.hdfs;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsClient {
    private Configuration configuration ;
    private FileSystem fs ;

    @Before
    public void befroe() throws Exception {
        configuration = new Configuration();
        fs = FileSystem.get(new URI("hdfs://hadoop50g102:9000"), configuration, "clay");
    }

    @After
    public void after() throws Exception {
        fs.close();
        System.out.println("over");
    }

    @Test
    public void testMkdirs() throws IOException, InterruptedException,URISyntaxException {

        // 1 获取文件系统
        //Configuration configuration = new Configuration();
        // 配置在集群上运行
        // configuration.set("fs.defaultFS", "hdfs://hadoop102:9000");
        // FileSystem fs = FileSystem.get(configuration);
       // FileSystem fs = FileSystem.get(new URI("hdfs://hadoop50g102:9000"), configuration, "clay");

        // 2 创建目录
        fs.mkdirs(new Path("/1108/daxian/banzhang"));

        // 3 关闭资源
        fs.close();

    }

    @Test
    public void testCopyFromLocalFile() throws IOException, InterruptedException, URISyntaxException {

        // 1 获取文件系统
        //Configuration configuration = new Configuration();
        configuration.set("dfs.replication", "2");
        //FileSystem fs = FileSystem.get(new URI("hdfs://hadoop50g102:9000"), configuration, "clay");

        // 2 上传文件
        fs.copyFromLocalFile(new Path("e:/数据分析-ALL.xlsx"), new Path("/banzhang.xlsx"));

        // 3 关闭资源
        //fs.close();


    }

    @Test
    public void testCopyToLocalFile() throws IOException, InterruptedException, URISyntaxException{

        // 1 获取文件系统
       // Configuration configuration = new Configuration();
       // FileSystem fs = FileSystem.get(new URI("hdfs://hadoop50g102:9000"), configuration, "clay");

        // 2 执行下载操作
        // boolean delSrc 指是否将原文件删除
        // Path src 指要下载的文件路径
        // Path dst 指将文件下载到的路径
        // boolean useRawLocalFileSystem 是否开启文件校验
        fs.copyToLocalFile(false, new Path("/1.txt"), new Path("e:/banhua.txt"), true);

        // 3 关闭资源
        //fs.close();
    }

    @Test
    public void testdup() throws IOException, InterruptedException, URISyntaxException{

        // 1 获取文件系统
        // Configuration configuration = new Configuration();
        // FileSystem fs = FileSystem.get(new URI("hdfs://hadoop50g102:9000"), configuration, "clay");

        // 2 执行下载操作
        // boolean delSrc 指是否将原文件删除
        // Path src 指要下载的文件路径
        // Path dst 指将文件下载到的路径
        // boolean useRawLocalFileSystem 是否开启文件校验
        FileInputStream fis = new FileInputStream(new File("e:/1.txt"));
        FSDataOutputStream fos = fs.append(new Path("/1.txt"));
        IOUtils.copyBytes(fis,fos,configuration);


        // 3 关闭资源
        //fs.close();
    }


}
