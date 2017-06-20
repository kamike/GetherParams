package com.pursuege.gether.android.ui.setting;


import android.content.Intent;
import android.view.View;

import com.pursuege.gether.android.BaseActivity;
import com.pursuege.gether.android.R;

public class SettingActivity extends BaseActivity {


    @Override
    public void initShowLayout() {
        setContentView(R.layout.activity_setting);
        dosSettitle(R.string.setting);
    }

    @Override
    public void setAllData() {

    }


    public void onclickSettingHelp(View view) {
    }

    public void onclickSettingIconSetting(View view) {
    }

    public void onclickSettingInit(View view) {
    }

    public void onclickSettingFeedback(View view) {
    }

    public void onclickSettingUpdateOnline(View view) {
    }

    public void onclickSettingAboutMe(View view) {
    }

    public void onclickSettingprivate(View view) {
    }

    public void onclickSettingShareFriend(View view) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "分享的文字......");
        shareIntent.setType("text/plain");

        //设置分享列表的标题，并且每次都显示分享列表
        startActivity(Intent.createChooser(shareIntent, "分享到"));
    }
}
