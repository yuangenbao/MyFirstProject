package com.example.ygb.myfirstproject.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.DateFormat;

/**  轻量的存储
 * Created by YuanBuyuan on 2017/10/18 0018.
 */

public class SharedUitls {
    public static final String FiLE="config";

    public static void putBooleanData(Context mContext,String key,boolean value){
        SharedPreferences sp=mContext.getSharedPreferences(FiLE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }
    public static boolean getBooleanData(Context mContext, String key, boolean defValue){
        SharedPreferences sp=mContext.getSharedPreferences(FiLE,Context.MODE_PRIVATE);
        return sp.getBoolean(key,defValue);
    }
}
