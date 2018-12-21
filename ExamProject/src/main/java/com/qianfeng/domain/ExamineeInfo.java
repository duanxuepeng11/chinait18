package com.qianfeng.domain;
// 因为user_summary表中没有学生姓名字段，所以这边做了一个关联
public class ExamineeInfo {
    private String examinee_num;
    private String examinee_name;

    public String getExaminee_num() {
        return examinee_num;
    }

    public void setExaminee_num(String examinee_num) {
        this.examinee_num = examinee_num;
    }

    public String getExaminee_name() {
        return examinee_name;
    }

    public void setExaminee_name(String examinee_name) {
        this.examinee_name = examinee_name;
    }
}
