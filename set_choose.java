package com.example.music;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class set_choose extends AppCompatActivity {
    private EditText editText1,editText2,editText3,editText4,editText5,editText6;
    private TextView textView1;
    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_choose);
        editText1 = (EditText) findViewById(R.id.di_yi);
        editText2 = (EditText) findViewById(R.id.di_er);
        editText3 = (EditText) findViewById(R.id.di_san);
        editText4 = (EditText) findViewById(R.id.di_si);
        editText5 = (EditText) findViewById(R.id.di_wu);
        editText6 = (EditText) findViewById(R.id.di_liu);
        Button button1 = (Button) findViewById(R.id.ti_jiao);
        textView1 = (TextView) findViewById(R.id.xian_shi);
        button1.setOnClickListener(v -> {
            String diyi = editText1.getText().toString();
            String dier = editText2.getText().toString();
            String disan = editText3.getText().toString();
            String disi = editText4.getText().toString();
            String diwu = editText5.getText().toString();
            String diliu = editText6.getText().toString();
                int x1= new Random().nextInt(7);
                if (x1==1){
                    textView1.setText("第一："+diyi);
                    Toast.makeText(set_choose.this, "第一："+diyi, Toast.LENGTH_SHORT).show();
                }
                if (x1==2){
                    textView1.setText("第二："+dier);
                    Toast.makeText(set_choose.this, "第二："+dier, Toast.LENGTH_SHORT).show();
                }
                if (x1==3){
                    textView1.setText("第三："+disan);
                    Toast.makeText(set_choose.this, "第三："+disan, Toast.LENGTH_SHORT).show();
                }
                if (x1==4){
                    textView1.setText("第四："+disi);
                    Toast.makeText(set_choose.this, "第四："+disi, Toast.LENGTH_SHORT).show();
                }
                if (x1==5){
                    textView1.setText("第五："+diwu);
                    Toast.makeText(set_choose.this, "第五："+diwu, Toast.LENGTH_SHORT).show();
                }
                if (x1==6){
                    textView1.setText("第六："+diliu);
                    Toast.makeText(set_choose.this, "第六："+diliu, Toast.LENGTH_SHORT).show();
                }
                if (x1==0){
                    textView1.setText("吃锤子，不吃了，淦");
                    Toast.makeText(set_choose.this, "没掷出", Toast.LENGTH_SHORT).show();
                }
        });

    }
}
