package com.example.ygb.myfirstproject.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ygb.myfirstproject.R;
import com.example.ygb.myfirstproject.Utils.SharedUitls;

public class SpalshActivity extends AppCompatActivity {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//一个整型值，用于标识具体的msg信息，这个整型值是我们自己定义的
            switch (msg.what) {
                case 1:
                    if (isFirst()){
                        Intent intent = new Intent(SpalshActivity.this,GuideActivity.class);
                        startActivity(intent);
                        SpalshActivity.this.finish();
                    }else {
                        Intent intent=new Intent(SpalshActivity.this, MainActivity.class);
                        startActivity(intent);
                        SpalshActivity.this.finish();
                    }


                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
//设置启动延迟
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    @Override
    public void onBackPressed() {
        /*super.onBackPressed();*/

    }

    public boolean isFirst(){
        boolean first=SharedUitls.getBooleanData(this,"ISFIRST",true);

        if (first){
            SharedUitls.putBooleanData(this,"ISFIRST",false);
            return true;
        }
        else {
            return false;
        }
    }
}
