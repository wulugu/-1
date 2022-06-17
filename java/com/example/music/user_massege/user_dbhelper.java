package com.example.music.user_massege;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class user_dbhelper extends SQLiteOpenHelper {
    public static final String name = "db_user_massege.db";
    public static final int DB_VERSION = 1;
    public static final String CREATE_USERDATA1 = "create table user(user_id varchar(10)primary key,user_date varchar(10)," +
            "user_massege varchar(10))";
    //构造函数
    public user_dbhelper(Context context) { super(context, name, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERDATA1);
        db.execSQL("insert into user(user_id,user_data,user_massege)Values('huahua','金炜翔','物联网')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
