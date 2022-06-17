package com.example.music;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout content;
    private ImageView imageView1,imageView2,imageView3,imageView_slt,imageView_sec;
    private Button bn1;
    private TextView t1,t2,t3;
    private FragmentManager fm;
    private FragmentTransaction ft;
    boolean isExit;
    //是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值
    protected boolean useThemestatusBarColor = false;
    //是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6.0以上可以设置
    protected boolean useStatusBarColor = true;
    private boolean wd =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (wd){
            Toast.makeText(MainActivity.this, "已登录", Toast.LENGTH_SHORT).show();
        }
        content=(FrameLayout)findViewById(R.id.content);

        imageView1= (ImageView) findViewById(R.id.menu1);
        imageView2=(ImageView) findViewById(R.id.menu2);
        imageView3=(ImageView) findViewById(R.id.menu3);
        imageView_slt=(ImageView)findViewById(R.id.select);
        imageView_sec= (ImageView) findViewById(R.id.search_search);
        t1=(TextView)findViewById(R.id.txt_music_menu1);
        t2=(TextView)findViewById(R.id.txt_main_menu2);
        t3=(TextView)findViewById(R.id.txt_my_menu3);

        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);

        imageView1.setImageResource(R.drawable.muxic_light);
        t1.setTextColor(this.getResources().getColor(R.color.main_activity_set_color));

        imageView_slt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Activity_user.class);
                startActivity(intent);
            }
        });
        imageView_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Activity_search.class);
                startActivity(intent);
            }
        });

        fm=getSupportFragmentManager();//若是继承FragmentActivity，fm=getFragmentManger();
        ft=fm.beginTransaction();
        ft.replace(R.id.content,new frag1());//默认情况下Fragment1
        ft.commit();

        //setStatusBar();
    }
    @Override
    //退出方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
    public void exit(){
        Handler mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                isExit = false;
            }

        };
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            System.exit(0);
        }
    }
    //退出方法结束
    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (useThemestatusBarColor) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.black));//设置状态栏背景色
            } else {
                getWindow().setStatusBarColor(Color.TRANSPARENT);//透明
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        } else {
            Toast.makeText(this, "低于4.4的android系统版本不存在沉浸式状态栏", Toast.LENGTH_SHORT).show();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && useStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @SuppressLint({"NonConstantResourceId", "ResourceAsColor"})
    @Override
    public void onClick(View v){
        ft=fm.beginTransaction();
        switch (v.getId()){
            case R.id.menu1:
            case R.id.txt_music_menu1:
                ft.replace(R.id.content,new frag1());
                imageView1.setImageResource(R.drawable.muxic_light);
                t1.setTextColor(this.getResources().getColor(R.color.main_activity_set_color));
                imageView2.setImageResource(R.drawable.xiaoxi);
                t2.setTextColor(this.getResources().getColor(R.color.gray));
                imageView3.setImageResource(R.drawable.my);
                t3.setTextColor(this.getResources().getColor(R.color.gray));
                break;
            case R.id.menu2:
            case R.id.txt_main_menu2:
                ft.replace(R.id.content,new frag2());
                imageView1.setImageResource(R.drawable.music);
                t1.setTextColor(this.getResources().getColor(R.color.gray));
                imageView2.setImageResource(R.drawable.xiaoxi_light);
                t2.setTextColor(this.getResources().getColor(R.color.main_activity_set_color));
                imageView3.setImageResource(R.drawable.my);
                t3.setTextColor(this.getResources().getColor(R.color.gray));
                break;
            case R.id.menu3:
            case R.id.txt_my_menu3:
                ft.replace(R.id.content,new frag3());
                imageView1.setImageResource(R.drawable.music);
                t1.setTextColor(this.getResources().getColor(R.color.gray));
                imageView2.setImageResource(R.drawable.xiaoxi);
                t2.setTextColor(this.getResources().getColor(R.color.gray));
                imageView3.setImageResource(R.drawable.my_light);
                t3.setTextColor(this.getResources().getColor(R.color.main_activity_set_color));
                break;
            default:
                break;
        }
        ft.commit();
    }
}
