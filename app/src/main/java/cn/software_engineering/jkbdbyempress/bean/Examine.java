package cn.software_engineering.jkbdbyempress.bean;

/**
 * Created by Asus on 2017/6/29.
 */

public class Examine {
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

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    @Override
    public String toString() {
        return "Examine{" +
                "考试科目：'" + subjectTitle +'\''+
                "\n考题数量：" + questionCount +
                "\n考试时间" + limitTime +
                '}';
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }
}
