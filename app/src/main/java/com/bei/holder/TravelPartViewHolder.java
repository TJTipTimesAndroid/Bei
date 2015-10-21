package com.bei.holder;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bei.R;
import com.bei.adapter.OnItemClickListener;
import com.facebook.drawee.view.SimpleDraweeView;

import cn.bmob.v3.datatype.BmobFile;


/**
 * Created by tiptimes on 15/8/7.
 */
public class TravelPartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView itemDate;
    private SimpleDraweeView itemCover;
    private TextView itemContent;

    public TravelPartViewHolder(View itemView, TextView itemDate, SimpleDraweeView itemCover, TextView itemContent) {
        super(itemView);
        this.itemDate = itemDate;
        this.itemCover = itemCover;
        this.itemContent = itemContent;
    }


    public static TravelPartViewHolder newInstance(View itemView) {
        TextView itemDate = (TextView) itemView.findViewById(R.id.item_date);
        SimpleDraweeView itemCover = (SimpleDraweeView) itemView.findViewById(R.id.item_cover);
        TextView itemContent = (TextView) itemView.findViewById(R.id.item_content);
        return new TravelPartViewHolder(itemView, itemDate, itemCover, itemContent);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.itemCover.setOnClickListener(this);
        this.onItemClickListener = onItemClickListener;
    }

    public void setItemDate(String name) {
        this.itemDate.setText(name);
    }

    public void setItemCover(Context context, BmobFile avatar) {
        this.itemCover.setImageURI(Uri.parse(avatar.getFileUrl(context)));
    }

    public void setItemContent(String content) {
        this.itemContent.setText(content);
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(view);
        }
    }
}
