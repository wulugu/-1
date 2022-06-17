package com.example.music;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class antivity_shouchang extends AppCompatActivity {
    private Button b1;
    private ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anctivity_shouchang);
        b1=(Button) findViewById(R.id.user_return);
        listView = (ListView)findViewById(R.id.lv_shouchang);

        MyInternalStorage myInternal = null;
        String name = null;
        String filename = null;
        String content = null;
        myInternal = new MyInternalStorage(antivity_shouchang.this);
        String[] files = myInternal.queryAllFile();
        System.out.println(files);
        ArrayAdapter<String> fileArray = new ArrayAdapter<String>(
                antivity_shouchang.this,
                android.R.layout.simple_list_item_1, files);
        listView.setAdapter(fileArray);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(antivity_shouchang.this, MainActivity.class);//返回到user界面
                startActivity(intent);
                finish();
            }
        });
    }
}
