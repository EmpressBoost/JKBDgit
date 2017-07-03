package cn.software_engineering.jkbdbyempress.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.software_engineering.jkbdbyempress.ExamApplication;
import cn.software_engineering.jkbdbyempress.R;
import cn.software_engineering.jkbdbyempress.bean.Examine;
import cn.software_engineering.jkbdbyempress.bean.Quetion;
import cn.software_engineering.jkbdbyempress.biz.ExamBiz;
import cn.software_engineering.jkbdbyempress.biz.IExamBiz;

/**
 * Created by Asus on 2017/6/29.
 */

public class RandonActivity extends AppCompatActivity {
    TextView exminfo,title,intem1,intem2,intem3,intem4,tvload;
    ImageView quetion_img;
    LinearLayout layoutLoading;
    ProgressBar loaddialog;
    boolean isLoadExamInfoReceiver=false;
    boolean isLoadExamQuetionReceiver=false;
    boolean isLoadExamInfo=false;
    boolean isLoadExamQuetion=false;
    IExamBiz biz;
    LoadExamBroadcast loadExamBroadcast;
    LoadQuetionBroadcast loadQuetionBroadcast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);


        //Intent intent = this.getIntent();
        exminfo=(TextView)findViewById(R.id.examineindo);
        title=(TextView)findViewById(R.id.tv_exmtitle);
        intem1=(TextView)findViewById(R.id.tv_intem1);
        intem2=(TextView)findViewById(R.id.tv_intem2);
        intem3=(TextView)findViewById(R.id.tv_intem3);
        intem4=(TextView)findViewById(R.id.tv_intem4);
        tvload= (TextView) findViewById(R.id.tv_load);
        loaddialog= (ProgressBar) findViewById(R.id.load_dialog);
        quetion_img= (ImageView) findViewById(R.id.image_1);
        layoutLoading= (LinearLayout) findViewById(R.id.layout_loading);
        layoutLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();

            }
        });
        loadExamBroadcast=new LoadExamBroadcast();
        loadQuetionBroadcast=new LoadQuetionBroadcast();
        setListener();
        biz=new ExamBiz();
        loadData();
    }

    private void setListener() {
        registerReceiver(loadExamBroadcast,new IntentFilter(ExamApplication.LOAD_EXAM_INFO));
        registerReceiver(loadQuetionBroadcast,new IntentFilter(ExamApplication.LOAD_EXAM_QUESTION));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(loadExamBroadcast!=null){
            unregisterReceiver(loadExamBroadcast);
        }
        if(loadQuetionBroadcast!=null){
            unregisterReceiver(loadQuetionBroadcast);
        }
    }

    private void loadData() {
        layoutLoading.setEnabled(false);
        loaddialog.setVisibility(View.VISIBLE);
        tvload.setText("下载中……");
        new Thread(new Runnable() {
            @Override
            public void run() {
                biz.biginExam();
            }
        }).start();
    }

    private void initData() {
        if(isLoadExamInfoReceiver && isLoadExamQuetionReceiver){
            if(isLoadExamInfo==true && isLoadExamQuetion==true){
                layoutLoading.setVisibility(View.GONE);
                Examine mexamine = ExamApplication.getInstance().getMexamine();
                if(mexamine!=null){
                    showData(mexamine);
                }
                List<Quetion> quetion=ExamApplication.getInstance().getMquetions();
                if(quetion!=null){
                    showQuetion(quetion);
                }
            }
        }else {
            layoutLoading.setEnabled(true);
            loaddialog.setVisibility(View.GONE);
            tvload.setText("下载失败，点击重新下载");
        }

    }

    private void showQuetion(List<Quetion> quetion) {
        Quetion mquetion=quetion.get(0);
        if(mquetion!=null)
        {
            title.setText(mquetion.getQuestion());
            intem1.setText(mquetion.getItem1());
            intem2.setText(mquetion.getItem2());
            intem3.setText(mquetion.getItem3());
            intem4.setText(mquetion.getItem4());
            Picasso.with(RandonActivity.this).load(mquetion.getUrl()).into(quetion_img);
        }
    }

    private void showData(Examine mexamine) {
        exminfo.setText(mexamine.toString());
    }
    class LoadExamBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean issuccess=intent.getBooleanExtra(ExamApplication.LOAD_DATA_SUCCESS,false);
            Log.e("LoadExamBroadcast","LoadExamBroadcast,issuccess:"+issuccess);
            if(issuccess)
            {
                isLoadExamInfo=true;
            }
            isLoadExamInfoReceiver=true;
            initData();
        }
    }
    class LoadQuetionBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean issuccess=intent.getBooleanExtra(ExamApplication.LOAD_DATA_SUCCESS,false);
            Log.e("LoadQuetionBroadcast","LoadQuetionBroadcast,issuccess:"+issuccess);
            if(issuccess)
            {
                isLoadExamQuetion=true;
            }
            isLoadExamQuetionReceiver=true;
            initData();
        }
    }

}
