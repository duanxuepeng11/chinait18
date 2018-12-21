package com.qianfeng.domain;

public class WaveStudent {

    private String exam_id; // 考试id
    private String start_time; // 考试时间
    private String class_name; // 班级
    private String examinee_num; // 学号
    private String examinee_name; // 姓名
    private String objective_mark; // 客观题
    private String subjective_mark; // 主观题
    private String total_mark; // 总分

    public String getExam_id() {
        return exam_id;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

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

    public String getObjective_mark() {
        return objective_mark;
    }

    public void setObjective_mark(String objective_mark) {
        this.objective_mark = objective_mark;
    }

    public String getSubjective_mark() {
        return subjective_mark;
    }

    public void setSubjective_mark(String subjective_mark) {
        this.subjective_mark = subjective_mark;
    }

    public String getTotal_mark() {
        return total_mark;
    }

    public void setTotal_mark(String total_mark) {
        this.total_mark = total_mark;
    }

    @Override
    public String toString() {
        return "WaveStudent{" +
                "exam_id='" + exam_id + '\'' +
                ", start_time='" + start_time + '\'' +
                ", class_name='" + class_name + '\'' +
                ", examinee_num='" + examinee_num + '\'' +
                ", examinee_name='" + examinee_name + '\'' +
                ", objective_mark='" + objective_mark + '\'' +
                ", subjective_mark='" + subjective_mark + '\'' +
                ", total_mark='" + total_mark + '\'' +
                '}';
    }
}
