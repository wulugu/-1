package com.example.music;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.music.playerlist.MusicInfo;
import com.example.music.playerlist.MusicListAdapter;
import com.example.music.playerlist.MusicUtil;

import java.util.ArrayList;
import java.util.List;

public class frag1 extends Fragment {
    private View view;
    private List<MusicInfo> musicInfos = new ArrayList<>();
    private MusicListAdapter Listadapter;
    public static String[] name={"ACKPINK - AS IF IT'S YOUR LAST (마지막처럼)","双笙 - 故梦","GAI周延 _ 刘煜 - 沧海一声笑",
                "爱殇","如约而至","雨幕","棠梨煎雪","绘笔江南","恣意千年","黯然销魂","千梦"};
    public static int[] icons={R.drawable.xianshi,R.drawable.xianshi,R.drawable.xianshi,R.drawable.xianshi,
            R.drawable.xianshi,R.drawable.xianshi,R.drawable.xianshi,R.drawable.xianshi,R.drawable.xianshi,R.drawable.xianshi,
            R.drawable.xianshi};
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view=inflater.inflate(R.layout.music_list,null);
        ListView listView=view.findViewById(R.id.lv);
        //musicInfos = MusicUtil.getMp3Infos(frag1.this.getContext());
        //Listadapter = new MusicListAdapter(this.getContext(),musicInfos);
        //listView.setAdapter(Listadapter);
        MyBaseAdapter adapter=new MyBaseAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(frag1.this.getContext(),Music_Activity.class);//创建Intent对象，启动check
                //将数据存入Intent对象
                //intent.putExtra("name",musicInfos.get(position).getTitle());
                //intent.putExtra("id",musicInfos.get(position).getUrl());
                intent.putExtra("name",name[position]);
                intent.putExtra("position",String.valueOf(position));
                startActivity(intent);
            }
        });
        return view;
    }
    //这里是创建一个自定义适配器，可以作为模板
    class MyBaseAdapter extends BaseAdapter{
        @Override
        public int getCount(){return  name.length;}
        @Override
        public Object getItem(int i){return name[i];}
        @Override
        public long getItemId(int i){return i;}

        @Override
        public View getView(int i ,View convertView, ViewGroup parent) {
            //绑定好VIew，然后绑定控件
            View view=View.inflate(frag1.this.getContext(),R.layout.item_layout,null);
            TextView tv_name=view.findViewById(R.id.item_name);
            ImageView iv=view.findViewById(R.id.iv);
            //设置控件显示的内容，就是获取的歌曲名和歌手图片
            tv_name.setText(name[i]);
            iv.setImageResource(icons[i]);
            return view;
        }
    }

}
