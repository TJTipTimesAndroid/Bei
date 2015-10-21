package com.bei.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by xinwenbo on 15/10/8.
 */
public class TravelPart extends BmobObject {

    private String content;
    private BmobDate date;
    private BmobFile cover;
    private BmobRelation pics;

    public TravelPart() {
    }

    public BmobDate getDate() {
        return date;
    }

    public void setDate(BmobDate date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BmobFile getCover() {
        return cover;
    }

    public void setCover(BmobFile cover) {
        this.cover = cover;
    }

    public BmobRelation getPics() {
        return pics;
    }

    public void setPics(BmobRelation pics) {
        this.pics = pics;
    }
}
