package cn.software_engineering.jkbdbyempress.View;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.software_engineering.jkbdbyempress.ExamApplication;
import cn.software_engineering.jkbdbyempress.R;
import cn.software_engineering.jkbdbyempress.bean.Quetion;

/**
 * Created by Asus on 2017/7/4.
 */

public class QuetionAdapter extends BaseAdapter{
    Context context;
    List<Quetion> examList;

    public QuetionAdapter(Context context) {
        this.context = context;
        examList=ExamApplication.getInstance().getMquetions();
    }

    @Override
    public int getCount() {
        return examList==null?0:examList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate= View.inflate(context,R.layout.item_quetion,null);
        ImageView ivQuetion= (ImageView) inflate.findViewById(R.id.iv_quetion);
        TextView tvno= (TextView) inflate.findViewById(R.id.tv_no );
        tvno.setText("第"+i+"题");
        return inflate;
    }
}
