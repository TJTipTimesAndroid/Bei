package com.bei.bean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by xinwenbo on 15/9/24.
 */
public class User extends BmobUser {

    private BmobFile avatar;

    public User() {
        this.setTableName("_User");
    }

    public BmobFile getAvatar() {
        return avatar;
    }

    public void setAvatar(BmobFile avatar) {
        this.avatar = avatar;
    }
}
