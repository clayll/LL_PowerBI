package com.clay.Shuffle;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

// 如果要排序，则继承可排序的接口Writable
public class FlowBean implements WritableComparable<FlowBean> {
    private long upFlow;
    private long downFlow;
    private long sumFlow;

    //2  反序列化时，需要反射调用空参构造函数，所以必须有
    public FlowBean(long downFlow, long upFlow) {
        super();
        this.sumFlow = downFlow+upFlow;
        this.downFlow = downFlow;
        this.upFlow = upFlow;
    }

    public FlowBean(){
        super();
    }


    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    public void set(long downFlow,long upFlow){
        this.sumFlow = downFlow+upFlow;
        this.downFlow = downFlow;
        this.upFlow = upFlow;

    }


    //3  写序列化方法
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    //4 反序列化方法
    //5 反序列化方法读顺序必须和写序列化方法的写顺序必须一致
    public void readFields(DataInput in) throws IOException {
        this.upFlow  = in.readLong();
        this.downFlow = in.readLong();
        this.sumFlow = in.readLong();
    }

    // 6 编写toString方法，方便后续打印到文本
    @Override
    public String toString() {
        return  "\t"+getSumFlow();
    }


    public int compareTo(FlowBean o) {
         long thisValue = this.sumFlow;
         long thatValue = o.sumFlow;
         return (thisValue > thatValue ? -1 : (thisValue==thatValue ? 0 : 1));
        }
}
