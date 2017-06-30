package cn.software_engineering.jkbdbyempress.bean;

import java.io.Serializable;

/**
 * Created by Asus on 2017/6/29.
 */

public class Examine implements Serializable{
    /**
     * subjectTitle : c1
     * uid : 1
     * limitTime : 10
     * questionCount : 100
     */

    private String subjectTitle;
    private int uid;
    private int limitTime;
    private int questionCount;

    public final String getSubjectTitle() {
        return subjectTitle;
    }

    public final void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public final int getUid() {
        return uid;
    }

    public final void setUid(int uid) {
        this.uid = uid;
    }

    public final int getLimitTime() {
        return limitTime;
    }

    public final void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }

    public final int getQuestionCount() {
        return questionCount;
    }

    @Override
    public final String toString() {
        return "考试科目：" + subjectTitle +
                "\n考题数量：" + questionCount +
                "\n考试时间：" + limitTime+"分钟";
    }

    public final void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }
}
