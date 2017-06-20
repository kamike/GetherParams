package com.pursuege.gether.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.orhanobut.logger.Logger;
import com.pursuege.gether.android.bean.ServiceIpBean;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wangtao on 2017/6/16.
 */

public class NetworkUtils {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    public static void doGetForUrlTime(final ServiceIpBean serviceIp) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(serviceIp.ipAddress)
                .build();

        final long start = System.currentTimeMillis();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.i("exception:"+e.getLocalizedMessage());
                serviceIp.delayTime=-1;
                EventBus.getDefault().post(serviceIp);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                long end = System.currentTimeMillis();
                serviceIp.delayTime= (int) (end - start);
                EventBus.getDefault().post(serviceIp);

            }
        });


    }


}
