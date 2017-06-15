package com.pursuege.gether.android.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by wangtao on 2017/6/15.
 */

public class GLSurfaceViewInfo extends android.opengl.GLSurfaceView {


    public GLSurfaceViewInfo(Context context, AttributeSet attrs) {
        super(context, attrs);
        setRenderer(new GlSurface());
        setBackgroundColor(Color.TRANSPARENT);
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
    }

    private class GlSurface implements GLSurfaceView.Renderer {

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            EventBus.getDefault().post(gl);
            Logger.i("发送gl--");
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {

        }

        @Override
        public void onDrawFrame(GL10 gl) {

        }

    }


}
