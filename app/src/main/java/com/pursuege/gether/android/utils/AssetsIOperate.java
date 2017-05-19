package com.pursuege.gether.android.utils;

import android.content.Context;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by wangtao on 2017/5/17.
 */

public class AssetsIOperate {

    public ArrayList getAssetsData(Context c,String fileName,Class classBean){
        try {
            InputStream in=c.getAssets().open(fileName);
            BufferedReader reader=new BufferedReader(new InputStreamReader(in));
            String str=null;
            StringBuilder sb=new StringBuilder();
            while((str=reader.readLine())!=null){
                sb.append(str);
            }
            in.close();
            ArrayList array= (ArrayList) JSON.parseArray(sb.toString(),classBean);
            return array;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
