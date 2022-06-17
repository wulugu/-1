package com.example.music;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class frag2 extends Fragment {
    private View view;
    public EditText ed1;
    private TextView textView1;
    private Button bt1;
    private ImageView imageView1,imageView2;
    private SearchView searchView1;
    private ImageButton imageButton1;


    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.frag2_layout, null);
        ImageView listView = view.findViewById(R.id.lv);
        imageView1 = view.findViewById(R.id.searchView);
        imageView2 = view.findViewById(R.id.messageView);
        textView1 = view.findViewById(R.id.get_massage);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(frag2.this.getContext(),Activity_search.class);//创建Intent对象，启动check
                //将数据存入Intent对象
                startActivity(intent);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(frag2.this.getContext(),activity_massege.class);//创建Intent对象，启动check
                //将数据存入Intent对象
                startActivity(intent);
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(frag2.this.getContext(),set_choose.class);//创建Intent对象，启动check
                //将数据存入Intent对象
                startActivity(intent);
            }
        });
        return view;

    }
}
