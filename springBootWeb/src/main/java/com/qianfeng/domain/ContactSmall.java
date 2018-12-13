package com.qianfeng.domain;

import java.io.Serializable;

public class ContactSmall implements Serializable {
    private double callsum;
    private double callcount;

    public double getCallsum() {
        return callsum;
    }

    public void setCallsum(double callsum) {
        this.callsum = callsum;
    }

    public double getCallcount() {
        return callcount;
    }

    public void setCallcount(double callcount) {
        this.callcount = callcount;
    }
}
