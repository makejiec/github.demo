package com.example.shopping_v3.avtivity.fragment;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.MenuPopupHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;


import com.example.shopping_v3.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CommunityFragment extends BaseFragment implements View.OnClickListener {

    private final static String TAG = CommunityFragment.class.getSimpleName();

    private ImageView imageView;
    private Drawable drawable1;
    private EditText editText;

    @Override
    public View initView() {
        Log.e(TAG,"主页面的Fragment的UI被初始化了");
        View view = View.inflate(mContext,R.layout.message,null);
        imageView = view.findViewById(R.id.jia);
        imageView.setOnClickListener(this);
        editText = view.findViewById(R.id.tv_xiaoxi);

        drawable1 = getResources().getDrawable(R.mipmap.sousu);
        drawable1.setBounds(0, 0, 50, 50);//第一0是距左边距离，第二0是距上边距离，分别是长宽
        editText.setCompoundDrawables(drawable1, null, null, null);//只放左边
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"主页面的Fragment的数据被初始化了");
    }

    @Override
    public void onClick(View view){
        showPopupMenu(view);
    }


    @SuppressLint("RestrictedApi")
    private void showPopupMenu(View view) {
        // 这里的view代表popupMenu需要依附的view
        PopupMenu popupMenu = new PopupMenu(mContext, view);
        // 获取布局文件
        popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());

        popupMenu.show();


    }

}
