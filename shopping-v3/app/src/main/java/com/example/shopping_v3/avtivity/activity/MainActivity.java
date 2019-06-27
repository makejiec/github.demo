package com.example.shopping_v3.avtivity.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.example.shopping_v3.R;
import com.example.shopping_v3.avtivity.fragment.BaseFragment;
import com.example.shopping_v3.avtivity.fragment.CommunityFragment;
import com.example.shopping_v3.avtivity.fragment.HomeFragment;
import com.example.shopping_v3.avtivity.fragment.ShoppingCartFragment;
import com.example.shopping_v3.avtivity.fragment.TypeCartFragment;
import com.example.shopping_v3.avtivity.fragment.UserCartFragment;

import java.lang.reflect.Method;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;


    //装fragment的实例集合
    private ArrayList<BaseFragment> fragments;


    private int position = 0;

    //缓存Fragment或上次显示的Fragment
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife和当前Activity绑定
        ButterKnife.bind(this);
        changeImageSize();
        //初始化Fragment
        initFragment();
        //设置RadioGroup的监听
        initListener();
    }

    private void initListener() {
        rgMain.check(R.id.rb_home);
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home: //首页
                        position = 0;
                        break;
                    case R.id.rb_type: //分类
                        position = 1;
                        break;
                    case R.id.rb_community: //发现
                        position = 2;
                        break;
                    case R.id.rb_cart: //购物车
                        position = 3;
                        break;
                    case R.id.rb_user: //个人中心
                        position = 4;
                        break;
                    default:
                        position = 0;
                        break;
                }
                //根据位置得到相应的Fragment
                BaseFragment baseFragment = getFragment(position);
                /**
                 * 第一个参数: 上次显示的Fragment
                 * 第二个参数: 当前正要显示的Fragment
                 */
                switchFragment(tempFragment,baseFragment);
            }
        });
    }

    /**
     * 添加的时候按照顺序
     */
    private void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeCartFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserCartFragment());
    }

    /**
     * 根据位置得到对应的 Fragment
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position){
        if(fragments != null && fragments.size()>0){
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * 切换Fragment
     * @param fragment
     * @param nextFragment
     */
    private void switchFragment(Fragment fragment,BaseFragment nextFragment){
        if (tempFragment != nextFragment){
            tempFragment = nextFragment;
            if (nextFragment != null){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加成功
                if (!nextFragment.isAdded()){
                    //隐藏当前的Fragment
                    if (fragment != null){
                        transaction.hide(fragment);
                    }
                    //添加Fragment
                    transaction.add(R.id.frameLayout,nextFragment).commit();
                }else {
                    //隐藏当前Fragment
                    if (fragment != null){
                        transaction.hide(fragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

    private void changeImageSize(){

        RadioButton[] rbs = new RadioButton[5];
        //初始化控件，中间大个的，周围小弟

        rbs[0] = (RadioButton) findViewById(R.id.rb_home);
        rbs[1] = (RadioButton) findViewById(R.id.rb_type);
        rbs[2] = (RadioButton) findViewById(R.id.rb_community);
        rbs[3] = (RadioButton) findViewById(R.id.rb_cart);
        rbs[4] = (RadioButton) findViewById(R.id.rb_user);

        for (RadioButton rb : rbs) {
            //挨着给每个RadioButton加入drawable限制边距以控制显示大小
            Drawable[] drs = rb.getCompoundDrawables();
            //获取drawables
            Rect r = new Rect(0, 0, 70, 70);
            //定义一个Rect边界
            drs[1].setBounds(r);
            //给drawable设置边界
            if (rb.getId() == R.id.rb_community) {
                r = new Rect(0, 0, 100, 100);
                drs[1].setBounds(r);
            }
            rb.setCompoundDrawables(null,drs[1],null,null);
            //添加限制给控件
        }

    }
    private long backTime = 0;


    public void onBackPressed() {
        //间隔时间小于2000毫秒，即2s，就关闭MainActivity
        if (System.currentTimeMillis() - backTime < 2000) {
            super.onBackPressed();
        } else {
            //否则就弹出toast提示，我这抽取了方法，然后把当前的时间值复制给backTime变量
            Toast.makeText(this,"再点击一次当前Activity",Toast.LENGTH_LONG).show();
            backTime = System.currentTimeMillis();
        }
    }

}