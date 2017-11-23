package com.example.ygb.myfirstproject.Activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ygb.myfirstproject.R;
import com.example.ygb.myfirstproject.adapter.GuideAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private View view1,view2,view3;
    private List<View> mList=new ArrayList<>();
    private ImageView iv1,iv2,iv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        //初始化指示器
        iv1= (ImageView) findViewById(R.id.iv_point1);
        iv2= (ImageView) findViewById(R.id.iv_point2);
        iv3= (ImageView) findViewById(R.id.iv_point3);

        //设定指示器的初始状态
        iv1.setImageResource(R.drawable.indicator);
        iv2.setImageResource(R.drawable.indicator2);
        iv3.setImageResource(R.drawable.indicator2);


        mViewPager= (ViewPager) findViewById(R.id.vp_viewpager);

        view1=View.inflate(this,R.layout.view1,null);
        view2=View.inflate(this,R.layout.view2,null);
        view3=View.inflate(this,R.layout.view3,null);

        mList.add(view1);
        mList.add(view2);
        mList.add(view3);

        //绑定adatper
        mViewPager.setAdapter(new GuideAdapter(mList));

        //滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        iv1.setImageResource(R.drawable.indicator);
                        iv2.setImageResource(R.drawable.indicator2);
                        iv3.setImageResource(R.drawable.indicator2);
                        break;
                    case 1:
                        iv1.setImageResource(R.drawable.indicator2);
                        iv2.setImageResource(R.drawable.indicator);
                        iv3.setImageResource(R.drawable.indicator2);
                        break;
                    case 2:
                        iv1.setImageResource(R.drawable.indicator2);
                        iv2.setImageResource(R.drawable.indicator2);
                        iv3.setImageResource(R.drawable.indicator);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void jumpFun(View view){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
