package com.qianfeng.domain;

public class ErrorXZ {

    private String question_id;
    private double error_rate;
    private double diff_error;
    private String question_difficulty;


    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public double getError_rate() {
        return error_rate;
    }

    public void setError_rate(double error_rate) {
        this.error_rate = error_rate;
    }

    public double getDiff_error() {
        return diff_error;
    }

    public void setDiff_error(double diff_error) {
        this.diff_error = diff_error;
    }

    public String getQuestion_difficulty() {
        return question_difficulty;
    }

    public void setQuestion_difficulty(String question_difficulty) {
        this.question_difficulty = question_difficulty;
    }


    @Override
    public String toString() {
        return "ErrorXZ{" +
                "question_id='" + question_id + '\'' +
                ", error_rate=" + error_rate +
                ", diff_error=" + diff_error +
                ", question_difficulty='" + question_difficulty + '\'' +
                '}';
    }
}
