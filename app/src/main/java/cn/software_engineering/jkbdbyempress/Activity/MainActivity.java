package cn.software_engineering.jkbdbyempress.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.software_engineering.jkbdbyempress.R;
import cn.software_engineering.jkbdbyempress.bean.All;
import cn.software_engineering.jkbdbyempress.bean.Examine;
import cn.software_engineering.jkbdbyempress.bean.Quetion;
import cn.software_engineering.jkbdbyempress.utils.OkHttpUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void randonExame(View view) {
        startActivity(new Intent(MainActivity.this,RandonActivity.class));

        /*Examine examine=new Examine();
        Quetion quetion=new Quetion();
        OkHttpUtils<Examine> okHttpUtils=new OkHttpUtils<Examine>(getApplication());
        String murl="http://101.251.196.90:8080/JztkServer/examInfo";
        okHttpUtils.url(murl).targetClass(Examine.class).execute(new OkHttpUtils.OnCompleteListener<Examine>() {
            @Override
            public void onSuccess(final Examine result) {
               Log.e("main","result:"+result.toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(MainActivity.this,RandonActivity.class);

                        intent.putExtra("key",result);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onError(String error) {
                Log.e("main","error"+error);

            }
        });
        OkHttpUtils<All> utils=new OkHttpUtils<All>(getApplication());
        murl="http://101.251.196.90:8080/JztkServer/getQuestions?testType=rand";
        utils.url(murl).targetClass(All.class).execute(new OkHttpUtils.OnCompleteListener<All>() {
            @Override
            public void onSuccess(All result) {
                Log.e("key","Quetion:"+result.getResult().toString());
               ArrayList<Quetion> list = (ArrayList<Quetion>) result.getResult();
                Log.e("ses","list.size()="+list.size());
               // intent.putExtra("result",list);
                //intent1.putExtra("result",list);
            }

            @Override
            public void onError(String error) {
                Log.e("key","you are wrong");

            }
        });*/
    }

    public void allExame(View view) {
    }

    public void setting(View view) {
    }

    public void exit(View view) {
        finish();
    }
}
