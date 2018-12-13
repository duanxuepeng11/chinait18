package com.qianfeng.domain;

import java.io.Serializable;

//前端显示 封装一个图形显示的对象
public class GoodBean implements Serializable {
    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
