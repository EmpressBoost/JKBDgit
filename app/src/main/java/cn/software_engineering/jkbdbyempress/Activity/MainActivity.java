package cn.software_engineering.jkbdbyempress.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.software_engineering.jkbdbyempress.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void randonExame(View view) {
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
