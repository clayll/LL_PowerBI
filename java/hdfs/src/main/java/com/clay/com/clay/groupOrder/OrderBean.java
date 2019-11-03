package com.clay.com.clay.groupOrder;


import org.apache.hadoop.io.WritableComparable;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements  WritableComparable<OrderBean> {


    public OrderBean(){
        super();
    }
    public OrderBean(int order_id, double price) {
        super();
        this.order_id = order_id;
        this.price = price;
    }


    private int order_id; // 订单id号

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price; // 价格

    public int getOrder_id() {
        return order_id;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return order_id + "\t" + price;
    }


    public int compareTo(OrderBean o) {
        System.out.println("本id；"+order_id+"  对比id:"+o.order_id);
        if(order_id > o.order_id){
            return 1;
        }
        else if(order_id < o.order_id){
            return -1;
        }
        else{

            return Double.compare(o.price,price);
        }

    }

    public void write(DataOutput out) throws IOException {
        out.writeInt(this.order_id);
        out.writeDouble(this.price);
    }

    public void readFields(DataInput in) throws IOException {
        order_id = in.readInt();
        price = in.readDouble();
    }
}
