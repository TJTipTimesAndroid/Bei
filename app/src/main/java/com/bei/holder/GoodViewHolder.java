package com.bei.holder;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bei.R;
import com.bei.widget.FlowLayout;
import com.facebook.drawee.view.SimpleDraweeView;

import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by tiptimes on 15/8/7.
 */
public class GoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private FlowLayout itemTagContainer;
    private SimpleDraweeView itemCover;
    private TextView itemTitle;
    private SimpleDraweeView itemAvatar;
    private TextView itemName;
    private TextView itemCreateTime;
    private TextView itemLikeCount;


    public GoodViewHolder(View itemView, FlowLayout itemTagContainer, SimpleDraweeView itemCover, TextView itemTitle, SimpleDraweeView itemAvatar, TextView itemName, TextView itemCreateTime, TextView itemLikeCount) {
        super(itemView);
        this.itemTagContainer = itemTagContainer;
        this.itemCover = itemCover;
        this.itemTitle = itemTitle;
        this.itemAvatar = itemAvatar;
        this.itemName = itemName;
        this.itemCreateTime = itemCreateTime;
        this.itemLikeCount = itemLikeCount;
        this.itemView.setOnClickListener(this);
    }


    public static GoodViewHolder newInstance(View itemView) {

        FlowLayout itemTagContainer = (FlowLayout) itemView.findViewById(R.id.item_tag_container);
        SimpleDraweeView itemCover = (SimpleDraweeView) itemView.findViewById(R.id.item_cover);
        TextView itemTitle = (TextView) itemView.findViewById(R.id.item_title);
        SimpleDraweeView itemAvatar = (SimpleDraweeView) itemView.findViewById(R.id.item_author_avatar);
        TextView itemName = (TextView) itemView.findViewById(R.id.item_author_name);
        TextView itemCreateTime = (TextView) itemView.findViewById(R.id.item_create_time);
        TextView itemLikeCount = (TextView) itemView.findViewById(R.id.item_likes_count);
        return new GoodViewHolder(itemView, itemTagContainer, itemCover, itemTitle, itemAvatar, itemName, itemCreateTime, itemLikeCount);
    }

    public void setItemTag(Context context, String tag) {
        String[] tags = tag.split(" ");
        for (String t : tags) {
//            Log.e("setItemTag", "tag --> " + t);
            TextView tagView = (TextView) ((Activity) context).getLayoutInflater().inflate(R.layout.v_travle_tag, null);
            tagView.setText(t);
            this.itemTagContainer.addView(tagView);
        }
    }

    public void setItemCover(Context context, BmobFile cover) {
        this.itemCover.setImageURI(Uri.parse(cover.getFileUrl(context)));
    }

    public void setItemTitle(String title) {
        this.itemTitle.setText(title);
    }

    public void setItemAvatar(Context context, BmobFile avatar) {
        avatar.loadImage(context, this.itemAvatar);
    }

    public void setItemName(String name) {
        this.itemName.setText(name);
    }

    public void setItemCreateTime(String create_time) {
        this.itemCreateTime.setText(create_time);
    }

    public void setItemLikeCount(String like_count) {
        this.itemLikeCount.setText(like_count);
    }

    @Override
    public void onClick(View view) {

    }
}
