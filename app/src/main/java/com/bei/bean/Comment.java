package com.bei.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by xinwenbo on 15/9/24.
 */
public class Comment extends BmobObject{

    private User from;
    private User to;
    private Scenic scenic;
    private String content;

    public Comment() {
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public Scenic getScenic() {
        return scenic;
    }

    public void setScenic(Scenic scenic) {
        this.scenic = scenic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
