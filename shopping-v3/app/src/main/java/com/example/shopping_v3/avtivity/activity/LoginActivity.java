package com.example.shopping_v3.avtivity.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopping_v3.R;
import com.example.shopping_v3.avtivity.service.UserService;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
    }
    private void findViews() {
        username=(EditText) findViewById(R.id.et_1);
        password=(EditText) findViewById(R.id.et_2);
        login=(Button) findViewById(R.id.btn_login);
        register=(Button) findViewById(R.id.btn_register);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name=username.getText().toString();
                String pass=password.getText().toString();
                Log.i("TAG",name+"_"+pass);
                UserService uService=new UserService(LoginActivity.this);
                boolean flag=uService.login(name, pass);
                if(flag){
                    Log.i("TAG","登录成功");
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                }else{
                    Log.i("TAG","登录失败");
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_LONG).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.pro)
                .setTitle("确认退出吗？")
                .setPositiveButton("残忍离开！", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("怎么可能！",null)
                .show();
    }
}