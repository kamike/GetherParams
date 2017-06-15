package com.pursuege.gether.android.ui.system;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.TrafficStats;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pursuege.gether.android.BaseActivity;
import com.pursuege.gether.android.R;
import com.pursuege.gether.android.ui.view.BlueProgressView;
import com.pursuege.gether.android.utils.CameraUtils;
import com.pursuege.gether.android.utils.PhoneInfoUtils;
import com.pursuege.gether.android.utils.TimeFormatUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Comparator;
import java.util.HashMap;

import javax.microedition.khronos.opengles.GL10;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static com.pursuege.gether.android.utils.PhoneInfoUtils.getMemInfo;

@RuntimePermissions
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
    private BlueProgressView tvPhoneRom;
    private BlueProgressView tvSdcard;
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
        tvPhoneRom = (BlueProgressView) v.findViewById(R.id.system_info_phone_rom_view);
        tvSdcard = (BlueProgressView) v.findViewById(R.id.system_info_sdcard_view);
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
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tvPhoneRom.getLayoutParams();
        params.width = (int) (BaseActivity.screenWidth * 0.65f);
        tvPhoneRom.setLayoutParams(params);
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) tvSdcard.getLayoutParams();
        params2.width = (int) (BaseActivity.screenWidth * 0.65f);
        tvSdcard.setLayoutParams(params2);
    }

    private Context context;
    private View rootView;


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
            rootView = inflater.inflate(R.layout.fragment_system_info, container, false);
            assignViews(rootView);
            initAllData();
        }
        return rootView;
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    private void initAllData() {
        EventBus.getDefault().register(this);  //注册

        batteryReceiver=new BatteryReceiver();
        IntentFilter batteryFilter = new IntentFilter();
        batteryFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        context.registerReceiver(batteryReceiver,batteryFilter);

        phoneInfo = new PhoneInfoUtils(getActivity().getApplication());
        getFixParams();
        handlerDelete.sendEmptyMessage(0);

    }

    private PhoneInfoUtils phoneInfo;

    private void getFixParams() {


        tvBrandModle.setText(android.os.Build.MODEL);
        tvMainNumber.setText(phoneInfo.getMainNumber());
        tvImsiMain.setText(phoneInfo.getMainIMSI());
        tvImeiMain.setText(phoneInfo.getMainIMEI());

        tvAndroidVersion.setText(Build.VERSION.RELEASE);

        tvIsRoot.setText(PhoneInfoUtils.isRoot() ? "是" : "否");

        tvScreenSize.setText(BaseActivity.screenWidth + "x" + BaseActivity.screenHeight);
        tvScreenDensity.setText(getResources().getDisplayMetrics().densityDpi + "dpi");
        tvMainFrequency.setText(TimeFormatUtils.getGMKHZ(PhoneInfoUtils.getMaxCpuFreq()));

        HashMap<String, String> cupInfo = getMemInfo();
        tvRunRamVolume.setText(TimeFormatUtils.getGMKBForKb(cupInfo.get("MemTotal")));

        tvPhoneRom.setCurrentProgress(0.1f);
        tvSdcard.setCurrentProgress(1 - PhoneInfoUtils.getSDAvailableSize() * 1.0f / PhoneInfoUtils.getSDTotalSize());

        HashMap<String, String> hashCpu = PhoneInfoUtils.getCpuInfo();
        System.out.println(hashCpu);
        tvCupModle.setText(hashCpu.get("Hardware"));
        tvBaseBand.setText(PhoneInfoUtils.getBaseband_Ver());
        tvCpuNumber.setText(PhoneInfoUtils.getCpuNumCores() + "核心");
        tvCpuSupport.setText(hashCpu.get("Processor"));
        int[] back = CameraUtils.getCameraPixels(CameraUtils.HasBackCamera());

        tvBehindCameraPix.setText(TimeFormatUtils.getPixString(back));
        tvBehindCameraRatio.setText(back[0] + "x" + back[1]);

        int[] front = CameraUtils.getCameraPixels(CameraUtils.HasFrontCamera());
        tvForwardCameraPix.setText(TimeFormatUtils.getPixString(front));
        tvForwordCameraRatio.setText(front[0] + "x" + front[1]);


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
        tvWifiUpDown.setText(TimeFormatUtils.getGMKB(TrafficStats.getTotalTxBytes() - TrafficStats.getMobileTxBytes()) + "/" +
                TimeFormatUtils.getGMKB(TrafficStats.getTotalRxBytes() - TrafficStats.getMobileRxBytes()));

//        HashMap<String, String> cupInfo= PhoneInfoUtils.getCupInfo();

        tvCurrentRamOverplus.setText(TimeFormatUtils.getGMKB(phoneInfo.getAvailMemory()));
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        if (info != null) {
            tvWifiConnect.setText(info.getSSID());
            tvMacAddress.setText(info.getMacAddress());
            tvWifiConnectSpeed.setText(info.getLinkSpeed() + "Mbps");
            tvInsideIp.setText(TimeFormatUtils.intToIp(info.getIpAddress()));
        }

    }

    // 为Size定义一个比较器Comparator
    static class CompareSizesByArea implements Comparator<Size> {
        @Override
        public int compare(Size lhs, Size rhs) {
            // 强转为long保证不会发生溢出
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                return Long.signum((long) lhs.getWidth() * lhs.getHeight() -
                        (long) rhs.getWidth() * rhs.getHeight());
            }
            return 0;
        }
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void showRationaleForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(context)
                .setMessage(R.string.permission_camera_rationale)
                .setPositiveButton(R.string.button_allow, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton(R.string.button_deny, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .show();
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void showDeniedForCamera() {
        Toast.makeText(context, R.string.permission_camera_denied, Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void showNeverAskForCamera() {
        Toast.makeText(context, R.string.permission_camera_neverask, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGlInfo(GL10 gl) {
        if (gl == null) {
            return;
        }
        tvGpu.setText(gl.glGetString(GL10.GL_RENDERER));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);  //注册
        context.unregisterReceiver(batteryReceiver);

    }

    private BatteryReceiver batteryReceiver;
    public class BatteryReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            tvCurrentElectricity.setText( arg1.getIntExtra("level", 0) + "%");
            tvBatteryVoltage.setText( arg1.getIntExtra("voltage",0) + "mv");
            tvCurrentTemperature.setText(arg1.getIntExtra("temperature",0)/10.0f+"度");
            tvRechangeStatus.setText(TimeFormatUtils.getBatteryStatus(arg1.getIntExtra("status",0)));
        }

    }


}
