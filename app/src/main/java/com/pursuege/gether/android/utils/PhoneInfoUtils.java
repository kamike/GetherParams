package com.pursuege.gether.android.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.regex.Pattern;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by wangtao on 2017/6/7.
 */

public class PhoneInfoUtils {

    private final Context context;
    private TelephonyManager telephonyManager;

    public PhoneInfoUtils(Context c) {
        this.context = c;
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


    public static HashMap<String, String> getMemInfo() {
        String str1 = "/proc/meminfo";
        String str2 = "";
        HashMap<String, String> hashMap = new HashMap();

        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            while ((str2 = localBufferedReader.readLine()) != null) {
                String[] array = str2.split(":");
                if (array.length > 1) {
                    hashMap.put(array[0], array[1].trim().split(" ")[0]);
                }
            }
            localBufferedReader.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e2) {
            e2.printStackTrace();
        }
        return hashMap;
    }

    public static HashMap<String, String> getCpuInfo() {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        HashMap<String, String> hashMap = new HashMap();

        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            while ((str2 = localBufferedReader.readLine()) != null) {
                String[] array = str2.split(":");
                if (array.length > 1) {
                    hashMap.put(array[0].trim(), array[1].trim());
                }
            }
            localBufferedReader.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e2) {
            e2.printStackTrace();
        }
        return hashMap;
    }

    public long getAvailMemory() {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        return mi.availMem;
    }

    /**
     * 获得SD卡总大小
     *
     * @return
     */
    public static long getSDTotalSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return blockSize * totalBlocks;
    }

    /**
     * 获得sd卡剩余容量，即可用大小
     *
     * @return
     */
    public static long getSDAvailableSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return blockSize * availableBlocks;
    }

    /**
     * BASEBAND-VER
     * 基带版本
     * return String
     */

    public static String getBaseband_Ver() {
        String Version = "";
        try {
            Class cl = Class.forName("android.os.SystemProperties");
            Object invoker = cl.newInstance();
            Method m = cl.getMethod("get", new Class[]{String.class, String.class});
            Object result = m.invoke(invoker, new Object[]{"gsm.version.baseband", "no message"});
// System.out.println(">>>>>>><<<<<<<" +(String)result);
            Version = (String) result;
        } catch (Exception e) {
        }
        return Version;
    }

    public static int getCpuNumCores() {
        //Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                //Check if filename is "cpu", followed by a single digit number
                if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }

        try {
            //Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            //Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            Log.d(TAG, "CPU Count: " + files.length);
            //Return the number of cores (virtual CPU devices)
            return files.length;
        } catch (Exception e) {
            //Print exception
            Log.d(TAG, "CPU Count: Failed.");
            e.printStackTrace();
            //Default to return 1 core
            return 1;
        }
    }


}
