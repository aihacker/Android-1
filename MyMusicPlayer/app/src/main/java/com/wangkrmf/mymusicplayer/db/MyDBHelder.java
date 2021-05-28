package com.wangkrmf.mymusicplayer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelder extends SQLiteOpenHelper {
    private static  final String DBNAME="financial.db";
    private static final int VERSION=1;
    public MyDBHelder(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    //1 创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建用户表
        db.execSQL("create table tb_userinfo(id integer primary key autoincrement,name varchar(10),pwd varchar(15),email varchar(50),phone varchar(11))");
    }

    //2 升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

