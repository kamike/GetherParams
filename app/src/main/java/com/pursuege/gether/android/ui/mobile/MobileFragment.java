package com.pursuege.gether.android.ui.mobile;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pursuege.gether.android.R;


public class MobileFragment extends Fragment {
    private LinearLayout linearParams;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        View v=inflater.inflate(R.layout.fragment_mobile, container, false);
        initView(v);
        return v;
    }
    private void initView(View v){
        linearParams = (LinearLayout) v.findViewById(R.id.main_params_linear);
        addBaseParams();
    }
    private void addBaseParams() {
        for (int index = 0; index < 7; index++) {
            LinearLayout paramsItem = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.linear_item_params_net, null);
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

}
