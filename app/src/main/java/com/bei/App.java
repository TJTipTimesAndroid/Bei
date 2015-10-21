package com.bei;


import com.bei.bean.User;
import com.bei.utils.Sputil;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;

import java.util.HashSet;
import java.util.Set;

import cn.bmob.v3.Bmob;

/**
 * Created by tiptimes on 15/8/6.
 */
public class App extends android.app.Application {

    private static final String BMOB_KEY = "5da2ec5f294e0ef6a36d638a1fe0058b";
    private static App mApp;

    private User currentUser;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;

        FLog.setMinimumLoggingLevel(FLog.DEBUG);
        Set<RequestListener> listeners = new HashSet<>();
        listeners.add(new RequestLoggingListener());
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this).setRequestListeners(listeners).build();
        Fresco.initialize(this, config);

        Bmob.initialize(this, BMOB_KEY);
    }

    public static App getInstance() {
        return mApp;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
