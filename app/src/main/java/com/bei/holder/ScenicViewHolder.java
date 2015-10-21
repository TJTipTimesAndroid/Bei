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
public class ScenicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView itemName;
    private SimpleDraweeView itemCover;

    public ScenicViewHolder(View itemView, TextView itemName, SimpleDraweeView itemCover) {
        super(itemView);
        this.itemName = itemName;
        this.itemCover = itemCover;
        this.itemCover.setOnClickListener(this);
    }


    public static ScenicViewHolder newInstance(View itemView) {
        TextView itemName = (TextView) itemView.findViewById(R.id.item_name);
        SimpleDraweeView itemCover = (SimpleDraweeView) itemView.findViewById(R.id.item_cover);
        return new ScenicViewHolder(itemView, itemName, itemCover);
    }


    public void setItemName(String name) {
        this.itemName.setText(name);
    }

    public void setItemCover(Context context, BmobFile cover) {
        this.itemCover.setImageURI(Uri.parse(cover.getFileUrl(context)));
//        cover.loadImage(context, this.itemCover);
    }

    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_cover:
                onItemClickListener.onItemClick(view);
                break;
        }
    }

}
