package com.example.shopping_v3.avtivity.fragment;

import android.content.ComponentName;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.shopping_v3.R;
import com.example.shopping_v3.avtivity.service.MediaPlayerService;

public class UserCartFragment extends BaseFragment {

    private final static String TAG = UserCartFragment.class.getSimpleName();
    private TextView mtv;
    private Switch msw;

    boolean isbind = false;
    MediaPlayerService.MyBind mBind;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBind = (MediaPlayerService.MyBind) service;
            Log.i("MyLog","服务被绑定");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };



    @Override
    public View initView() {
        Log.e(TAG,"主页面的Fragment的UI被初始化了");
        View view = View.inflate(mContext, R.layout.usercart,null);
        mtv = view.findViewById(R.id.qt);
        msw = view.findViewById(R.id.sw);

        msw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mBind.play();
                    //Todo
                }else {
                    mBind.stop();
                    //Todo
                }

            }
        });




        mtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });


        Intent intent = new Intent();
        intent.setClass(mContext, MediaPlayerService.class);

        mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);



        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"主页面的Fragment的数据被初始化了");
    }
}