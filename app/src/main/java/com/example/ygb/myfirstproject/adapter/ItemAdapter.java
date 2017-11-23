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

import java.util.List;


/**
 * Created by YuanBuyuan on 2017/11/9 0009.
 */

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private List<ItemData> mList;
    private LayoutInflater inflater;//加载布局
    private ItemData itemData;

    public ItemAdapter(Context context, List<ItemData> mList) {
        this.context = context;
        this.mList = mList;
        /*
            获取到LayoutInflater的实例，有两种方法
            1.简写
            LayoutInflater layoutInflater=LayoutInflater.form(context);
            2.完成同样效果
            LayoutInflater inflater = (LayoutInflater) context.
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         */
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 当系统开始绘制ListView的时候，首先调用getCount方法，得到返回值，即ListView的长度
     * @return
     */
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
        /*
        if (view==null){
            view=inflater.inflate(R.layout.item,null);
        }
        ImageView imageView=view.findViewById(R.id.iv_image);
        TextView itemName=view.findViewById(R.id.tv_itemName);
        TextView itemDesc=view.findViewById(R.id.tv_itemDesc);
        TextView itemThird=view.findViewById(R.id.tv_itemThird);
        imageView.setImageResource(itemData.getItemImageId());
        itemName.setText(itemData.getItemName());
        itemDesc.setText(itemData.getItemDesc());
        itemThird.setText(itemData.getItemThird());
        return view;
        */

        //代码优化
        ViewHolder viewHolder;
        if (view==null){
            view=inflater.inflate(R.layout.item,null);

            viewHolder=new ViewHolder();
            //viewHolder.imageView=view.findViewById(R.id.iv_image);
            viewHolder.itemName=view.findViewById(R.id.tv_itemName);
            viewHolder.itemDesc=view.findViewById(R.id.tv_itemDesc);
            viewHolder.itemAuthor=view.findViewById(R.id.tv_author);
            viewHolder.itemThird=view.findViewById(R.id.tv_itemThird);
                /*
                缓存显示数据的视图（View）,以便加快UI的响应速度；
                */
            view.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) view.getTag();
        }
        itemData=mList.get(i);
        //viewHolder.imageView.setImageResource(itemData.getItemImageId());
        viewHolder.itemName.setText(itemData.getItemName());
        viewHolder.itemDesc.setText(itemData.getItemDesc());
        viewHolder.itemAuthor.setText(itemData.getItemAuthor());
        viewHolder.itemThird.setText(itemData.getItemThird());


        return view;
    }

     class ViewHolder{
         public TextView itemName,itemDesc,itemThird,itemAuthor;
         public ImageView imageView;
     }
}
