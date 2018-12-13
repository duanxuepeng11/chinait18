package com.qianfeng.domain;

import java.io.Serializable;

//好友表
public class Good implements Serializable,Comparable<Good> {
    private int gid;
    private int uid_my;
    private int uid_you;
    private int love;
    private int did;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getUid_my() {
        return uid_my;
    }

    public void setUid_my(int uid_my) {
        this.uid_my = uid_my;
    }

    public int getUid_you() {
        return uid_you;
    }

    public void setUid_you(int uid_you) {
        this.uid_you = uid_you;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    @Override
    public int compareTo(Good o) {
        if(this.love > o.love){
            return -1;
        }else if(this.love < o.love){
            return 1;
        }else{
            return 0;
        }
    }
}
