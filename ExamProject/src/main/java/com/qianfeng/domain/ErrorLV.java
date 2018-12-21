package com.qianfeng.domain;

public class ErrorLV {

    private String question_id; // 题目id
    private String error_rate; // 错误率
    private String rank; // 排名

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getError_rate() {
        return error_rate;
    }

    public void setError_rate(String error_rate) {
        this.error_rate = error_rate;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
