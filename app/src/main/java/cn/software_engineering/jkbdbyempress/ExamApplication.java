package cn.software_engineering.jkbdbyempress;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import cn.software_engineering.jkbdbyempress.Activity.MainActivity;
import cn.software_engineering.jkbdbyempress.Activity.RandonActivity;
import cn.software_engineering.jkbdbyempress.bean.Examine;
import cn.software_engineering.jkbdbyempress.bean.Quetion;
import cn.software_engineering.jkbdbyempress.utils.OkHttpUtils;

/**
 * Created by Asus on 2017/6/30.
 */

public class ExamApplication extends Application{
    Examine mexamine;
    List<Quetion> mquetions;
    private static ExamApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        initData();
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

    private void initData() {
        OkHttpUtils<Examine> okHttpUtils=new OkHttpUtils<Examine>(instance);
        String murl="http://101.251.196.90:8080/JztkServer/examInfo";
        okHttpUtils.url(murl).targetClass(Examine.class).execute(new OkHttpUtils.OnCompleteListener<Examine>() {
            @Override
            public void onSuccess(Examine result) {
                Log.e("main","result:"+result);
                mexamine=result;
            }
            @Override
            public void onError(String error) {
                Log.e("main","error:"+error);

            }
        });
    }
}
