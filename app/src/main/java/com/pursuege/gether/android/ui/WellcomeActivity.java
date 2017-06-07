package com.pursuege.gether.android.ui;

import com.pursuege.gether.android.BaseActivity;
import com.pursuege.gether.android.R;

public class WellcomeActivity extends BaseActivity {


    @Override
    public void initShowLayout() {
        setContentView(R.layout.activity_wellcome);
        doStartOter(MainActivity.class);
        finish();
    }

    @Override
    public void setAllData() {

    }

}
