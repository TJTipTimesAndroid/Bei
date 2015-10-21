package com.bei.holder;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bei.R;
import com.bei.adapter.OnItemClickListener;
import com.facebook.drawee.view.SimpleDraweeView;

import cn.bmob.v3.datatype.BmobFile;


/**
 * Created by tiptimes on 15/8/7.
 */
public class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private SimpleDraweeView itemAvatar;
    private TextView itemName;
    private EditText itemContent;
    private LinearLayout itemReply;

    public CommentViewHolder(View itemView, SimpleDraweeView itemAvatar, TextView itemName, EditText itemContent, LinearLayout itemReply) {
        super(itemView);
        this.itemView.setOnClickListener(this);
        this.itemAvatar = itemAvatar;
        this.itemName = itemName;
        this.itemContent = itemContent;
        this.itemReply = itemReply;
    }


    public static CommentViewHolder newInstance(View itemView) {
        SimpleDraweeView itemAvatar = (SimpleDraweeView) itemView.findViewById(R.id.item_author_avatar);
        TextView itemName = (TextView) itemView.findViewById(R.id.item_name);
        EditText itemContent = (EditText) itemView.findViewById(R.id.item_content);
        LinearLayout itemReply = (LinearLayout) itemView.findViewById(R.id.item_reply);
        return new CommentViewHolder(itemView, itemAvatar, itemName, itemContent, itemReply);
    }

    private OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setItemName(String name) {
        this.itemName.setText(name);
    }

    public void setItemAvatar(Context context, BmobFile avatar) {
        this.itemAvatar.setImageURI(Uri.parse(avatar.getFileUrl(context)));
    }

    public void setItemContent(String content){
        this.itemContent.setText(content);
    }


    @Override
    public void onClick(View view) {
        onItemClickListener.onItemClick(view);
    }
}
