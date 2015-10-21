package com.bei.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bei.R;
import com.bei.bean.Scenic;
import com.bei.holder.ScenicViewHolder;
import com.bei.ui.ScenicHomeActivity;
import com.bei.ui.TravelHomeActivity;


/**
 * Created by tiptimes on 15/8/7.
 */
public class ScenicAdapter extends BaseAdapter {

    private Context mContext;

    public ScenicAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.i_scenic, viewGroup, false);
        return ScenicViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        ScenicViewHolder holder = (ScenicViewHolder) viewHolder;
        final Scenic scenic = (Scenic) dataList.get(i);
        holder.setItemName(scenic.getName());
        holder.setItemCover(mContext, scenic.getCover());
        holder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(Scenic.class.getSimpleName(), scenic);
                intent.setClass(mContext, ScenicHomeActivity.class);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }
}
