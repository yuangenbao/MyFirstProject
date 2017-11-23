package com.example.ygb.myfirstproject.Activity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.ygb.myfirstproject.R;
import com.example.ygb.myfirstproject.Utils.DbManager;
import com.example.ygb.myfirstproject.Utils.MyDbHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewFileActivity extends AppCompatActivity {
    private EditText etTitle,etFile,etAuthor;
    private MyDbHelper helper;
    private Button btnSave,btnCancle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_file);
        
        initView();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!"".equals(etTitle.getText().toString())&&!"".equals(etFile.getText().toString())){
                    alertDialog();
                }else{
                    Toast.makeText(NewFileActivity.this,"请确保信息完善！！",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initView() {
        etFile= (EditText) findViewById(R.id.et_file);
        etTitle= (EditText) findViewById(R.id.et_title);
        etAuthor= (EditText) findViewById(R.id.et_author);
        btnSave= (Button) findViewById(R.id.btn_save);
        btnCancle= (Button) findViewById(R.id.btn_cancle);
        helper= DbManager.getHelperIntance(this);
    }

    public  String getTimeNow(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        Date date=new Date();
        String dateString=sdf.format(date);
        return dateString;
    }

    public String getAuthorName(){
       if (!"".equals(etAuthor.getText().toString())){
            return etAuthor.getText().toString();
       }else {
           return "匿名";
       }
    }


    //弹出提示框
    public void alertDialog(){
        new AlertDialog.Builder(this).setTitle("提示")//设置对话框标题
                .setMessage("是否确定保存？")//设置显示的内容
                .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮

                    @Override

                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                        SQLiteDatabase dbInsert=helper.getWritableDatabase();
                        dbInsert.execSQL("insert into infoBook("+
                                MyDbHelper.TABLE_TITLES+","+MyDbHelper.TABLE_AUTHOR+","+
                                MyDbHelper.TABLE_FILES+","+MyDbHelper.TABLE_DATE+")" + " values (?,?,?,?);"
                                ,new Object[]{etTitle.getText(),getAuthorName(),etFile.getText(),getTimeNow()});
                        Toast.makeText(NewFileActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                        // TODO Auto-generated method stub
                        dbInsert.close();
                        finish();

                    }

                }).setNegativeButton("取消",new DialogInterface.OnClickListener() {//添加返回按钮
            @Override

            public void onClick(DialogInterface dialog, int which) {//响应事件

                // TODO Auto-generated method stub

            }

        }).show();//在按键响应事件中显示此对话框

    }
}
