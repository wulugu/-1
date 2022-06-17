package com.example.music;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_user extends AppCompatActivity {
    private Button b1;
    private LinearLayout linearLayout1,linearLayout2,linearLayout3;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        b1=(Button) findViewById(R.id.user_return);
        linearLayout1 = (LinearLayout)findViewById(R.id.yonghuxinxi);
        linearLayout2 = (LinearLayout)findViewById(R.id.shezhi);
        linearLayout3 = (LinearLayout)findViewById(R.id.bangzhu);
        //用户信息
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity_user.this,MainActivity_my.class);//到user_help界面
                startActivity(intent);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity_user.this,Activity_user_setting.class);//到user_help界面
                startActivity(intent);
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity_user.this,Activity_user_help.class);//到user_help界面
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity_user.this,MainActivity.class);//返回到user界面
                startActivity(intent);
                finish();
            }
        });
    }
}
