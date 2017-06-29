package cn.software_engineering.jkbdbyempress.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import cn.software_engineering.jkbdbyempress.R;
import cn.software_engineering.jkbdbyempress.bean.Examine;
import cn.software_engineering.jkbdbyempress.utils.OkHttpUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void randonExame(View view) {
        OkHttpUtils<Examine> okHttpUtils=new OkHttpUtils<Examine>(getApplication());
        String murl="http://101.251.196.90:8080/JztkServer/examInfo";
        okHttpUtils.url(murl).targetClass(Examine.class).execute(new OkHttpUtils.OnCompleteListener<Examine>() {
            @Override
            public void onSuccess(Examine result) {
                Log.e("main","result:"+result.toString());
            }

            @Override
            public void onError(String error) {
                Log.e("main","error"+error);

            }
        });
        startActivity(new Intent(MainActivity.this,RandonActivity.class));
    }

    public void allExame(View view) {
    }

    public void setting(View view) {
    }

    public void exit(View view) {
        finish();
    }
}
