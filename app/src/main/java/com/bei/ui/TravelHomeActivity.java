package com.bei.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.bei.R;
import com.bei.adapter.TravelPartAdapter;
import com.bei.bean.TravelPart;
import com.bei.bean.Travels;
import com.bei.common.BaseActivity;
import com.bei.ui.fragment.ImagePagerFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by xinwenbo on 15/10/8.
 */
public class TravelHomeActivity extends BaseActivity {

    //    private DrawerLayout mDrawerLayout;
    private RecyclerView recyclerView;
    //    private ListView dateListView;
    private TravelPartAdapter adapter;
    private List<TravelPart> travelPartList;

    @Override
    public void init(Bundle savedInstanceState) {
        setContentView(R.layout.a_travel_home);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        Intent intent = getIntent();
        Travels travels = (Travels) intent.getSerializableExtra("travel");

        setToolbar(R.mipmap.ic_arrow_back, travels.getTitle());
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(travels.getTitle());

//        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_main_drawer);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        dateListView = (ListView) findViewById(R.id.date_list);

        BmobQuery<TravelPart> query = new BmobQuery<>();
        query.addWhereRelatedTo("parts", new BmobPointer(travels));
        query.findObjects(this, new FindListener<TravelPart>() {

            @Override
            public void onSuccess(List<TravelPart> object) {
                travelPartList = object;
                adapter = new TravelPartAdapter(travelPartList);
                adapter.setOnCoverClickListener(new TravelPartAdapter.OnCoverClickListener() {
                    @Override
                    public void onCoverClick(View view, TravelPart travelPart) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("part", travelPart);
                        getSupportFragmentManager().beginTransaction().replace(Window.ID_ANDROID_CONTENT, ImagePagerFragment.getInstance(bundle), "ImagePagerFragment")
                                .addToBackStack(null).commit();
                    }
                });
                recyclerView.setAdapter(adapter);

//                List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
//                for (TravelPart t : travelPartList) {
//                    Map<String, String> map = new HashMap<String, String>();
//                    map.put("date", t.getDate().getDate().substring(0, 10));
//                }

//                dateListView.setAdapter(new SimpleAdapter(TravelHomeActivity.this, maps, R.layout.i_travel_home_date, new String[]{"date"}, new int[]{R.id.item_date}));
            }

            @Override
            public void onError(int code, String msg) {
                Log.e("TravelHomeActivity", "查询失败：" + code + "-" + msg);
            }
        });
    }


    public void Test(View view) {
        startActivity(new Intent(this, TravelPublishActivity.class));
    }
}
