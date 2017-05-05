package com.pursuege.gether.android.ui;

import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pursuege.gether.android.BaseActivity;
import com.pursuege.gether.android.R;

public class MainActivity extends BaseActivity {

    private LinearLayout linearParams;

    @Override
    public void initShowLayout() {
        setContentView(R.layout.activity_main);
        doSetStatusBars();
        setTranslucent(this);
        linearParams = (LinearLayout) findViewById(R.id.main_params_linear);

    }

    @Override
    public void setAllData() {
        addBaseParams();
        addProgressLocaltion();
    }

    private void addBaseParams() {
        for (int index = 0; index < 7; index++) {
            LinearLayout paramsItem = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.linear_item_params_net, null);
            TextView tvParamsName = (TextView) paramsItem.findViewById(R.id.params_item_name_tv);
            TextView tvParamsMainValue = (TextView) paramsItem.findViewById(R.id.params_item_main_value_tv);

            TextView tvParamsAssistant = (TextView) paramsItem.findViewById(R.id.params_item_assistant_value_tv);
            tvParamsMainValue.setText("" + index);
            tvParamsAssistant.setText("" + index);
            switch (index) {
                case 0:
                    tvParamsName.setText(getString(R.string.carrieroperator));

                    break;
                case 1:
                    tvParamsName.setText(getString(R.string.mccmnc));
                    break;
                case 2:
                    tvParamsName.setText(getString(R.string.local_lac));
                    break;
                case 3:
                    tvParamsName.setText(getString(R.string.enb_rnc));
                    break;
                case 4:
                    tvParamsName.setText(getString(R.string.cid_cid_bid));
                    break;
                case 5:
                    tvParamsName.setText(getString(R.string.arfcn));
                    break;
                case 6:
                    tvParamsName.setText(getString(R.string.pci_psc));
                    break;

            }
            linearParams.addView(paramsItem);
        }
    }

    private void addProgressLocaltion() {

    }
}
