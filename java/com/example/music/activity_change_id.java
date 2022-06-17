package com.example.music;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_change_id extends AppCompatActivity {
private TextView textView1,textView2;
private EditText editText1;
private Button button1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_id);
        textView1 = (TextView)findViewById(R.id.precently_id);
        editText1 = (EditText) findViewById(R.id.now_id);
        button1 = (Button)findViewById(R.id.changeid_button);

        String id_name = editText1.getText().toString();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity_change_id.this,MainActivity_my.class);//创建Intent对象，启动check
                //将数据存入Intent对象
                intent.putExtra("id",id_name);
                startActivity(intent);
                activity_change_id.this.finish();
            }
        });
    }
}
