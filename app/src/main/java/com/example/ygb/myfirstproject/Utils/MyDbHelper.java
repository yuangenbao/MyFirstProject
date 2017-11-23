package com.example.ygb.myfirstproject.Utils;

/**
 * Created by YuanBuyuan on 2017/11/9 0009.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MyDbHelper extends SQLiteOpenHelper {
    private Context context;
    public final static String TABLE="infoBook";
    public final static String TABLE_ID="id";
    public final static String TABLE_TITLES="titles";
    public final static String TABLE_AUTHOR="author";
    public final static String TABLE_FILES="files";
    public final static String TABLE_DATE="date";
    public final static String CREATE_TABLE="create table "+TABLE+"(" +
            TABLE_ID+" integer primary key autoincrement,"+
            TABLE_TITLES+" text not null," +
            TABLE_AUTHOR+" text not null," +
            TABLE_FILES+" text not null," +
            TABLE_DATE+" text not null);";
    /*    public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }*/
    public MyDbHelper(Context context) {
        super(context,"Info.db", null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        Toast.makeText(context,"创建数据库成功！！",Toast.LENGTH_SHORT).show();
        Log.d("sqltest","创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        Log.d("sqltest","------------onOpen------------------");
    }
}

