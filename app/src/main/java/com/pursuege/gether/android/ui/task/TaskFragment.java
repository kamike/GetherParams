package com.pursuege.gether.android.ui.task;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.pursuege.gether.android.R;
import com.pursuege.gether.android.ui.view.AdapterViewpager;

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

            initView();
            initAllData();
        }
        return rootView;
    }


    private void initView() {
        tableLayout = (TabLayout) rootView.findViewById(R.id.task_topbar_tablayout);
        viewPager = (ViewPager) rootView.findViewById(R.id.task_viewpager);
        tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        String[] titleArray = context.getResources().getStringArray(R.array.task_title_array);

        arrayViews = new ArrayList<>();
        //服务测试
        View viewService = LayoutInflater.from(context).inflate(R.layout.view_item_task_service, null);
        serviceIpTestView = new ServiceIpTestView(context, viewService);
        //延迟测试
        View viewDelayTest = LayoutInflater.from(context).inflate(R.layout.view_item_task_delay_test, null);
        delayTestView = new DelayTestView(viewDelayTest);
        //语音测试
        View viewVoiceTest = LayoutInflater.from(context).inflate(R.layout.view_item_task_voice_test, null);
        voiceTestView = new VoiceTestView(viewVoiceTest);

        //短信测试
        View viewSms = LayoutInflater.from(context).inflate(R.layout.view_item_task_sms_test, null);
        new SmsTestView(viewSms);

        //流媒体
        View viewStream = LayoutInflater.from(context).inflate(R.layout.view_item_task_stream_test, null);
        new StreamTestView(viewStream);

        //音频测试
        View viewAutio = LayoutInflater.from(context).inflate(R.layout.view_item_task_audio_test, null);
        new StreamTestView(viewAutio);

        arrayViews.add(viewService);
        arrayViews.add(viewDelayTest);
        arrayViews.add(viewVoiceTest);
        arrayViews.add(viewSms);
        arrayViews.add(viewStream);
        arrayViews.add(viewAutio);
        //title不显示的bug
        for (int i = 0; i < arrayViews.size(); i++) {
            arrayViews.get(i).setTag(titleArray[i]);
        }

        viewPager.setAdapter(new AdapterViewpager(arrayViews));
        for (int i = 0; i < titleArray.length; i++) {
            tableLayout.addTab(tableLayout.newTab().setText(titleArray[i]));
        }
        tableLayout.setupWithViewPager(viewPager);
    }

    private ServiceIpTestView serviceIpTestView;
    private DelayTestView delayTestView;
    private VoiceTestView voiceTestView;

    private void initAllData() {


    }


    private ArrayList<View> arrayViews;


    @Override
    public void onDetach() {
        super.onDetach();
        Logger.i("onDetach===");
        serviceIpTestView.ondestoryView();
    }
}
