package com.bei.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.bei.R;
import com.bei.adapter.pager.FragmentPagerItemAdapter;
import com.bei.adapter.pager.FragmentPagerItems;
import com.bei.bean.Scenic;
import com.bei.common.BaseActivity;
import com.bei.ui.fragment.ScenicCommentsFragment;
import com.bei.ui.fragment.ScenicIntroduceFragment;
import com.bei.ui.fragment.TravelListFragment;
import com.flaviofaria.kenburnsview.KenBurnsView;


/**
 * Created by xinwenbo on 15/8/11.
 */
public class ScenicHomeActivity extends BaseActivity {

    private KenBurnsView headerImage;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private static Scenic scenic;

    @Override
    public void init(Bundle savedInstanceState) {
        setContentView(R.layout.a_scenic_home);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        Intent intent = getIntent();
        scenic = (Scenic) intent.getSerializableExtra(Scenic.class.getSimpleName());
        headerImage = (KenBurnsView) findViewById(R.id.backdrop);
        scenic.getCover().loadImage(this, headerImage);

        setToolbar(R.mipmap.ic_arrow_back, " ");

        setupViewPager();
    }

    private void setupViewPager() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        String[] titles = {"简介", "点评", "游记"};

        for (int i = 0; i < titles.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles[i]));
        }

        Bundle introduceBundle = new Bundle();
        introduceBundle.putSerializable(Scenic.class.getSimpleName(), scenic);

        Bundle travelBundle = new Bundle();
        travelBundle.putInt(TravelListFragment.KEY_TRAVEL_TYPE, TravelListFragment.SCENIC_TRAVEL);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(titles[0], ScenicIntroduceFragment.class, introduceBundle)
                .add(titles[1], ScenicCommentsFragment.class)
                .add(titles[2], TravelListFragment.class, travelBundle)
                .create());

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }

    public static Scenic getScenic() {
        return scenic;
    }
}
