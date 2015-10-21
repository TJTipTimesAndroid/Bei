package com.bei.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by xinwenbo on 15/8/11.
 */
public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List dataList;

    private OnLoadMoreListener onLoadMoreListener = null;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (position == getItemCount() - 1) {
            onLoadMoreListener.onLoadMore();
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }


    public interface OnLoadMoreListener {
        void onLoadMore();
    }



    public void setDataList(List dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public List getDataList() {
        return dataList;
    }
}
