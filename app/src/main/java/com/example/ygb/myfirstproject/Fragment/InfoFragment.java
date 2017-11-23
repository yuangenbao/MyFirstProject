package com.example.ygb.myfirstproject.Fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ygb.myfirstproject.Activity.NewFileActivity;
import com.example.ygb.myfirstproject.Activity.UpdateFileActivity;
import com.example.ygb.myfirstproject.R;
import com.example.ygb.myfirstproject.Utils.DbManager;
import com.example.ygb.myfirstproject.Utils.MyDbHelper;
import com.example.ygb.myfirstproject.adapter.ItemAdapter;
import com.example.ygb.myfirstproject.entity.ItemData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuanBuyuan on 2017/10/26 0026.
 */

public class InfoFragment extends Fragment {

    private List<ItemData> mList;
    private ListView listView;
    private TextView tvTitle;
    private Button btnNewFile;
    private MyDbHelper helper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      //  return super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.fragment_info,null);

        helper= DbManager.getHelperIntance(getActivity());
        helper.getWritableDatabase();
        listView= (ListView) view.findViewById(R.id.lv);
        btnNewFile= (Button) view.findViewById(R.id.btn_newFile);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this,"tew"+i,Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(getActivity(),UpdateFileActivity.class);
                intent.putExtra("titleUp",mList.get(i).getItemName().toString());
                intent.putExtra("fileUp",mList.get(i).getItemDesc().toString());
                intent.putExtra("authorUp",mList.get(i).getItemAuthor().toString());
                intent.putExtra("id",mList.get(i).getId().toString());

                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String id=mList.get(i).getId().toString();
                new AlertDialog.Builder(getActivity()).setTitle("提示")//设置对话框标题
                        .setMessage("是否确定删除？")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮

                            @Override

                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                                SQLiteDatabase dbDel=helper.getWritableDatabase();

                                dbDel.delete("infoBook","id=?",new String[] {id});
                                // TODO Auto-generated method stub
                                dbDel.close();
                                onStart();
                            }

                        }).setNegativeButton("取消",new DialogInterface.OnClickListener() {//添加返回按钮
                    @Override

                    public void onClick(DialogInterface dialog, int which) {//响应事件

                        // TODO Auto-generated method stub

                    }

                }).show();
                return true;
            }
        });
        btnNewFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),NewFileActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        refreshList();
    }


    private void  refreshList(){
        this.mList=new ArrayList<>();
        SQLiteDatabase dbQuery=helper.getWritableDatabase();
        Cursor cursor=dbQuery.rawQuery("select * from infoBook",new String[]{});

        if (cursor.moveToLast()){
            do{
                String titles=cursor.getString(cursor.getColumnIndex("titles"));
                String author=cursor.getString(cursor.getColumnIndex("author"));
                String files=cursor.getString(cursor.getColumnIndex("files"));
                String date=cursor.getString(cursor.getColumnIndex("date"));
                String id=cursor.getString(cursor.getColumnIndex("id"));
                ItemData itemData=new ItemData(titles,files,author,"更新时间："+date,id);
                mList.add(itemData);
            }while (cursor.moveToPrevious());
        }

        cursor.close();
        dbQuery.close();
        ItemAdapter itemAdapter=new ItemAdapter(getActivity(),mList);
        listView.setAdapter(itemAdapter);
    }

}

