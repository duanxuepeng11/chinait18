package com.qianfeng.domain;

import java.io.Serializable;

/**
 * 封装返回到 柱形图的数据，3个字段对应图上3个数据，
 * 遍历的时候第一个数据，遍历主打，第二个数据，遍历被打，第三个数据，遍历总次数
 */
public class ZTable implements Serializable {

    private double zhudaNum ;//主打的数量
    private double beidaNum; //被打的数量
    private double Count; //总次数

    public double getZhudaNum() {
        return zhudaNum;
    }

    public void setZhudaNum(double zhudaNum) {
        this.zhudaNum = zhudaNum;
    }

    public double getBeidaNum() {
        return beidaNum;
    }

    public void setBeidaNum(double beidaNum) {
        this.beidaNum = beidaNum;
    }

    public double getCount() {
        return Count;
    }

    public void setCount(double count) {
        Count = count;
    }
}
