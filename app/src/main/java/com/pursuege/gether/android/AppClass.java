package com.pursuege.gether.android;

import android.app.Application;

import com.pursuege.gether.android.utils.FileOperate;

/**
 * Created by wangtao on 2017/5/12.
 */

public class AppClass extends Application {

    @Override
    public void onCreate() {
        FileOperate.init();
    }
}
