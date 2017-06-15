package com.pursuege.gether.android.utils;

import android.os.BatteryManager;

import java.text.DecimalFormat;

/**
 * Created by wangtao on 2017/6/7.
 */

public class TimeFormatUtils {
    public static String getTimeString(long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;
        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {
            sb.append(hour + "小时");
        }

        sb.append(minute + "分钟");


        sb.append(second + "秒");
//        if(milliSecond > 0) {
//            sb.append(milliSecond+"毫秒");
//        }
        return sb.toString();
    }

    public static final int GB = 1024 * 1024 * 1024;
    public static final int MB = 1024 * 1024;
    public static final int KB = 1024;

    public static String getGMKB(long mobileRxBytes) {
        DecimalFormat format = new java.text.DecimalFormat("#.00");
        if (mobileRxBytes > GB) {
            return format.format(mobileRxBytes * 1.0 / GB) + "GB";
        }
        if (mobileRxBytes > MB) {
            return format.format(mobileRxBytes * 1.0 / MB) + "MB";
        }
        if (mobileRxBytes > KB) {
            return format.format(mobileRxBytes * 1.0 / KB) + "KB";
        }
        return mobileRxBytes + "B";

    }

    public static String getGMKHZ(long hz) {
        DecimalFormat format = new java.text.DecimalFormat("#.00");
        if (hz > GB) {
            return format.format(hz * 1.0 / GB) + "GHz";
        }
        if (hz > MB) {
            return format.format(hz * 1.0 / MB) + "MHz";
        }
        if (hz > KB) {
            return format.format(hz * 1.0 / KB) + "KHz";
        }
        return hz + "Hz";

    }

    public static String getGMKBForKb(String memFree) {
        long kb = 0;
        try {
            kb = Long.parseLong(memFree);

        } catch (NumberFormatException e) {

        }

        DecimalFormat format = new java.text.DecimalFormat("#.00");
        if (kb > MB) {
            return format.format(kb * 1.0 / MB) + "GB";
        }
        if (kb > kb) {
            return format.format(kb * 1.0 / kb) + "MB";
        }

        return kb + "KB";
    }

    public static String getPixString(int[] back) {
        if (back == null || back.length <= 1) {
            return "0像素";
        }
        int size = back[0] * back[1] / 10000;

        return size + "万像素";
    }

    public static String intToIp(int ip) {
        return (ip & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "."
                + ((ip >> 24) & 0xFF);
    }

    public static String getBatteryStatus(int status) {
        switch (status) {
            case BatteryManager.BATTERY_STATUS_FULL:
                return "充满状态";
            case BatteryManager.BATTERY_STATUS_CHARGING://充电状态。
                return "充电状态";
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                return "放电状态";
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                return "未充满状态";
            default:
                return "未知状态";

        }
    }
}
