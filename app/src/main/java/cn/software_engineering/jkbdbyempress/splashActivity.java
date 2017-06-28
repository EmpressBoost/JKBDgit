package cn.software_engineering.jkbdbyempress;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.software_engineering.jkbdbyempress.R;

/**
 * Created by Administrator on 2017/6/28.
 */

public class splashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Intent intent=new Intent(splashActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
