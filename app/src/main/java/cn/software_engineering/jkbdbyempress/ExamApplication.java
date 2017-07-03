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
    Examine mexamine;
    List<Quetion> mquetions;
    private static ExamApplication instance;
    IExamBiz biz;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        biz=new ExamBiz();
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
       new Thread(new Runnable() {
            @Override
            public void run() {
                biz.biginExam();
               /*  OkHttpUtils<Examine> okHttpUtils=new OkHttpUtils<Examine>(instance);
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

                OkHttpUtils<String> utils1=new OkHttpUtils<>(instance);
                String url2="http://101.251.196.90:8080/JztkServer/getQuestions?testType=rand";
                utils1.url(url2).targetClass(String.class).execute(new OkHttpUtils.OnCompleteListener<String>() {
                    @Override
                    public void onSuccess(String jsonStr) {
                        All listResultFromJson = ResultUtils.getListResultFromJson(jsonStr);
                        if(listResultFromJson!=null && listResultFromJson.getError_code()==0){
                            List<Quetion> list=listResultFromJson.getResult();
                            if(list!=null && list.size()>0){
                                mquetions=list;
                                Log.e("hei","chenggongle:"+mquetions);
                            }
                        }

                    }

                    @Override
                    public void onError(String error) {
                        Log.e("main","error：0000"+error);

                    }
                });*/
            }
        }).start();

    }
}
