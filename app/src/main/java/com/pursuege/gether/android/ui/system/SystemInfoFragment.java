package com.pursuege.gether.android.ui.system;

import android.app.Fragment;
import android.content.Context;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pursuege.gether.android.BaseActivity;
import com.pursuege.gether.android.R;
import com.pursuege.gether.android.utils.PhoneInfoUtils;
import com.pursuege.gether.android.utils.TimeFormatUtils;


public class SystemInfoFragment extends Fragment {
    private TextView tvBrandModle;
    private TextView tvMainNumber;
    private TextView tvImsiMain;
    private TextView tvImsiAncillary;
    private TextView tvImeiMain;
    private TextView tvImeiAncillary;
    private TextView tvAndroidVersion;
    private TextView tvIsRoot;
    private TextView tvScreenSize;
    private TextView tvScreenDensity;
    private TextView tvRunTime;
    private TextView tvGsmUpDown;
    private TextView tvWifiUpDown;
    private TextView tvCupModle;
    private TextView tvCpuNumber;
    private TextView tvMainFrequency;
    private TextView tvFabricationProcess;
    private TextView tvBaseBand;
    private TextView tvCpuSupport;
    private TextView tvRunRamVolume;
    private TextView tvCurrentRamOverplus;
    private TextView tvPhoneRom;
    private TextView tvSdcard;
    private TextView tvBehindCameraPix;
    private TextView tvBehindCameraRatio;
    private TextView tvForwardCameraPix;
    private TextView tvForwordCameraRatio;
    private TextView tvGpu;
    private TextView tvWifiConnect;
    private TextView tvMacAddress;
    private TextView tvWifiConnectSpeed;
    private TextView tvInsideIp;
    private TextView tvExteriorIp;
    private TextView tvBatteryVolume;
    private TextView tvBatteryVoltage;
    private TextView tvCurrentElectricity;
    private TextView tvCurrentTemperature;
    private TextView tvRechangeStatus;

    private void assignViews(View v) {
        tvBrandModle = (TextView) v.findViewById(R.id.system_info_brand_modle_tv);
        tvMainNumber = (TextView) v.findViewById(R.id.system_info_main_number_tv);
        tvImsiMain = (TextView) v.findViewById(R.id.system_info_imsi_main_tv);
        tvImsiAncillary = (TextView) v.findViewById(R.id.system_info_imsi_ancillary_tv);
        tvImeiMain = (TextView) v.findViewById(R.id.system_info_imei_main_tv);
        tvImeiAncillary = (TextView) v.findViewById(R.id.system_info_imei_ancillary_tv);
        tvAndroidVersion = (TextView) v.findViewById(R.id.system_info_android_version_tv);
        tvIsRoot = (TextView) v.findViewById(R.id.system_info_is_root_tv);
        tvScreenSize = (TextView) v.findViewById(R.id.system_info_screen_size_tv);
        tvScreenDensity = (TextView) v.findViewById(R.id.system_info_screen_density_tv);
        tvRunTime = (TextView) v.findViewById(R.id.system_info_run_time_tv);
        tvGsmUpDown = (TextView) v.findViewById(R.id.system_info_gsm_up_down_tv);
        tvWifiUpDown = (TextView) v.findViewById(R.id.system_info_wifi_up_down_tv);
        tvCupModle = (TextView) v.findViewById(R.id.system_info_cup_modle_tv);
        tvCpuNumber = (TextView) v.findViewById(R.id.system_info_cpu_number_tv);
        tvMainFrequency = (TextView) v.findViewById(R.id.system_info_main_frequency_tv);
        tvFabricationProcess = (TextView) v.findViewById(R.id.system_info_fabrication_process_tv);
        tvBaseBand = (TextView) v.findViewById(R.id.system_info_base_band_tv);
        tvCpuSupport = (TextView) v.findViewById(R.id.system_info_cpu_support_tv);
        tvRunRamVolume = (TextView) v.findViewById(R.id.system_info_run_ram_volume_tv);
        tvCurrentRamOverplus = (TextView) v.findViewById(R.id.system_info_current_ram_overplus_tv);
        tvPhoneRom = (TextView) v.findViewById(R.id.system_info_phone_rom_tv);
        tvSdcard = (TextView) v.findViewById(R.id.system_info_sdcard_tv);
        tvBehindCameraPix = (TextView) v.findViewById(R.id.system_info_behind_camera_pix_tv);
        tvBehindCameraRatio = (TextView) v.findViewById(R.id.system_info_behind_camera_ratio_tv);
        tvForwardCameraPix = (TextView) v.findViewById(R.id.system_info_forward_camera_pix_tv);
        tvForwordCameraRatio = (TextView) v.findViewById(R.id.system_info_forword_camera_ratio_tv);
        tvGpu = (TextView) v.findViewById(R.id.system_info_gpu_tv);
        tvWifiConnect = (TextView) v.findViewById(R.id.system_info_wifi_connect_tv);
        tvMacAddress = (TextView) v.findViewById(R.id.system_info_mac_address_tv);
        tvWifiConnectSpeed = (TextView) v.findViewById(R.id.system_info_wifi_connect_speed_tv);
        tvInsideIp = (TextView) v.findViewById(R.id.system_info_inside_ip_tv);
        tvExteriorIp = (TextView) v.findViewById(R.id.system_info_exterior_ip_tv);
        tvBatteryVolume = (TextView) v.findViewById(R.id.system_info_battery_volume_tv);
        tvBatteryVoltage = (TextView) v.findViewById(R.id.system_info_battery_voltage_tv);
        tvCurrentElectricity = (TextView) v.findViewById(R.id.system_info_current_electricity_tv);
        tvCurrentTemperature = (TextView) v.findViewById(R.id.system_info_current_temperature_tv);
        tvRechangeStatus = (TextView) v.findViewById(R.id.system_info_rechange_status_tv);
    }

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        View v = inflater.inflate(R.layout.fragment_system_info, container, false);
        assignViews(v);
        initAllData();
        return v;
    }

    private void initAllData() {
        getFixParams();

        handlerDelete.sendEmptyMessage(0);
    }

    private void getFixParams() {
        PhoneInfoUtils phoneInfo = new PhoneInfoUtils(getActivity().getApplication());

        tvBrandModle.setText(android.os.Build.MODEL);
        tvMainNumber.setText(phoneInfo.getMainNumber());
        tvImsiMain.setText(phoneInfo.getMainIMSI());
        tvImeiMain.setText(phoneInfo.getMainIMEI());

        tvAndroidVersion.setText(Build.VERSION.RELEASE);

        tvIsRoot.setText(PhoneInfoUtils.isRoot() ? "是" : "否");

        tvScreenSize.setText(BaseActivity.screenWidth + "x" + BaseActivity.screenHeight);
        tvScreenDensity.setText(getResources().getDisplayMetrics().densityDpi + "Dpi");
        tvMainFrequency.setText(TimeFormatUtils.getGMKHZ(PhoneInfoUtils.getMaxCpuFreq()));
        tvCupModle.setText(PhoneInfoUtils.getCpuName());

        PhoneInfoUtils.getCupInfo();


    }

    private Handler handlerDelete = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (isDetached()) {
                return;
            }
            getDynamicParams();
            sendEmptyMessageDelayed(0, 1000);
        }
    };

    private void getDynamicParams() {
        tvRunTime.setText(TimeFormatUtils.getTimeString(SystemClock.elapsedRealtime()));
        tvGsmUpDown.setText(TimeFormatUtils.getGMKB(TrafficStats.getMobileTxBytes()) + "/" +
                TimeFormatUtils.getGMKB(TrafficStats.getMobileRxBytes()));
        tvWifiUpDown.setText(TimeFormatUtils.getGMKB(TrafficStats.getTotalTxBytes()-TrafficStats.getMobileTxBytes()) + "/" +
                TimeFormatUtils.getGMKB(TrafficStats.getTotalRxBytes()-TrafficStats.getMobileRxBytes()));
    }


}
