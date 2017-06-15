package com.pursuege.gether.android;

import android.app.Application;

import com.fiiipay.grand.android.utils.MyEventBusIndex;
import com.orhanobut.logger.Logger;
import com.pursuege.gether.android.utils.FileOperate;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by wangtao on 2017/5/12.
 */

public class AppClass extends Application {

    @Override
    public void onCreate() {
        FileOperate.init();
        Logger.init("Logger_info").methodCount(2).hideThreadInfo();
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
    }
}
