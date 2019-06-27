package com.example.shopping_v3.avtivity.fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shopping_v3.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class List_adapter extends BaseAdapter {

    private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    private Context context;

    //初始化位置
    private int clickStatus = 0;

    public void setSeclection(int position) {
        clickStatus = position;
    }

    public List_adapter(Context context, List<Map<String, String>> list, int a1a1) {
        this.context = context;
        this.list = list;
        this.clickStatus = a1a1;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.typecart_item, null);
            holder = new ViewHolder();
            holder.tv1 = (TextView) convertView.findViewById(R.id.tv1);
            holder.right_tv = (TextView) convertView.findViewById(R.id.right_tv);
            holder.rl1 = (RelativeLayout) convertView.findViewById(R.id.rl1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (clickStatus == position) {
            holder.tv1.setText(list.get(position).get("1"));
            holder.tv1.setTextColor(Color.rgb(221, 84, 102));
            holder.right_tv.setBackgroundColor(Color.rgb(244, 245, 247));
            holder.rl1.setBackgroundColor(Color.rgb(244, 245, 247));
        } else {
            holder.tv1.setText(list.get(position).get("1"));
            holder.tv1.setTextColor(Color.rgb(29, 29, 29));
            holder.right_tv.setBackgroundColor(Color.rgb(230, 230, 230));
            holder.rl1.setBackgroundColor(Color.rgb(255, 255, 255));
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv1;
        TextView right_tv;
        RelativeLayout rl1;
    }
}
