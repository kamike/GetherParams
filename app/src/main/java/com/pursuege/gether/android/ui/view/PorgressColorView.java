package com.pursuege.gether.android.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by wangtao on 2017/5/5.
 */

public class PorgressColorView extends View {
    private int maxValue;

    public PorgressColorView(Context context) {
        super(context,null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    public void setMaxValue(int maxValue){
        this.maxValue=maxValue;
    }
}
