package com.example.ygb.myfirstproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ygb.myfirstproject.R;
import com.example.ygb.myfirstproject.entity.ItemData;
import com.example.ygb.myfirstproject.entity.MemoItemData;

import java.util.List;

/**
 * Created by YuanBuyuan on 2017/11/23 0023.
 */

public class MemoItemAdapter extends BaseAdapter {
    private Context context;
    private List<MemoItemData> mList;
    private LayoutInflater inflater;//加载布局
    private MemoItemData memoItemData;


    public MemoItemAdapter(Context context,List<MemoItemData> list){
        this.context=context;
        this.mList=list;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.memo_item,null);
            holder.itemStatus=view.findViewById(R.id.tv_status);
            holder.itemYMD=view.findViewById(R.id.tv_ymd);
            holder.itemHm=view.findViewById(R.id.tv_hm);

            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        memoItemData=mList.get(i);

        holder.itemStatus.setText(memoItemData.getStatus());
        holder.itemYMD.setText(memoItemData.getTimeYmd());
        holder.itemHm.setText(memoItemData.getTimeHm());
        return view;
    }

    class ViewHolder{
        public TextView itemStatus,itemYMD,itemHm;
    }
}
