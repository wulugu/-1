package com.example.music;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class list_show extends AppCompatActivity {
    private Button b1;
    public String[] name={"ACKPINK - AS IF IT'S YOUR LAST (마지막처럼)","双笙 - 故梦","GAI周延 _ 刘煜 - 沧海一声笑",
            "爱殇","如约而至","雨幕","棠梨煎雪","绘笔江南","恣意千年","黯然销魂","千梦"};
    public static int[] icons={R.drawable.xianshi,R.drawable.xianshi,R.drawable.xianshi,R.drawable.xianshi,
            R.drawable.xianshi,R.drawable.xianshi,R.drawable.xianshi,R.drawable.xianshi,R.drawable.xianshi,R.drawable.xianshi,
            R.drawable.xianshi};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_show);
        ListView listView=(ListView) findViewById(R.id.listview1);
        b1=(Button) findViewById(R.id.user_return);
        MyBaseAdapter adapter=new MyBaseAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(list_show.this,Music_Activity.class);//创建Intent对象，启动check
                //将数据存入Intent对象
                intent.putExtra("name",name[position]);
                intent.putExtra("position",String.valueOf(position));
                startActivity(intent);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(list_show.this,MainActivity.class);//返回到user界面
                startActivity(intent);
                finish();
            }
        });
    }
    class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount(){return  name.length;}
        @Override
        public Object getItem(int i){return name[i];}
        @Override
        public long getItemId(int i){return i;}

        @Override
        public View getView(int i ,View convertView, ViewGroup parent) {
            @SuppressLint("ViewHolder") View view=View.inflate(list_show.this,R.layout.item_layout,null);
            TextView tv_name=view.findViewById(R.id.item_name);
            ImageView iv=view.findViewById(R.id.iv);

            tv_name.setText(name[i]);
            iv.setImageResource(icons[i]);
            return view;
        }
    }

}
