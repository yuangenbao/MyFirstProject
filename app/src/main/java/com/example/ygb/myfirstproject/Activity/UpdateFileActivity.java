package com.example.ygb.myfirstproject.Activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
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

public class UpdateFileActivity extends AppCompatActivity {
    private EditText etTitle,etFile,etAuthor;
    private MyDbHelper helper;
    private Button btnUpdate,btnCancle;
    private String itemID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_file);

        initView();
        initData();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!"".equals(etTitle.getText().toString())&&!"".equals(etFile.getText().toString())){
                    alertUpDialog();
                }else {
                    Toast.makeText(UpdateFileActivity.this,"信息不能为空",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void initData() {
        Intent intent=getIntent();
        String title=intent.getStringExtra("titleUp");
        String file=intent.getStringExtra("fileUp");
        String author=intent.getStringExtra("authorUp");
        itemID=intent.getStringExtra("id");
        etTitle.setText(title);
        etFile.setText(file);
        etAuthor.setText(author);
    }

    private void initView() {
        etFile= (EditText) findViewById(R.id.et_fileUp);
        etTitle= (EditText) findViewById(R.id.et_titleUp);
        etAuthor= (EditText) findViewById(R.id.et_authorUp);
        btnUpdate= (Button) findViewById(R.id.btn_update);
        btnCancle= (Button) findViewById(R.id.btn_cancleUp);
        helper= DbManager.getHelperIntance(this);
    }

    public String getDateString(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=new Date();
        String dateString=format.format(date);
        return dateString;
    }
    //弹出提示框
    public void alertUpDialog(){
        new AlertDialog.Builder(this).setTitle("提示")//设置对话框标题
                .setMessage("是否确定修改？")//设置显示的内容
                .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮

                    @Override

                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                        SQLiteDatabase dbUpdate=helper.getWritableDatabase();
                        ContentValues cv=new ContentValues();
                        cv.put(MyDbHelper.TABLE_TITLES,etTitle.getText().toString());
                        cv.put(MyDbHelper.TABLE_FILES,etFile.getText().toString());
                        cv.put(MyDbHelper.TABLE_AUTHOR,etAuthor.getText().toString());
                        cv.put(MyDbHelper.TABLE_DATE,getDateString());
                        dbUpdate.update(MyDbHelper.TABLE,cv,"id=?",new String[]{itemID});
                        Toast.makeText(UpdateFileActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                        // TODO Auto-generated method stub
                        dbUpdate.close();
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
