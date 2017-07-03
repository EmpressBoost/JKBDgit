package cn.software_engineering.jkbdbyempress.dao;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import cn.software_engineering.jkbdbyempress.ExamApplication;
import cn.software_engineering.jkbdbyempress.bean.All;
import cn.software_engineering.jkbdbyempress.bean.Examine;
import cn.software_engineering.jkbdbyempress.bean.Quetion;
import cn.software_engineering.jkbdbyempress.utils.OkHttpUtils;
import cn.software_engineering.jkbdbyempress.utils.ResultUtils;

/**
 * Created by Asus on 2017/7/3.
 */

public class ExamDao implements IExamDao{
    @Override
    public void loadExamInfo() {
        OkHttpUtils<Examine> okHttpUtils=new OkHttpUtils<Examine>(ExamApplication.getInstance());
        String murl="http://101.251.196.90:8080/JztkServer/examInfo";
        okHttpUtils.url(murl).targetClass(Examine.class).execute(new OkHttpUtils.OnCompleteListener<Examine>() {
            @Override
            public void onSuccess(Examine result) {
                Log.e("main","result:"+result);
                ExamApplication.getInstance().setMexamine(result);
                ExamApplication.getInstance()
                        .sendBroadcast(new Intent(ExamApplication.LOAD_EXAM_INFO)
                        .putExtra(ExamApplication.LOAD_DATA_SUCCESS,true));
            }
            @Override
            public void onError(String error) {
                ExamApplication.getInstance()
                        .sendBroadcast(new Intent(ExamApplication.LOAD_EXAM_INFO)
                                .putExtra(ExamApplication.LOAD_DATA_SUCCESS,false));
                Log.e("main","error:"+error);
            }
        });
    }

    @Override
    public void loadQuetionLists() {
        OkHttpUtils<String> utils1=new OkHttpUtils<>(ExamApplication.getInstance());
        String url2="http://101.251.196.90:8080/JztkServer/getQuestions?testType=rand";
        utils1.url(url2).targetClass(String.class).execute(new OkHttpUtils.OnCompleteListener<String>() {
            @Override
            public void onSuccess(String jsonStr) {
                boolean success=false;
                All listResultFromJson = ResultUtils.getListResultFromJson(jsonStr);
                if(listResultFromJson!=null && listResultFromJson.getError_code()==0){
                    List<Quetion> list=listResultFromJson.getResult();
                    if(list!=null && list.size()>0){
                        ExamApplication.getInstance().setMquetions(list);
                        success=true;
                        Log.e("hei","chenggongle:"+list);
                    }
                    ExamApplication.getInstance()
                            .sendBroadcast(new Intent(ExamApplication.LOAD_EXAM_QUESTION)
                                    .putExtra(ExamApplication.LOAD_DATA_SUCCESS,success));
                }

            }

            @Override
            public void onError(String error) {
                ExamApplication.getInstance()
                        .sendBroadcast(new Intent(ExamApplication.LOAD_EXAM_INFO)
                                .putExtra(ExamApplication.LOAD_DATA_SUCCESS,false));
                Log.e("main","error：0000"+error);

            }
        });
    }

}
