package cn.software_engineering.jkbdbyempress.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.software_engineering.jkbdbyempress.ExamApplication;
import cn.software_engineering.jkbdbyempress.R;
import cn.software_engineering.jkbdbyempress.bean.Examine;
import cn.software_engineering.jkbdbyempress.bean.Quetion;

/**
 * Created by Asus on 2017/6/29.
 */

public class RandonActivity extends AppCompatActivity {
    TextView exminfo,title,intem1,intem2,intem3,intem4;
    ImageView quetion_img;

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
        quetion_img= (ImageView) findViewById(R.id.image_1);
        initData();

       /* if(intent!=null)
        {
            Examine examine=(Examine)intent.getSerializableExtra("key");
            ArrayList<Quetion> quetion= (ArrayList<Quetion>) intent.getSerializableExtra("result");
            exminfo.setText(examine.toString());
            Log.e("fff","qqq:"+quetion.size());
            Log.e("fff","aaa:"+quetion.get(0));

            if (quetion!=null)
            {
                title.setText(quetion.get(0).getQuestion());
                intem1.setText(quetion.get(0).getItem1());
                intem2.setText(quetion.get(0).getItem2());
                intem3.setText(quetion.get(0).getItem3());
                intem4.setText(quetion.get(0).getItem4());

            }
            else{

            }


        }
        else
        {
            exminfo.setText("error");
        }


        //Examine exainfo=(Examine)intent.getSerializableExtra("key");

        //textView.setText(exainfo.toString());*/
    }

    private void initData() {
        Examine mexamine = ExamApplication.getInstance().getMexamine();
        if(mexamine!=null)
        {
            showData(mexamine);
        }
        List<Quetion> quetion=ExamApplication.getInstance().getMquetions();
        if(quetion!=null){
            showQuetion(quetion);
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


}
