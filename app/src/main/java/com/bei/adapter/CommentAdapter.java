package com.bei.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bei.R;
import com.bei.bean.Comment;
import com.bei.bean.Scenic;
import com.bei.bean.Travels;
import com.bei.bean.User;
import com.bei.holder.CommentViewHolder;

import cn.bmob.v3.BmobUser;

/**
 * Created by tiptimes on 15/8/7.
 */
public class CommentAdapter extends BaseAdapter {

    private Context mContext;

    public CommentAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.i_comment, viewGroup, false);
        return CommentViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        super.onBindViewHolder(viewHolder, i);
        final CommentViewHolder holder = (CommentViewHolder) viewHolder;
        final Comment comment = (Comment) dataList.get(i);
        if (comment.getFrom() != null) {
            if (comment.getFrom().getAvatar() != null) {
                holder.setItemAvatar(mContext, comment.getFrom().getAvatar());
            }
        } else {

        }

//        holder.setItemName(comment.getFrom().getUsername() == null ? " " : comment.getFrom().getUsername());
        holder.setItemContent(comment.getContent());
//        holder.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view) {
//                Log.e("CommentAdapter", "onCLick");
//                holder.addItemView();
//                add(new Comment(), 0);
//            }
//        });

    }

    public void add(Comment comment, int position) {
        dataList.add(position, comment);
        notifyItemInserted(position);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }
}
