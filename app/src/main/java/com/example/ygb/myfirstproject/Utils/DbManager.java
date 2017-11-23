package com.example.ygb.myfirstproject.Utils;

import android.content.Context;

/**
 * Created by YuanBuyuan on 2017/11/9 0009.
 */

public class DbManager {
    private static MyDbHelper helper;
    public static MyDbHelper getHelperIntance(Context context){
        if (helper==null){
            helper=new MyDbHelper(context);
        }
        return helper;
    }
}
