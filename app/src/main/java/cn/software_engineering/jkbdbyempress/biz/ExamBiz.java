package cn.software_engineering.jkbdbyempress.biz;

import cn.software_engineering.jkbdbyempress.dao.ExamDao;
import cn.software_engineering.jkbdbyempress.dao.IExamDao;

/**
 * Created by Asus on 2017/7/3.
 */

public class ExamBiz implements IExamBiz{
    IExamDao dao;

    public ExamBiz() {
        this.dao = new ExamDao();
    }

    @Override
    public void biginExam() {
       // loadExamInfo();
        dao.loadExamInfo();
        dao.loadQuetionLists();

    }

    @Override
    public void nextQuetion() {

    }

    @Override
    public void preQuetion() {

    }

    @Override
    public void commitExam() {

    }
}
