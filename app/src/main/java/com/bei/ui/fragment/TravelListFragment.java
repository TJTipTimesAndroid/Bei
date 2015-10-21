package com.bei.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bei.R;
import com.bei.adapter.TravelAdapter;
import com.bei.bean.Travels;
import com.bei.widget.SwipeRecyclerView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class TravelListFragment extends Fragment {

    public final static String KEY_TRAVEL_TYPE = "TRAVEL_TYPE";
    public int TRAVEL_TYPE = -1;
    public final static int HOME_TRAVEL = 0;
    public final static int SCENIC_TRAVEL = 1;


    private SwipeRecyclerView swipeRecyclerView;
    private TravelAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        TRAVEL_TYPE = bundle.getInt(KEY_TRAVEL_TYPE);
        return inflater.inflate(R.layout.f_travel_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRecyclerView = (SwipeRecyclerView) view.findViewById(R.id.swipe_recycler_view);
        adapter = new TravelAdapter();
        swipeRecyclerView.setAdapter(adapter, Travels.class);
        swipeRecyclerView.addInclude("author");
        swipeRecyclerView.setRefreshabled(TRAVEL_TYPE == HOME_TRAVEL);
        swipeRecyclerView.start();
    }

}
