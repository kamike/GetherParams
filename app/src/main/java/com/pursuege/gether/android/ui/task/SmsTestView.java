package com.pursuege.gether.android.ui.task;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.pursuege.gether.android.R;

/**
 * Created by wangtao on 2017/6/20.
 */

class SmsTestView {
    public SmsTestView(View viewSms) {
        TextView tvLastTime = (TextView) viewSms.findViewById(R.id.task_sms_item_last_time_tv);
        EditText etOtherNumber = (EditText) viewSms.findViewById(R.id.task_sms_item_other_phone_et);
        CheckBox checkTimePrint = (CheckBox) viewSms.findViewById(R.id.task_sms_item_time_check);
    }
}
