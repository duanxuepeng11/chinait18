package com.qianfeng.domain;

/**
 * 用来表示每场考试的，每题的错误率
 */
public class Error_Rate {
    private String question_id;     // 问题id
    private String category_name;   // 考试类型
    private String error_rate;      // 错误率

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getError_rate() {
        return error_rate;
    }

    public void setError_rate(String error_rate) {
        this.error_rate = error_rate;
    }
}
