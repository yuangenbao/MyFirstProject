package com.example.ygb.myfirstproject.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by YuanBuyuan on 2017/10/19 0019.
 */

public class GuideAdapter extends PagerAdapter{
    private List<View> mList;

    //viewpager用view.list用于传递view
    public GuideAdapter(List<View> mList){
        this.mList=mList;
    }

    /**
     *
     *返回需要滑动的View个数
     * @return
     */
    @Override
    public int getCount() {
        return mList.size();
    }

    /**
     *  判断instantiateItem返回的view与当前的view是否匹配
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    /**
     *把当前的view添加cantainer里，并且返回当前的view
     *
     * @param container 视图容器
     * @param position 当前view的位置
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       // return super.instantiateItem(container, position);
        ((ViewPager)container).addView(mList.get(position));
        return mList.get(position);
    }

    /**
     * 从contains中销移除指定的view
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       // super.destroyItem(container, position, object);
        ((ViewPager)container).removeView(mList.get(position));
    }
}
