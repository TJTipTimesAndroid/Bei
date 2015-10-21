package com.bei.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bei.R;
import com.bei.adapter.GoodsAdapter;
import com.bei.bean.Travels;
import com.bei.widget.SwipeRecyclerView;


/**
 * Created by xinwenbo on 15/9/1.
 */
public class GoodsListFragment extends Fragment {

    private SwipeRecyclerView swipeRecyclerView;
    private GoodsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_goods_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRecyclerView = (SwipeRecyclerView) view.findViewById(R.id.swipe_recycler_view);
        swipeRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new GoodsAdapter();
        swipeRecyclerView.setAdapter(adapter, Travels.class);
        swipeRecyclerView.start();
    }
}
