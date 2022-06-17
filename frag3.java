package com.example.music;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class frag3 extends Fragment {
    private View view;
    private ImageView imageView1,imageView2;
    private TextView textView1,textView2;

    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag3_layout, null);
        imageView1 = view.findViewById(R.id.located_imageView);
        imageView2 = view.findViewById(R.id.shouchang);
        textView1 = view.findViewById(R.id.located_textview);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(frag3.this.getContext(),list_show.class);//创建Intent对象，启动check
                //将数据存入Intent对象
                startActivity(intent);
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(frag3.this.getContext(),list_show.class);//创建Intent对象，启动check
                //将数据存入Intent对象
                startActivity(intent);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(frag3.this.getContext(), antivity_shouchang.class);//创建Intent对象，启动check
                //将数据存入Intent对象
                startActivity(intent);
            }
        });
        return view;

    }

}