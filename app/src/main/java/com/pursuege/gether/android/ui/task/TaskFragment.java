package com.pursuege.gether.android.ui.task;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.pursuege.gether.android.R;
import com.pursuege.gether.android.bean.ServiceIpBean;
import com.pursuege.gether.android.ui.view.AdapterViewpager;
import com.pursuege.gether.android.ui.view.BlueProgressView;
import com.pursuege.gether.android.utils.AssetsIOperate;
import com.pursuege.gether.android.utils.NetworkUtils;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment {
    private Context context;
    private View rootView;
    private android.support.design.widget.TabLayout tableLayout;
    private ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(R.layout.fragment_task, container, false);

            initView();
            initAllData();
        }
        return rootView;
    }

    private final int[] titleArray = {R.string.service_test, R.string.delay_test, R.string.vioce_test, R.string.sms_test, R.string.stream_test};

    private void initView() {
        tableLayout = (TabLayout) rootView.findViewById(R.id.task_topbar_tablayout);
        viewPager = (ViewPager) rootView.findViewById(R.id.task_viewpager);

        for (int i = 0; i < titleArray.length; i++) {
            tableLayout.addTab(tableLayout.newTab().setText(titleArray[i]));
        }

        arrayViews = new ArrayList<>();
        View viewService = LayoutInflater.from(context).inflate(R.layout.view_item_task_service, null);
        addServiceView(viewService);
        arrayViews.add(viewService);
        viewPager.setAdapter(new AdapterViewpager(arrayViews));
    }

    private void addServiceView(View viewService) {
        TextView tvLastTime = (TextView) viewService.findViewById(R.id.task_item_last_time_tv);
        tvLastTime.setText(context.getText(R.string.last_test_time) + "");
        ListView listViewContent = (ListView) viewService.findViewById(R.id.task_item_content_listview);
        Button btn = (Button) viewService.findViewById(R.id.task_item_start_btn);


        ArrayList<ServiceIpBean> listIp = AssetsIOperate.getAssetsData(context, "ServiceIpList.json", ServiceIpBean.class);
        final CommonAdapter adapterServiceIp = new CommonAdapter<ServiceIpBean>(context, R.layout.view_item_txt_progress_loading, listIp) {
            @Override
            protected void convert(ViewHolder viewHolder, ServiceIpBean item, int position) {
                viewHolder.setText(R.id.test_progress_load_tv, item.ipName);
                BlueProgressView blueProgressView = viewHolder.getView(R.id.test_progress_load_bp);
                ImageView ivLoading = viewHolder.getView(R.id.test_progress_load_iv);
            }
        };
        listViewContent.setAdapter(adapterServiceIp);
        if (NetworkUtils.isNetworkAvailable(context)) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapterServiceIp.notifyDataSetChanged();
                }
            });
        } else {
            btn.setText(R.string.run_this_test_allow_wifi_gprs);
        }


    }

    private ArrayList<View> arrayViews;

    private void initAllData() {


    }


}
