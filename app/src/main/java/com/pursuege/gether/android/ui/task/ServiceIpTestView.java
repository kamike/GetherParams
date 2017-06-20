package com.pursuege.gether.android.ui.task;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.pursuege.gether.android.R;
import com.pursuege.gether.android.bean.ServiceIpBean;
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
 * Created by wangtao on 2017/6/20.
 */

public class ServiceIpTestView {
    public int urlIndex = 0;
    private ArrayList<ServiceIpBean> listIp;
    private CommonAdapter adapterServiceIp;

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


    private Animation animLoading;


    public ServiceIpTestView(Context context, View viewService) {
        EventBus.getDefault().register(this);
        animLoading = AnimationUtils.loadAnimation(context, R.anim.rotate_return);

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
            btn.setText(R.string.run_service_test);
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

    public void ondestoryView() {
        EventBus.getDefault().unregister(this);
    }
}
