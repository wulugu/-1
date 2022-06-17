package com.example.music.user_massege;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class userDAO {
    private Context context;
    private user_dbhelper dbhelper;
    private SQLiteDatabase db;

    public userDAO(Context context){this.context = context;}

    public void open() throws SQLException {
        dbhelper = new user_dbhelper(context);
        try {
            db = dbhelper.getWritableDatabase();
        }catch (SQLException ex){
            db = dbhelper.getReadableDatabase();
        }
    }
    public void close(){
        if (db != null){
            db.close();
            db = null;
        }
    }
    //添加信息
    public long adduser(user o){
        // 创建ContentValues对象
        ContentValues values = new ContentValues();
        // 向该对象中插入键值对
        values.put("user_id", o.user_id);
        values.put("user_date", o.user_date);
        values.put("user_massege", o.user_massege);

        // 调用insert()方法将数据插入到数据库当中
        return db.insert("user", null, values);
    }
}
