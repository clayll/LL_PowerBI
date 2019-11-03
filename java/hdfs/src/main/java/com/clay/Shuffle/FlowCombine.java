package com.clay.Shuffle;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCombine extends Reducer<Text, FlowBean, FlowBean,NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {

        FlowBean f = new FlowBean();
        for(FlowBean value: values) {
            f.setDownFlow( f.getDownFlow()+ value.getDownFlow());
            f.setDownFlow( f.getUpFlow()+ value.getUpFlow());
            f.setDownFlow( f.getSumFlow()+ value.getSumFlow());
        }

        context.write( f,  NullWritable.get());
    }
}
