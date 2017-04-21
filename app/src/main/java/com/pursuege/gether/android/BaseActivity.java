package com.pursuege.gether.android;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.pursuege.gether.android.bean.BaseServerObj;
import com.pursuege.gether.android.utils.StaticMethod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by BaseActivity on 2016/9/9.
 * QQ：751190264
 */
public abstract class BaseActivity extends Activity {
    public static final String NETWORK_EXCEPTION = "网络异常";
    /**
     * 导航栏高度
     */
    public static int TILE_HEIGHT = 200;
    /**
     * 像素密度
     */
    public static float screenDensity = 1;
    /**
     * 屏幕高度
     */
    public static int screenHeight = 1920;
    /**
     * 屏幕宽度
     */
    public static int screenWidth = 1080;
    protected Context mContext;
    /**
     * 和某个用户绑定的首选项设置，切换了用户，就是另外一个配置了
     */
    public SharedPreferences sharePreferenceUser, sharePreferenceAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        this.mContext = this;
        initCacheData();
        initShowLayout();
        EventBus.getDefault().register(this);
        setupScreen();
        setAllData();
    }

    private void initCacheData() {


        if (sharePreferenceUser == null) {
            String userId = "1";
            sharePreferenceUser = getSharedPreferences("user_setting" + userId, Context.MODE_PRIVATE);
        }
        if (sharePreferenceAll == null) {
            sharePreferenceAll = getSharedPreferences("all_setting", Context.MODE_PRIVATE);
        }
    }




    public abstract void initShowLayout();

    public abstract void setAllData();

    public void setupScreen() {
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        if (frame.top != 0) {
            this.TILE_HEIGHT = frame.top;// 获取导航栏的高度,这里必须在界面绘制出来才能正确获取
        } else {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                TILE_HEIGHT = mContext.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenDensity = metrics.density;
        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;

    }

    /**
     * 根据界面名称打印日志
     *
     * @param message
     */
    public void doLogMsg(String message) {
        Logger.t(getLocalClassName()).i(message);
    }

    public void doShowMesage(String msg, DialogInterface.OnClickListener listener) {
        if (isFinishing()) {
            return;
        }
        new AlertDialog.Builder(this).setTitle(null).setMessage(msg).setNegativeButton("确定", listener).show();
    }

    public void doShowMesage(int msg, DialogInterface.OnClickListener listener) {
        if (isFinishing()) {
            return;
        }
        new AlertDialog.Builder(this).setTitle(null).setMessage(msg).setNegativeButton("确定", listener).show();
    }


    public void doShowMesage(String msg) {
        if (isFinishing()) {
            return;
        }
        doShowMesage(msg, null);
    }

    public void doShowMesage(int msg) {
        if (isFinishing()) {
            return;
        }
        doShowMesage(msg, null);
    }

    /**
     * 短消息提示
     *
     * @param msg
     */
    public void doShowToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短消息提示
     *
     * @param msg
     */
    public void doShowToast(int msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长消息提示
     *
     * @param msg
     */
    public void doShowToastLong(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 长消息提示
     *
     * @param msg
     */
    public void doShowToastLong(int msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * 设置为竖屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

    }

    /**
     * 启动另外一个界面
     *
     * @param activity
     */
    public void doStartOter(Class activity) {
        Intent intentActive = new Intent(this, activity);
        startActivity(intentActive);
    }


    public void doSetStatusBars() {
        setStatusBarDarkMode(true);
        //沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            int color = Color.YELLOW;
            ViewGroup contentView = (ViewGroup) findViewById(android.R.id.content);
            View statusBarView = new View(this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(this));
            statusBarView.setBackgroundColor(color);
            contentView.addView(statusBarView, lp);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Logger.i("set bar--21");
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }


    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 设置状态栏图标和文本是否为深色
     * 注:仅支持小米和魅族
     */
    private void setStatusBarDarkMode(boolean darkMode) {
        setStatusBarDarkModeForMUI(darkMode);
        setStatusBarDarkModeForMZ(darkMode);
    }

    private void setStatusBarDarkModeForMUI(boolean darkmode) {
        Class<? extends Window> clazz = this.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(this.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setStatusBarDarkModeForMZ(boolean darkmode) {
        Window window = this.getWindow();
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (darkmode) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
            } catch (NoSuchFieldException e) {
//                LogUtils.e("MeiZu", "setStatusBarDarkIcon: failed");
            } catch (IllegalAccessException e) {
//                LogUtils.e("MeiZu", "setStatusBarDarkIcon: failed");
            }
        }
    }

    /**
     * 使状态栏透明 * <p> * 适用于图片作为背景的界面,此时需要图片填充到状态栏 * * @param activity 需要设置的activity
     */
    public static void setTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        doDissmissProgress();
    }

    public void doshowNetworkException() {
        doShowMesage(R.string.network_exception);
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onServerCodeException(BaseServerObj serverObj) {
        doDissmissProgress();
        if (serverObj == null) {
            doshowNetworkException();
            return;
        }
        doLogMsg(serverObj.Data + "");
        doShowMesage(StaticMethod.getMessForServerCode(serverObj.ReasonCode));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkException(String message) {
        Logger.i(message);
        doDissmissProgress();
        doshowNetworkException();
    }

    private ProgressDialog networkProgress;

    protected void doShowProgress() {
        networkProgress = ProgressDialog.show(this, null, null, true, true);
    }

    protected void doDissmissProgress() {
        if (networkProgress != null) {
            if (networkProgress.isShowing()) {
                networkProgress.dismiss();
            }
            networkProgress = null;
        }
    }




}
