package com.example.music;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Activity_search extends AppCompatActivity {
    private String[] mStrs = {"ACKPINK - AS IF IT'S YOUR LAST (마지막처럼)","双笙 - 故梦","GAI周延 _ 刘煜 - 沧海一声笑",
            "爱殇","如约而至","雨幕","棠梨煎雪","绘笔江南","恣意千年","黯然销魂","千梦"};
//    public static int[] icons={R.drawable.music0,R.drawable.music1,R.drawable.music2,R.drawable.music3,
//            R.drawable.music4,R.drawable.music5,R.drawable.music6,R.drawable.music7,R.drawable.music8,R.drawable.music9,
//            R.drawable.music10};
    private SearchView mSearchView;
    int sum;
    private ListView lListView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mSearchView = (SearchView) findViewById(R.id.searchView);
        lListView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter adapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrs);

        lListView.setTextFilterEnabled(true);
        //设置该SearchView默认是否自动缩小为图标
        mSearchView.setIconifiedByDefault(false);
        //设置该SearchView显示搜索按钮
        mSearchView.setSubmitButtonEnabled(true);

        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {

                if (!TextUtils.isEmpty(newText)){
                    lListView.setAdapter(adapter);
                    lListView.setFilterText(newText);
                    System.out.println(newText);
                    if(newText.equals("雨幕")){
                        System.out.println("123");
                        sum=5;
                    } if(newText.equals("ACKPINK - AS IF IT'S YOUR LAST (마지막처럼)")){
                        System.out.println("123");
                        sum=0;
                    } if(newText.equals("双笙 - 故梦")){
                        System.out.println("123");
                        sum=1;
                    } if(newText.equals("GAI周延 _ 刘煜 - 沧海一声笑")){
                        System.out.println("123");
                        sum=2;
                    } if(newText.equals("爱殇")){
                        System.out.println("123");
                        sum=3;
                    } if(newText.equals("如约而至")){
                        System.out.println("123");
                        sum=4;
                    } if(newText.equals("棠梨煎雪")){
                        System.out.println("123");
                        sum=6;
                    } if(newText.equals("绘笔江南")){
                        System.out.println("123");
                        sum=7;
                    } if(newText.equals("恣意千年")){
                        System.out.println("123");
                        sum=8;
                    } if(newText.equals("黯然销魂")){
                        System.out.println("123");
                        sum=9;
                    } if(newText.equals("千梦")){
                        System.out.println("123");
                        sum=10;
                    }

                }else{
                    lListView.clearTextFilter();
                }
                return false;
            }
        });
        lListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object string = adapter.getItem(position);
                Intent intent=new Intent(Activity_search.this,Music_Activity.class);//创建Intent对象，启动check
                //将数据存入Intent对象
                intent.putExtra("name",mStrs[position]);
                intent.putExtra("position",String.valueOf(position));
                System.out.println(position);
                startActivity(intent);
            }
        });
    }

}
