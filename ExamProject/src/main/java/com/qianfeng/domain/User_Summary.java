package com.qianfeng.domain;

public class User_Summary {
    private String exam_id;                 // 考试id
    private String start_time;              // 考试开始时间
    private String class_name;              // 班级
    private String category_name;           // 考试阶段名称
    private String question_difficulty;     // 试题难度
    private String examinee_num;            // 学号
    private String score_avg;               // 正确率
    private String examinee_name;          // 字段为空，需要多表关联。

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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getQuestion_difficulty() {
        return question_difficulty;
    }

    public void setQuestion_difficulty(String question_difficulty) {
        this.question_difficulty = question_difficulty;
    }

    public String getExaminee_num() {
        return examinee_num;
    }

    public void setExaminee_num(String examinee_num) {
        this.examinee_num = examinee_num;
    }

    public String getScore_avg() {
        return score_avg;
    }

    public void setScore_avg(String score_avg) {
        this.score_avg = score_avg;
    }

    public String getExaminee_name() {
        return examinee_name;
    }

    public void setExaminee_name(String examinee_name) {
        this.examinee_name = examinee_name;
    }

}
