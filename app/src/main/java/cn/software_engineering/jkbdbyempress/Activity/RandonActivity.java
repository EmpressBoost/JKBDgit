package cn.software_engineering.jkbdbyempress.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import cn.software_engineering.jkbdbyempress.R;
import cn.software_engineering.jkbdbyempress.bean.Examine;

/**
 * Created by Asus on 2017/6/29.
 */

public class RandonActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        Intent intent = this.getIntent();
        TextView textView=(TextView)findViewById(R.id.examineindo);
        if(intent!=null)
        {
            Examine examine=(Examine)intent.getSerializableExtra("key");
            textView.setText(examine.toString());
        }
        else
        {
            textView.setText("error");
        }


        //Examine exainfo=(Examine)intent.getSerializableExtra("key");

        //textView.setText(exainfo.toString());
    }
}
