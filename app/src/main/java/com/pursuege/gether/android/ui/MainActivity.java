package com.pursuege.gether.android.ui;

import android.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pursuege.gether.android.BaseActivity;
import com.pursuege.gether.android.R;
import com.pursuege.gether.android.ui.log.LogFragment;
import com.pursuege.gether.android.ui.mobile.MobileFragment;
import com.pursuege.gether.android.ui.setting.SettingActivity;
import com.pursuege.gether.android.ui.system.SystemInfoFragment;
import com.pursuege.gether.android.ui.task.TaskFragment;
import com.pursuege.gether.android.ui.test.TestRoadFragment;

public class MainActivity extends BaseActivity {

    private LinearLayout mMainParamsLinear;
    private ImageView mHomeMobileIv;
    private TextView mHomeMobileTv;
    private ImageView mHomeTaskIv;
    private TextView mHomeTaskTv;
    private ImageView mHomeTestIv;
    private TextView mHomeTestTv;
    private ImageView mHomeSystemInfoIv;
    private TextView mHomeSystemInfoTv;
    private ImageView mHomeLogIv;
    private TextView mHomeLogTv;



    private MobileFragment fragmentMobile;
    private TaskFragment fragmentTask;
    private TestRoadFragment fragmentTestRoad;
    private SystemInfoFragment fragmentSystemInfo;
    private LogFragment fragmentLog;

    @Override
    public void initShowLayout() {
        setContentView(R.layout.activity_main);
        doSetStatusBars();
        setTranslucent(this);

        assignViews();

    }


    private void assignViews() {
        mMainParamsLinear = (LinearLayout) findViewById(R.id.main_params_linear);
        mHomeMobileIv = (ImageView) findViewById(R.id.home_mobile_iv);
        mHomeMobileTv = (TextView) findViewById(R.id.home_mobile_tv);
        mHomeTaskIv = (ImageView) findViewById(R.id.home_task_iv);
        mHomeTaskTv = (TextView) findViewById(R.id.home_task_tv);
        mHomeTestIv = (ImageView) findViewById(R.id.home_test_iv);
        mHomeTestTv = (TextView) findViewById(R.id.home_test_tv);
        mHomeSystemInfoIv = (ImageView) findViewById(R.id.home_system_info_iv);
        mHomeSystemInfoTv = (TextView) findViewById(R.id.home_system_info_tv);
        mHomeLogIv = (ImageView) findViewById(R.id.home_log_iv);
        mHomeLogTv = (TextView) findViewById(R.id.home_log_tv);
    }


    @Override
    public void setAllData() {
        colorBule = getResources().getColor(R.color.color_blue_them);
        colorHint = getResources().getColor(R.color.color_text_hint);
        addProgressLocaltion();
        fragmentLog = new LogFragment();
        fragmentSystemInfo = new SystemInfoFragment();
        fragmentTask = new TaskFragment();
        fragmentTestRoad = new TestRoadFragment();
        fragmentMobile = new MobileFragment();
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.replace(R.id.main_fragment, fragmentMobile);
        trans.commit();
    }



    private void addProgressLocaltion() {

    }

    public void onclickHomeLog(View view) {
        setHomeIndex(5);
    }

    public void onclickHomeSystemInfo(View view) {
        setHomeIndex(4);
    }

    public void onclickHomeTest(View view) {
        setHomeIndex(3);
    }

    public void onclickHomeTask(View view) {
        setHomeIndex(2);
    }

    public void onclickHomeMobile(View view) {
        setHomeIndex(1);
    }

    private int colorBule, colorHint;

    private void setHomeIndex(int index) {
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        switch (index) {
            case 1:
                mHomeMobileTv.setTextColor(colorBule);
                mHomeTaskTv.setTextColor(colorHint);
                mHomeTestTv.setTextColor(colorHint);
                mHomeSystemInfoTv.setTextColor(colorHint);
                mHomeLogTv.setTextColor(colorHint);

                mHomeMobileIv.setImageResource(R.drawable.icon_home_mobile_press);
                mHomeTaskIv.setImageResource(R.drawable.icon_home_task);
                mHomeTestIv.setImageResource(R.drawable.icon_home_test);
                mHomeSystemInfoIv.setImageResource(R.drawable.icon_home_system_info);
                mHomeLogIv.setImageResource(R.drawable.icon_home_log);

                trans.replace(R.id.main_fragment, fragmentMobile);
                break;
            case 2:
                mHomeMobileTv.setTextColor(colorHint);
                mHomeTaskTv.setTextColor(colorBule);
                mHomeTestTv.setTextColor(colorHint);
                mHomeSystemInfoTv.setTextColor(colorHint);
                mHomeLogTv.setTextColor(colorHint);

                mHomeMobileIv.setImageResource(R.drawable.icon_home_mobile);
                mHomeTaskIv.setImageResource(R.drawable.icon_home_task_press);
                mHomeTestIv.setImageResource(R.drawable.icon_home_test);
                mHomeSystemInfoIv.setImageResource(R.drawable.icon_home_system_info);
                mHomeLogIv.setImageResource(R.drawable.icon_home_log);

                trans.replace(R.id.main_fragment, fragmentTask);
                break;
            case 3:
                mHomeMobileTv.setTextColor(colorHint);
                mHomeTaskTv.setTextColor(colorHint);
                mHomeTestTv.setTextColor(colorBule);
                mHomeSystemInfoTv.setTextColor(colorHint);
                mHomeLogTv.setTextColor(colorHint);

                mHomeMobileIv.setImageResource(R.drawable.icon_home_mobile);
                mHomeTaskIv.setImageResource(R.drawable.icon_home_task);
                mHomeTestIv.setImageResource(R.drawable.icon_home_test_press);
                mHomeSystemInfoIv.setImageResource(R.drawable.icon_home_system_info);
                mHomeLogIv.setImageResource(R.drawable.icon_home_log);

                trans.replace(R.id.main_fragment, fragmentTestRoad);
                break;
            case 4:
                mHomeMobileTv.setTextColor(colorHint);
                mHomeTaskTv.setTextColor(colorHint);
                mHomeTestTv.setTextColor(colorHint);
                mHomeSystemInfoTv.setTextColor(colorBule);
                mHomeLogTv.setTextColor(colorHint);

                mHomeMobileIv.setImageResource(R.drawable.icon_home_mobile);
                mHomeTaskIv.setImageResource(R.drawable.icon_home_task);
                mHomeTestIv.setImageResource(R.drawable.icon_home_test);
                mHomeSystemInfoIv.setImageResource(R.drawable.icon_home_system_info_press);
                mHomeLogIv.setImageResource(R.drawable.icon_home_log);

                trans.replace(R.id.main_fragment, fragmentSystemInfo);
                break;
            case 5:
                mHomeMobileTv.setTextColor(colorHint);
                mHomeTaskTv.setTextColor(colorHint);
                mHomeTestTv.setTextColor(colorHint);
                mHomeSystemInfoTv.setTextColor(colorHint);
                mHomeLogTv.setTextColor(colorBule);

                mHomeMobileIv.setImageResource(R.drawable.icon_home_mobile);
                mHomeTaskIv.setImageResource(R.drawable.icon_home_task);
                mHomeTestIv.setImageResource(R.drawable.icon_home_test);
                mHomeSystemInfoIv.setImageResource(R.drawable.icon_home_system_info);
                mHomeLogIv.setImageResource(R.drawable.icon_home_log_press);

                trans.replace(R.id.main_fragment, fragmentLog);
                break;

        }
        trans.commit();
    }

    public void onclickHomeSetting(View view) {
        doStartOter(SettingActivity.class);
    }
}
