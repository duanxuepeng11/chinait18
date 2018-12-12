package com.qianfeng.domain;

import java.io.Serializable;

//通话对象
public class Contact implements Serializable {
    private int cid;
    private int uid;
    private int did;
    private double callsum;
    private double callcount;
    private int statue;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

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

    public int getStatue() {
        return statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }
}
