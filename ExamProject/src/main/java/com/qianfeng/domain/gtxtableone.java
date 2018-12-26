package com.qianfeng.domain;

import java.io.Serializable;

public class gtxtableone implements Serializable {
    private int exam_id;
    private String start_time;
    private String calss_name;
    private String examinee_num;
    private String examinee_name;
    private String category_name;
    private String question_name;
    private double score_avg;

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getCalss_name() {
        return calss_name;
    }

    public void setCalss_name(String calss_name) {
        this.calss_name = calss_name;
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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getQuestion_name() {
        return question_name;
    }

    public void setQuestion_name(String question_name) {
        this.question_name = question_name;
    }

    public double getScore_avg() {
        return score_avg;
    }

    public void setScore_avg(double score_avg) {
        this.score_avg = score_avg;
    }

    @Override
    public String toString() {
        return "qtxtableone{" +
                "exam_id=" + exam_id +
                ", start_time='" + start_time + '\'' +
                ", calss_name='" + calss_name + '\'' +
                ", examinee_num='" + examinee_num + '\'' +
                ", examinee_name='" + examinee_name + '\'' +
                ", category_name='" + category_name + '\'' +
                ", question_name='" + question_name + '\'' +
                ", score_avg=" + score_avg +
                '}';
    }
}
