package cn.software_engineering.jkbdbyempress.biz;

import java.util.List;

import cn.software_engineering.jkbdbyempress.ExamApplication;
import cn.software_engineering.jkbdbyempress.bean.Quetion;
import cn.software_engineering.jkbdbyempress.dao.ExamDao;
import cn.software_engineering.jkbdbyempress.dao.IExamDao;

/**
 * Created by Asus on 2017/7/3.
 */

public class ExamBiz implements IExamBiz{
    IExamDao dao;
    int quetionIndex=0;
    List<Quetion> examlist=null;

    public ExamBiz() {
        this.dao = new ExamDao();
    }

    @Override
    public void biginExam() {
        quetionIndex=0;
        dao.loadExamInfo();
        dao.loadQuetionLists();
    }

    @Override
    public Quetion getNowQuetion() {
        examlist=ExamApplication.getInstance().getMquetions();
        if(examlist!=null){
            return examlist.get(quetionIndex);
        }else{
            return null;
        }
    }

    @Override
    public Quetion nextQuetion() {
        if(examlist!=null && quetionIndex<examlist.size()-1){
            quetionIndex++;
            return examlist.get(quetionIndex);
        }else{
            return null;
        }
    }

    @Override
    public Quetion preQuetion() {
        if(examlist!=null && quetionIndex>0){
            quetionIndex--;
            return examlist.get(quetionIndex);
        }else{
            return null;
        }
    }

    @Override
    public int commitExam() {
        int s=0;
        for (Quetion quetion : examlist) {
            String userAnswer=quetion.getUserAnswer();
            if(userAnswer!=null && !userAnswer.equals("")){
                if(quetion.getAnswer().equals(userAnswer)){
                    s++;
                }
            }
        }
        return s;

    }

    @Override
    public String getQuetionIndex() {
        return (quetionIndex+1)+".";
    }
}
