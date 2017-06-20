package com.pursuege.gether.android.ui.task;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.pursuege.gether.android.R;
import com.pursuege.gether.android.bean.ServiceIpBean;
import com.pursuege.gether.android.ui.view.AdapterViewpager;
import com.pursuege.gether.android.ui.view.BlueProgressView;
import com.pursuege.gether.android.utils.AssetsIOperate;
import com.pursuege.gether.android.utils.NetworkUtils;
import com.pursuege.gether.android.utils.TimeFormatUtils;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
            Logger.i("onCreateView===");
            rootView = inflater.inflate(R.layout.fragment_task, container, false);
            EventBus.getDefault().register(this);
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
        //服务测试
        View viewService = LayoutInflater.from(context).inflate(R.layout.view_item_task_service, null);
        addServiceView(viewService);
        //短信测试
        View viewSms = LayoutInflater.from(context).inflate(R.layout.view_item_task_sms_test, null);

        addSmsTestView(viewSms);

        arrayViews.add(viewService);
        arrayViews.add(viewSms);
        viewPager.setAdapter(new AdapterViewpager(arrayViews));

        tableLayout.setupWithViewPager(viewPager);
    }
    private void initAllData() {
        animLoading = AnimationUtils.loadAnimation(context, R.anim.rotate_return);

    }


    private ArrayList<ServiceIpBean> listIp;
    private CommonAdapter adapterServiceIp;

    /**
     * 服务测试
     *
     * @param viewService
     */
    private void addServiceView(View viewService) {
        TextView tvLastTime = (TextView) viewService.findViewById(R.id.task_item_last_time_tv);
        tvLastTime.setText(context.getText(R.string.last_test_time) + "");
        ListView listViewContent = (ListView) viewService.findViewById(R.id.task_item_content_listview);
        Button btn = (Button) viewService.findViewById(R.id.task_item_start_btn);


        listIp = AssetsIOperate.getAssetsData(context, "ServiceIpList.json", ServiceIpBean.class);
        adapterServiceIp =
                new CommonAdapter<ServiceIpBean>(context, R.layout.view_item_txt_progress_loading, listIp) {
                    @Override
                    protected void convert(ViewHolder viewHolder, ServiceIpBean item, int position) {

                        viewHolder.setText(R.id.test_progress_load_tv, item.ipName);
                        BlueProgressView blueProgressView = viewHolder.getView(R.id.test_progress_load_bp);
                        ImageView ivLoading = viewHolder.getView(R.id.test_progress_load_iv);

                        blueProgressView.setCurrentProgress(TimeFormatUtils.getRadio(item.delayTime / item.maxDelay));
                        if (item.isLoading) {
                            ivLoading.startAnimation(animLoading);
                        } else {
                            ivLoading.clearAnimation();
                        }

                    }
                };
        listViewContent.setAdapter(adapterServiceIp);
        if (NetworkUtils.isNetworkAvailable(context)) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //开始测试
                    if (listIp == null || listIp.isEmpty()) {
                        return;
                    }
                    urlIndex = 0;
                    listIp.get(urlIndex).isLoading = true;

                    NetworkUtils.doGetForUrlTime(listIp.get(urlIndex));

                    adapterServiceIp.notifyDataSetChanged();
                }
            });
        } else {
            btn.setText(R.string.run_this_test_allow_wifi_gprs);
        }


    }

    public int urlIndex = 0;


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getUrlTime(ServiceIpBean time) {
        listIp.get(urlIndex).isLoading = false;
        if (time.delayTime != -1) {
            listIp.get(urlIndex).delayTime = time.delayTime;
        } else {
            listIp.get(urlIndex).delayTime = listIp.get(urlIndex).maxDelay;
        }
        adapterServiceIp.notifyDataSetChanged();

        urlIndex++;
        if (urlIndex >= listIp.size()) {
            return;
        }
        listIp.get(urlIndex).isLoading = true;
        NetworkUtils.doGetForUrlTime(listIp.get(urlIndex));
    }


    private ArrayList<View> arrayViews;
    private Animation animLoading;




    /**
     * 短信测试
     *
     * @param viewSms
     */
    private void addSmsTestView(View viewSms) {
        TextView tvLastTime = (TextView) viewSms.findViewById(R.id.task_sms_item_last_time_tv);
        EditText etOtherNumber = (EditText) viewSms.findViewById(R.id.task_sms_item_other_phone_et);
        CheckBox checkTimePrint = (CheckBox) viewSms.findViewById(R.id.task_sms_item_time_check);


    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.i("onDetach===");
        EventBus.getDefault().unregister(this);
    }
}
