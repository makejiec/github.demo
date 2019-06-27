package com.example.shopping_v3.avtivity.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shopping_v3.R;
import com.example.shopping_v3.avtivity.domain.Mul;

public class ShoppingCartFragment extends BaseFragment {

    private final static String TAG = ShoppingCartFragment.class.getSimpleName();
    private Button mbt_1,mbt_2;
    private EditText met;
    private TextView mtv;
    int count=0,sum=0;


    @Override
    public View initView() {
        Log.e(TAG,"主页面的Fragment的UI被初始化了");
        View view = View.inflate(mContext, R.layout.car,null);
        mbt_1 = view.findViewById(R.id.mul);
        mbt_2 = view.findViewById(R.id.add);
        met = view.findViewById(R.id.met);
        mtv = view.findViewById(R.id.sum);
        mbt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                if(count<0){
                    met.setText("0");
                    mtv.setText("0");
                }else {
                    sum = Mul.mul(count);
                    mtv.setText(sum+"");
                    met.setText(count+"");
                }
            }
        });

        mbt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count<0){
                    count = 0;
                    count++;
                    met.setText(count+"");
                }
                else{
                    count++;
                    met.setText(count+"");
                }
                sum = Mul.mul(count);
                mtv.setText(sum+"");
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
