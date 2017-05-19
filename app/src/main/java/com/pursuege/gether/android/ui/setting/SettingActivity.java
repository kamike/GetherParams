package com.pursuege.gether.android.ui.setting;


import android.widget.ListView;

import com.pursuege.gether.android.BaseActivity;
import com.pursuege.gether.android.R;
import com.pursuege.gether.android.view.NetworkProgress;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

public class SettingActivity extends BaseActivity {


    @Override
    public void initShowLayout() {
        setContentView(R.layout.activity_setting);
        dosSettitle(R.string.setting);



    }

    @Override
    public void setAllData() {
        final ArrayList<TestBean> list = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            TestBean test = new TestBean();
            test.name = "Name " + i;
            list.add(test);

        }

        ListView listView = (ListView) findViewById(R.id.setting_list_view);
        listView.setAdapter(new CommonAdapter<TestBean>(this, R.layout.view_item_adapter, list) {

            @Override
            protected void convert(ViewHolder viewHolder, TestBean item, int position) {
                viewHolder.setText(R.id.vite_item_context, list.get(position).name);
            }
        });

        new NetworkProgress(this);
    }

    private class TestBean {
        public String name;
    }
}
