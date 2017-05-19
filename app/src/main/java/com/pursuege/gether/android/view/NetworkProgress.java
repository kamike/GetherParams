package com.pursuege.gether.android.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.pursuege.gether.android.R;

/**
 * Created by wangtao on 2017/5/15.
 */

public class NetworkProgress extends Dialog {
    public NetworkProgress(Context context) {
        super(context, R.style.dialog_all_style);
        Activity a= (Activity) context;
        if(a==null){
            return;
        }
        if(a.isFinishing()){
            return;
        }
        show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress_network);
    }
}
