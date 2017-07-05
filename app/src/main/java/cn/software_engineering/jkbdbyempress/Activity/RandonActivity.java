package cn.software_engineering.jkbdbyempress.Activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.software_engineering.jkbdbyempress.ExamApplication;
import cn.software_engineering.jkbdbyempress.R;
import cn.software_engineering.jkbdbyempress.View.QuetionAdapter;
import cn.software_engineering.jkbdbyempress.bean.Examine;
import cn.software_engineering.jkbdbyempress.bean.Quetion;
import cn.software_engineering.jkbdbyempress.biz.ExamBiz;
import cn.software_engineering.jkbdbyempress.biz.IExamBiz;

/**
 * Created by Asus on 2017/6/29.
 */

public class RandonActivity extends AppCompatActivity {
    int count,correct_num,error_num;
    boolean firstAsk;
    CheckBox[] cbs;
    TextView[] options;
    TextView[] optionText;
    boolean isLoadExamInfoReceiver = false;
    boolean isLoadExamQuetionReceiver = false;
    boolean isLoadExamInfo = false;
    boolean isLoadExamQuetion = false;
    QuetionAdapter adapter;
    IExamBiz biz;
    LoadExamBroadcast loadExamBroadcast;
    LoadQuetionBroadcast loadQuetionBroadcast;
    @BindView(R.id.load_dialog)    ProgressBar loaddialog;
    @BindView(R.id.tv_load)    TextView tvload;
    @BindView(R.id.layout_loading)    LinearLayout layoutLoading;
    @BindView(R.id.examineindo)    TextView exminfo;
    @BindView(R.id.tv_time)    TextView tvtime;
    @BindView(R.id.image_1)    ImageView quetion_img;
    @BindView(R.id.tv_exam_no)    TextView tvno;
    @BindView(R.id.tv_exmtitle)    TextView title;
    @BindView(R.id.op_01)    TextView op01;
    @BindView(R.id.tv_intem1)    TextView intem1;
    @BindView(R.id.op_02)    TextView op02;
    @BindView(R.id.tv_intem2)    TextView intem2;
    @BindView(R.id.op_03)    TextView op03;
    @BindView(R.id.tv_intem3)    TextView intem3;
    @BindView(R.id.layout_03)    LinearLayout layout03;
    @BindView(R.id.op_04)    TextView op04;
    @BindView(R.id.tv_intem4)    TextView intem4;
    @BindView(R.id.layout_04)    LinearLayout layout04;
    @BindView(R.id.cb_01)    CheckBox cb_01;
    @BindView(R.id.cb_02)    CheckBox cb_02;
    @BindView(R.id.cb_03)    CheckBox cb_03;
    @BindView(R.id.cb_04)    CheckBox cb_04;
    @BindView(R.id.tv_analysis)    TextView tvanalyisis;
    @BindView(R.id.gallery01)    Gallery gallery;
    @BindView(R.id.btn_next)    Button btnNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        ButterKnife.bind(this);
        firstAsk=true;
        count=correct_num=error_num=0;
        cbs = new CheckBox[4];
        cbs[0] = cb_01;
        cbs[1] = cb_02;
        cbs[2] = cb_03;
        cbs[3] = cb_04;
        options=new TextView[4];
        options[0]=intem1;
        options[1]=intem2;
        options[2]=intem3;
        options[3]=intem4;
        optionText=new TextView[4];
        optionText[0]=op01;
        optionText[1]=op02;
        optionText[2]=op03;
        optionText[3]=op04;
        cb_01.setOnCheckedChangeListener(listener);
        cb_02.setOnCheckedChangeListener(listener);
        cb_03.setOnCheckedChangeListener(listener);
        cb_04.setOnCheckedChangeListener(listener);
        loadExamBroadcast = new LoadExamBroadcast();
        loadQuetionBroadcast = new LoadQuetionBroadcast();
        setListener();
        biz = new ExamBiz();
        loadData();
    }
    @OnClick(R.id.layout_loading) void onLoadClick(){loadData();}

    CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if (isChecked) {
                int userAnswer = 0;
                switch (compoundButton.getId()) {
                    case R.id.cb_01:
                        userAnswer = 1;
                        break;
                    case R.id.cb_02:
                        userAnswer = 2;
                        break;
                    case R.id.cb_03:
                        userAnswer = 3;
                        break;
                    case R.id.cb_04:
                        userAnswer = 4;
                        break;
                }
                Log.e("CheckedChanged", "user" + userAnswer + ",isChecked" + isChecked);
                if (userAnswer > 0) {
                    for (CheckBox cb : cbs) {
                        cb.setChecked(false);
                    }
                    cbs[userAnswer - 1].setChecked(true);
                }
            }
        }
    };

    private void notSelect() {
        for (CheckBox cb : cbs) {
            cb.setOnCheckedChangeListener(null);
            cb.setEnabled(false);
        }
    }

    private void setListener() {
        registerReceiver(loadExamBroadcast, new IntentFilter(ExamApplication.LOAD_EXAM_INFO));
        registerReceiver(loadQuetionBroadcast, new IntentFilter(ExamApplication.LOAD_EXAM_QUESTION));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loadExamBroadcast != null) {
            unregisterReceiver(loadExamBroadcast);
        }
        if (loadQuetionBroadcast != null) {
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
        if (isLoadExamInfoReceiver && isLoadExamQuetionReceiver) {
            if (isLoadExamInfo == true && isLoadExamQuetion == true) {
                layoutLoading.setVisibility(View.GONE);
                Examine mexamine = ExamApplication.getInstance().getMexamine();
                if (mexamine != null) {
                    showData(mexamine);
                }
                innitGallery();
                showQuetion(biz.getNowQuetion());
                initTime(mexamine);
            } else {
                layoutLoading.setEnabled(true);
                loaddialog.setVisibility(View.GONE);
                tvload.setText("下载失败，点击重新下载");
            }
        }
    }

    private void innitGallery() {
        adapter = new QuetionAdapter(this);
        gallery.setAdapter(adapter);
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                saveUserAnswer();
                showQuetion(biz.getNowQuetion(i));
            }
        });
    }

    private void initTime(Examine mexamine) {
        long sumTime = mexamine.getLimitTime() * 60 * 1000;
        final long overTime = sumTime + System.currentTimeMillis();
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long l = overTime - System.currentTimeMillis();
                final long min = l / 1000 / 60;
                final long sec = l / 1000 % 60;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvtime.setText("剩余时间：" + min + "分" + sec + "秒");
                    }
                });
            }
        }, 0, 1000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        commit(null);
                    }
                });
            }
        }, sumTime);
    }

    private void showQuetion(Quetion mquetion) {
        Log.e("showQuetion", "showQuetion,mquetion" + mquetion);
        if (mquetion != null) {
            tvno.setText(biz.getQuetionIndex());
            title.setText(mquetion.getQuestion());
            intem1.setText(mquetion.getItem1());
            intem2.setText(mquetion.getItem2());
            intem3.setText(mquetion.getItem3());
            intem4.setText(mquetion.getItem4());
            layout03.setVisibility(mquetion.getItem3().equals("") ? View.GONE : View.VISIBLE);
            cb_03.setVisibility(mquetion.getItem3().equals("") ? View.GONE : View.VISIBLE);
            layout04.setVisibility(mquetion.getItem4().equals("") ? View.GONE : View.VISIBLE);
            cb_04.setVisibility(mquetion.getItem4().equals("") ? View.GONE : View.VISIBLE);
            if (mquetion.getUrl() != null && !mquetion.getUrl().equals("")) {
                quetion_img.setVisibility(View.VISIBLE);
                Picasso.with(RandonActivity.this).load(mquetion.getUrl()).into(quetion_img);
            } else {
                quetion_img.setVisibility(View.GONE);
            }
            resetOption();
            String userAnswer = mquetion.getUserAnswer();
            if (userAnswer != null && !userAnswer.equals("")) {//用户已经答题
                int userCB = Integer.parseInt(userAnswer) - 1;
                cbs[userCB].setChecked(true);
                String answer = null;
                switch (mquetion.getAnswer()) {
                    case "1":
                        answer = "A";
                        break;
                    case "2":
                        answer = "B";
                        break;
                    case "3":
                        answer = "C";
                        break;
                    case "4":
                        answer = "D";
                        break;
                }
                String analysis = mquetion.getExplains();
                tvanalyisis.setText("正确答案为：" + answer + "\n解析：" + analysis);
                tvanalyisis.setVisibility(View.VISIBLE);
                notSelect();
                if(!userAnswer.equals(mquetion.getAnswer())){
                    tvanalyisis.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    tvanalyisis.setTextColor(getResources().getColor(R.color.black));
                }
                int ua=Integer.parseInt(userAnswer)-1;
                int a=Integer.parseInt(mquetion.getAnswer())-1;
                for (int i = 0; i < options.length; i++) {
                    if(i==a){//正确答案
                        options[i].setTextColor(getResources().getColor(R.color.green));
                        optionText[i].setTextColor(getResources().getColor(R.color.green));
                    }
                    else{//错误答案
                        if(ua!=a && i==ua){//用户选错的答案
                            options[i].setTextColor(getResources().getColor(R.color.red));
                            optionText[i].setTextColor(getResources().getColor(R.color.red));
                        }
                        else {//错误答案中又非用户选中的
                            options[i].setTextColor(getResources().getColor(R.color.black));
                            optionText[i].setTextColor(getResources().getColor(R.color.black));
                        }
                    }
                }
            }
        }
    }

    private void resetOption() {
        for (CheckBox cb : cbs) {
            cb.setChecked(false);
            cb.setOnCheckedChangeListener(listener);
            cb.setEnabled(true);
        }
        for (TextView option : options) {
            option.setTextColor(getResources().getColor(R.color.black));
        }
        for (TextView optiontext : optionText) {
            optiontext.setTextColor(getResources().getColor(R.color.black));
        }
        tvanalyisis.setTextColor(getResources().getColor(R.color.black));
        tvanalyisis.setVisibility(View.GONE);
    }

    private void saveUserAnswer() {
        for (int i = 0; i < cbs.length; i++) {
            if (cbs[i].isChecked()) {
                biz.getNowQuetion().setUserAnswer(String.valueOf(i + 1));
                int a=Integer.parseInt(biz.getNowQuetion().getAnswer())-1;
                if(a==i){//答题正确
                    correct_num++;
                    count++;
                }else {//答题错误
                    error_num++;
                    count++;
                }
                if(error_num==11  && firstAsk){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setIcon(R.mipmap.exam_commit32x32)
                            .setTitle("是否答题")
                            .setMessage("你的分数已经不及格，还要继续答题吗？")
                            .setPositiveButton("继续", null)
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    commit(null);
                                }
                            });
                    firstAsk=false;
                    builder.create().show();
                }
                if(count==100){
                    commit(null);
                }
                adapter.notifyDataSetChanged();
                return;
            }
        }
        biz.getNowQuetion().setUserAnswer("");
        adapter.notifyDataSetChanged();
    }

    private void showData(Examine mexamine) {
        exminfo.setText(mexamine.toString());
    }

    public void preQuetion(View view) {
        saveUserAnswer();
        showQuetion(biz.preQuetion());
    }

    public void nextQuetion(View view) {
        saveUserAnswer();
        showQuetion(biz.nextQuetion());
    }

    public void commit(View view) {
        saveUserAnswer();
        int s = biz.commitExam();
        View inflate = View.inflate(this, R.layout.layout_result, null);
        TextView tvResult = (TextView) inflate.findViewById(R.id.tv_result);
        tvResult.setText("你的分数是\n" + s + "分");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.exam_commit32x32)
                .setTitle("交卷")
                //     .setMessage("你的分数为\n"+s+"分数")
                .setView(inflate)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
        builder.create().show();
    }

    class LoadExamBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean issuccess = intent.getBooleanExtra(ExamApplication.LOAD_DATA_SUCCESS, false);
            Log.e("LoadExamBroadcast", "LoadExamBroadcast,issuccess:" + issuccess);
            if (issuccess) {
                isLoadExamInfo = true;
            }
            isLoadExamInfoReceiver = true;
            initData();
        }
    }

    class LoadQuetionBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean issuccess = intent.getBooleanExtra(ExamApplication.LOAD_DATA_SUCCESS, false);
            Log.e("LoadQuetionBroadcast", "LoadQuetionBroadcast,issuccess:" + issuccess);
            if (issuccess) {
                isLoadExamQuetion = true;
            }
            isLoadExamQuetionReceiver = true;
            initData();
        }
    }
}
