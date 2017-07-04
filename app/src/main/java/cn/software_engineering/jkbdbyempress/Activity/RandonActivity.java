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
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    TextView exminfo,title,intem1,intem2,intem3,intem4,tvload,tvno;
    ImageView quetion_img;
    LinearLayout layoutLoading,layout03,layout04;
    ProgressBar loaddialog;
    CheckBox cb_01,cb_02,cb_03,cb_04;
    CheckBox[] cbs;
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
        tvno= (TextView) findViewById(R.id.tv_exam_no);
        loaddialog= (ProgressBar) findViewById(R.id.load_dialog);
        quetion_img= (ImageView) findViewById(R.id.image_1);
        layoutLoading= (LinearLayout) findViewById(R.id.layout_loading);
        layout03= (LinearLayout) findViewById(R.id.layout_03);
        layout04= (LinearLayout) findViewById(R.id.layout_04);
        cb_01= (CheckBox) findViewById(R.id.cb_01);
        cb_02= (CheckBox) findViewById(R.id.cb_02);
        cb_03= (CheckBox) findViewById(R.id.cb_03);
        cb_04= (CheckBox) findViewById(R.id.cb_04);
        cbs=new CheckBox[4];
        cbs[0]=cb_01;
        cbs[1]=cb_02;
        cbs[2]=cb_03;
        cbs[3]=cb_04;
        cb_01.setOnCheckedChangeListener(listener);
        cb_02.setOnCheckedChangeListener(listener);
        cb_03.setOnCheckedChangeListener(listener);
        cb_04.setOnCheckedChangeListener(listener);
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
    CompoundButton.OnCheckedChangeListener listener=new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if(isChecked){
                int userAnswer=0;
                switch (compoundButton.getId()){
                    case R.id.cb_01:
                        userAnswer=1;
                        break;
                    case R.id.cb_02:
                        userAnswer=2;
                        break;
                    case R.id.cb_03:
                        userAnswer=3;
                        break;
                    case R.id.cb_04:
                        userAnswer=4;
                        break;
                }
                Log.e("CheckedChanged","user"+userAnswer+",isChecked"+isChecked);
                if(userAnswer>0){
                    for (CheckBox cb:cbs){
                        cb.setChecked(false);
                    }
                    cbs[userAnswer-1].setChecked(true);
                }
            }
        }
    };
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

                showQuetion(biz.getNowQuetion());
            }
        }else {
            layoutLoading.setEnabled(true);
            loaddialog.setVisibility(View.GONE);
            tvload.setText("下载失败，点击重新下载");
        }

    }

    private void showQuetion(Quetion mquetion) {
        Log.e("showQuetion","showQuetion,mquetion"+mquetion);
        if(mquetion!=null)
        {
            tvno.setText(biz.getQuetionIndex());
            title.setText(mquetion.getQuestion());
            intem1.setText(mquetion.getItem1());
            intem2.setText(mquetion.getItem2());
            intem3.setText(mquetion.getItem3());
            intem4.setText(mquetion.getItem4());
            layout03.setVisibility(mquetion.getItem3().equals("")?View.GONE:View.VISIBLE);
            cb_03.setVisibility(mquetion.getItem3().equals("")?View.GONE:View.VISIBLE);
            layout04.setVisibility(mquetion.getItem4().equals("")?View.GONE:View.VISIBLE);
            cb_04.setVisibility(mquetion.getItem4().equals("")?View.GONE:View.VISIBLE);
            if(mquetion.getUrl()!=null && !mquetion.getUrl().equals("")){
                quetion_img.setVisibility(View.VISIBLE);
                Picasso.with(RandonActivity.this).load(mquetion.getUrl()).into(quetion_img);
            }else {
                quetion_img.setVisibility(View.GONE);
            }
        }
    }

    private void showData(Examine mexamine) {
        exminfo.setText(mexamine.toString());
    }

    public void preQuetion(View view) {
        showQuetion(biz.preQuetion());
    }

    public void nextQuetion(View view) {
        showQuetion(biz.nextQuetion());
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
