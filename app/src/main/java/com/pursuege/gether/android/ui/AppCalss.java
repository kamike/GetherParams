package com.pursuege.gether.android.ui;

import android.app.Application;

import com.pursuege.gether.android.utils.LogCrashHandler;

/**
 * Created by wangtao on 2017/5/9.
 */

public class AppCalss extends Application {
    @Override
    public void onCreate() {
        LogCrashHandler.getInstance().init(this);
    }
}
