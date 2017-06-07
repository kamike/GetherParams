package com.pursuege.gether.android.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wangtao on 2017/6/7.
 */

public class PhoneInfoUtils {

    private TelephonyManager telephonyManager;

    public PhoneInfoUtils(Context c) {
        telephonyManager = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
    }


    public String getMainIMEI() {

        return telephonyManager.getDeviceId();
    }

    public String getMainNumber() {
        return telephonyManager.getLine1Number();
    }

    public String getMainIMSI() {
        return telephonyManager.getSubscriberId();
    }

    public static boolean isRoot() {
        boolean bool = false;

        try {
            if ((!new File("/system/bin/su").exists()) && (!new File("/system/xbin/su").exists())) {
                bool = false;
            } else {
                bool = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }

    public static long getMaxCpuFreq() {

        String result = "";

        ProcessBuilder cmd;

        try {

            String[] args = {"/system/bin/cat",

                    "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"};

            cmd = new ProcessBuilder(args);

            Process process = cmd.start();

            InputStream in = process.getInputStream();

            byte[] re = new byte[24];

            while (in.read(re) != -1) {

                result = result + new String(re, "UTF-8");

            }

            in.close();

        } catch (IOException ex) {

            ex.printStackTrace();

            result = "N/A";

        }
        if (TextUtils.isEmpty(result)) {
            return 0;
        }

        long res = 0;

        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < result.length(); i++) {
                if (result.charAt(i) >= 48 && result.charAt(i) <= 57) {
                    sb.append(result.charAt(i));
                } else {
                    break;
                }
            }
            res = Long.parseLong(sb.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Logger.i("主频:" + result);
        }

        return res;
    }

    public static String getCpuName() {

        try {
            FileReader fr = new FileReader("/proc/cpuinfo");

            BufferedReader br = new BufferedReader(fr);

            String text = br.readLine();

            String[] array = text.split(":\\s+", 2);

            for (int i = 0; i < array.length; i++) {

            }

            return array[1];

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;

    }

    public static void getCupInfo() {
        String str1 = "/proc/meminfo";
        String str2 = "";
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            while ((str2 = localBufferedReader.readLine()) != null) {
                Logger.i(""+str2);
            }
        } catch (IOException e) {
        }
    }
}
