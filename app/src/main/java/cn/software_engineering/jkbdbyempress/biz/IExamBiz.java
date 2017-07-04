package cn.software_engineering.jkbdbyempress.biz;

import cn.software_engineering.jkbdbyempress.bean.Quetion;

/**
 * Created by Asus on 2017/7/3.
 */

public interface IExamBiz {
    void biginExam();
    Quetion getNowQuetion();
    Quetion nextQuetion();
    Quetion preQuetion();
    int commitExam();
    String getQuetionIndex();
}
