package com.example.shopping_v3.avtivity.fragment;


import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.shopping_v3.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeCartFragment extends BaseFragment {

    private final static String TAG = TypeCartFragment.class.getSimpleName();

    private ListView listview;
    private List<Map<String, String>>list = new ArrayList<Map<String,String>>();



    @Override
    public View initView() {
        Log.e(TAG,"主页面的Fragment的UI被初始化了");
        View view = View.inflate(mContext, R.layout.typecart,null);
        listview = (ListView) view.findViewById(R.id.listview);

        //模拟列表集合
        list = new ArrayList<Map<String,String>>();
        String str[] = {"推荐分类","潮流女装","品牌男装","个护化妆","家用电器","电脑办公","手机数码",
                "母婴童装","图书音像","家居家纺","家具建材","食品生鲜","酒水饮料","运动户外","鞋靴箱包","奢品礼品",
                "钟表珠宝","玩具乐器","内衣配饰","汽车用品","医药保健","生活旅行","宠物农资"};
        for (int i = 0; i < str.length; i++) {
            Map<String, String>map = new HashMap<String, String>();
            map.put("1", str[i]);
            list.add(map);
        }

        //自定义adapter  实现效果
        final List_adapter list_adapter = new List_adapter(mContext, list,0);
        listview.setAdapter(list_adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //选中的位置
                list_adapter.setSeclection(arg2);
                //刷新listview
                list_adapter.notifyDataSetChanged();
                //将选中位置平滑滑动到中间位置
                if(arg2==0||arg2==1||arg2==2||arg2==3||arg2==4||arg2==5){
                    listview.smoothScrollToPosition(0);//滑动到排头
                }else if(arg2==24||arg2==23||arg2==22||arg2==21||arg2==20){
                    listview.smoothScrollToPosition(list_adapter.getCount()-1);//滑动到底部
                }else if(arg2==6||arg2==7||arg2==8||arg2==9||arg2==10||arg2==11
                        ||arg2==12||arg2==13||arg2==14||arg2==15||arg2==16||arg2==17
                        ||arg2==18||arg2==19){
                    listview.smoothScrollToPositionFromTop(arg2-5, 0); //滑动到中间位置
                }

            }
        });

        return view;

    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"主页面的Fragment的数据被初始化了");
    }
}
