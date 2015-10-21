package com.bei.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.bei.R;
import com.bei.adapter.CommentAdapter;
import com.bei.bean.Comment;
import com.bei.bean.Scenic;
import com.bei.ui.ScenicHomeActivity;
import com.bei.widget.SwipeRecyclerView;

import jp.wasabeef.recyclerview.animators.FadeInRightAnimator;


public class ScenicCommentsFragment extends Fragment {

    private SwipeRecyclerView swipeRecyclerView;
    private CommentAdapter adapter;
    private Scenic scenic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_scenic_comment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRecyclerView = (SwipeRecyclerView) view.findViewById(R.id.swipe_recycler_view);
        swipeRecyclerView.setRefreshabled(false);
        adapter = new CommentAdapter();
        swipeRecyclerView.setAdapter(adapter, Comment.class);
        swipeRecyclerView.addInclude("from,to,scenic");
        swipeRecyclerView.addWhere("scenic", ScenicHomeActivity.getScenic());
        swipeRecyclerView.getRecyclerView().setItemAnimator(new FadeInRightAnimator(new OvershootInterpolator(1f)));
        swipeRecyclerView.getRecyclerView().getItemAnimator().setAddDuration(500);
        swipeRecyclerView.getRecyclerView().getItemAnimator().setRemoveDuration(500);
        swipeRecyclerView.start();
    }

}
