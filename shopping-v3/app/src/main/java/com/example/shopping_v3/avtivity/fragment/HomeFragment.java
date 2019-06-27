package com.example.shopping_v3.avtivity.fragment;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.shopping_v3.R;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private final static String TAG = HomeFragment.class.getSimpleName();

    private EditText tv_search_home;
    private TextView tv_message_home,tv_right;
    private Drawable drawable1,drawable2,drawable3;
    private GridView gridView;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;



    @Override
    public View initView() {
        Log.e(TAG,"主页面的Fragment的UI被初始化了");
        View view = View.inflate(mContext,R.layout.fragment_home,null);
        //初始化布局控件
        tv_search_home =  view.findViewById(R.id.tv_search_home);
        tv_message_home =  view.findViewById(R.id.tv_message_home);
        tv_right = view.findViewById(R.id.tv_more_recommend);

        gridView = view.findViewById(R.id.gv);

        int icon[] = {R.mipmap.no_1,R.mipmap.no_2,R.mipmap.no_3,R.mipmap.no_4};
        dataList = new ArrayList<Map<String,Object>>();


        for (int i=0; i<icon.length; i++){
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("ItemImage",icon[i]);
            dataList.add(map);
        }
        String[] from={"ItemImage"};

        int[] to={R.id.ItemImage};

       adapter = new SimpleAdapter(mContext, dataList, R.layout.gridview_item, from, to);


        gridView.setAdapter(adapter);

        tv_search_home.setOnClickListener(this);
        tv_message_home.setOnClickListener(this);

        drawable1 = getResources().getDrawable(R.mipmap.sousu);
        drawable2 = getResources().getDrawable(R.mipmap.xiaoxi);
        drawable3 = getResources().getDrawable(R.mipmap.right);
        drawable1.setBounds(0, 0, 50, 50);//第一0是距左边距离，第二0是距上边距离，分别是长宽
        tv_search_home.setCompoundDrawables(drawable1, null, null, null);//只放左边
        drawable2.setBounds(0, 0, 80, 80);//第一0是距左边距离，第二0是距上边距离，分别是长宽
        tv_message_home.setCompoundDrawables(null, drawable2, null, null);//只放左边
        drawable3.setBounds(0,0,40,40);
        tv_right.setCompoundDrawables(null,null,drawable3,null);
        return view;
    }


    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"主页面的Fragment的数据被初始化了");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_search_home:  //搜索的监听
                Toast.makeText(mContext,"搜索",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_message_home: //消息监听

                Toast.makeText(mContext,"进入消息中心",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
