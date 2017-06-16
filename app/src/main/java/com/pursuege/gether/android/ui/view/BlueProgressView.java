package com.pursuege.gether.android.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.pursuege.gether.android.R;

/**
 * Created by wangtao on 2017/6/8.
 */

public class BlueProgressView extends View {

    private int viewWidth;
    private int viewHeight;
    private Paint paint;
    private  int backColor;

    public BlueProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.progress_attr);
        //backColor=typedArray.getColor(R.styleable.progress_attr_back_color,0x14A4f9);
        typedArray.recycle();
        paint = new Paint();

        rectBg = new RectF(0, 0, 0, 0);
        rectF = new RectF(0, 0, 0, 0);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.viewWidth = w;
        this.viewHeight = h;
        rectBg.right = w;
        rectBg.bottom = h;
        rectF.bottom = h;

    }

    private RectF rectBg;
    private RectF rectF;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        paint.setColor(backColor);
        paint.setColor(Color.parseColor("#14A4f9"));
        canvas.drawRoundRect(rectBg, viewHeight/2, viewHeight/2, paint);

        paint.setColor(Color.parseColor("#017FE9"));
        rectF.right=progress*viewWidth;
        canvas.drawRoundRect(rectF, viewHeight/2, viewHeight/2, paint);


    }
    private float progress=0.5f;

    public void setCurrentProgress(float progress){
        this.progress=progress;
        invalidate();
    }
}
