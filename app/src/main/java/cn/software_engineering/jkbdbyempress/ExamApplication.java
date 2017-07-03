package cn.software_engineering.jkbdbyempress;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import cn.software_engineering.jkbdbyempress.Activity.MainActivity;
import cn.software_engineering.jkbdbyempress.Activity.RandonActivity;
import cn.software_engineering.jkbdbyempress.bean.All;
import cn.software_engineering.jkbdbyempress.bean.Examine;
import cn.software_engineering.jkbdbyempress.bean.Quetion;
import cn.software_engineering.jkbdbyempress.biz.ExamBiz;
import cn.software_engineering.jkbdbyempress.biz.IExamBiz;
import cn.software_engineering.jkbdbyempress.utils.OkHttpUtils;
import cn.software_engineering.jkbdbyempress.utils.ResultUtils;

/**
 * Created by Asus on 2017/6/30.
 */

public class ExamApplication extends Application{
    public static String LOAD_EXAM_INFO="load_exam_info";
    public static String LOAD_EXAM_QUESTION="load_exam_question";
    public static String LOAD_DATA_SUCCESS="load_data_success";
    Examine mexamine;
    List<Quetion> mquetions;
    private static ExamApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
    public static  ExamApplication getInstance(){
        return  instance;
    }

    public Examine getMexamine() {
        return mexamine;
    }

    public void setMexamine(Examine mexamine) {
        this.mexamine = mexamine;
    }

    public List<Quetion> getMquetions() {
        return mquetions;
    }

    public void setMquetions(List<Quetion> mquetions) {
        this.mquetions = mquetions;
    }


}
