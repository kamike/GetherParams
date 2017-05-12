package com.pursuege.gether.android.utils;

import android.os.Environment;
import android.text.TextUtils;

import com.pursuege.gether.android.AllUrlStrings;

import java.io.File;
import java.io.IOException;

/**
 * Created by wangtao on 2017/5/12.
 */

public class FileOperate implements AllUrlStrings {
    private static String strSdcardDir;

    public static void init() {
        strSdcardDir = Environment.getExternalStorageDirectory() + "/" + strSdcardDir;
        File file = new File(strSdcardDir);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    public static File getSdcardFileDir(String strImgDir) {
        File file = new File(strSdcardDir);

        if (TextUtils.isEmpty(strImgDir)) {
            return file;
        }
        file = new File(strSdcardDir + "/" + strImgDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File createFile(String dir, String name) {
        File file = new File(strSdcardDir + "/" + dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        File f = new File(file.getAbsolutePath() + "/" + name);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return f;
    }
}
