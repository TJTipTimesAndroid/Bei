package com.bei.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bei.R;
import com.bei.adapter.pager.FragmentPagerItemAdapter;
import com.bei.adapter.pager.FragmentPagerItems;
import com.bei.bean.Scenic;
import com.bei.common.BaseActivity;
import com.bei.ui.fragment.GoodsListFragment;
import com.bei.ui.fragment.ScenicIntroduceFragment;
import com.bei.ui.fragment.ScenicListFragment;
import com.bei.ui.fragment.TravelListFragment;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    public void init(Bundle savedInstanceState) {
        setContentView(R.layout.a_main);
        setupViewPager();
        setupFloatButton();
    }

    private void setupViewPager() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        String[] titles = {"游记", "景区", "食宿"};
        for(int i = 0;i< titles.length;i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(titles[i]));
        }
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(titles[0], TravelListFragment.class)
                .add(titles[1], ScenicListFragment.class)
                .add(titles[2], GoodsListFragment.class)
                .create());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }

    private void setupFloatButton(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(BmobUser.getCurrentUser(MainActivity.this) != null){

                }else{
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }
}
