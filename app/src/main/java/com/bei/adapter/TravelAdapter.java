package com.bei.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bei.R;
import com.bei.bean.Travels;
import com.bei.holder.TravelViewHolder;
import com.bei.ui.TravelHomeActivity;

/**
 * Created by tiptimes on 15/8/7.
 */
public class TravelAdapter extends BaseAdapter {

    private Context mContext;

    public TravelAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.i_travel, viewGroup, false);
        return TravelViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        TravelViewHolder holder = (TravelViewHolder) viewHolder;
        final Travels travels = (Travels) dataList.get(i);
//        holder.setItemTag(mContext,travels.getTag());
        holder.setItemCover(mContext, travels.getCover());
        holder.setItemTitle(travels.getTitle());
        holder.setItemName(travels.getAuthor().getUsername());
        holder.setItemAvatar(mContext, travels.getAuthor().getAvatar());
        holder.setItemCreateTime(travels.getCreatedAt());
        holder.setItemLikeCount(travels.getLikescount() + "");
        holder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationZ", 20, 0);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Intent intent = new Intent(mContext, TravelHomeActivity.class);
                        intent.putExtra("travel", travels);
                        mContext.startActivity(intent);
                    }
                });
                animator.start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }
}
