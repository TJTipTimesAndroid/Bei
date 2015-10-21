package com.bei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bei.R;
import com.bei.bean.TravelPart;
import com.bei.holder.TravelPartViewHolder;

import java.util.List;

/**
 * Created by tiptimes on 15/8/7.
 */
public class TravelPartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<TravelPart> travelPartList;

    public TravelPartAdapter(List<TravelPart> travelPartList) {
        this.travelPartList = travelPartList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.i_travel_home, viewGroup, false);
        return TravelPartViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        TravelPartViewHolder holder = (TravelPartViewHolder) viewHolder;
        final TravelPart travelPart = travelPartList.get(i);
        holder.setItemDate("第" + (i + 1) + "天:" + travelPart.getDate().getDate().substring(0, 10));
        holder.setItemContent(travelPart.getContent());
        holder.setItemCover(mContext, travelPart.getCover());


        holder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                onCoverClickListener.onCoverClick(view, travelPart);
            }
        });
    }

    @Override
    public int getItemCount() {
        return travelPartList == null ? 0 : travelPartList.size();
    }

    private OnCoverClickListener onCoverClickListener = null;

    public void setOnCoverClickListener(OnCoverClickListener onCoverClickListener) {
        this.onCoverClickListener = onCoverClickListener;
    }

    public interface OnCoverClickListener {
        void onCoverClick(View view, TravelPart travelPart);
    }
}
