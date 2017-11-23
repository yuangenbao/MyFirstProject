package com.example.ygb.myfirstproject.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ygb.myfirstproject.Fragment.CalculatorFragment;
import com.example.ygb.myfirstproject.Fragment.InfoFragment;
import com.example.ygb.myfirstproject.Fragment.MemoFragment;
import com.example.ygb.myfirstproject.R;

import java.util.ArrayList;
import java.util.List;

public class  MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<String> tabNameList=new ArrayList<>();
    private List<Fragment> fragmentList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        ininView();
        
    }



    private void initData() {
        //填充标签名称
        tabNameList.add(getString(R.string.fragment_calculator));
        tabNameList.add(getString(R.string.fragment_info));
        tabNameList.add(getString(R.string.fragment_memo));

        //填充Fragment
        fragmentList.add(new CalculatorFragment());
        fragmentList.add(new InfoFragment());
        fragmentList.add(new MemoFragment());
    }

    private void ininView() {
        mTabLayout= (TabLayout) findViewById(R.id.tl_tablayout);
        mViewPager= (ViewPager) findViewById(R.id.vp_viewpager);

        //设置ViewPager的adapter
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
              //  return 0;
                return fragmentList.size();
            }

            @Override
            public Fragment getItem(int position) {
                //return null;4
                return fragmentList.get(position);
            }


            //设置tab名称
            @Override
            public CharSequence getPageTitle(int position) {
               // return super.getPageTitle(position);
                return tabNameList.get(position);
            }
        });

        //绑定TableLayout和viewpager
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
