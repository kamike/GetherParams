package com.pursuege.gether.android.utils;

import android.hardware.Camera;

import java.util.List;

/**
 * Created by wangtao on 2017/6/12.
 */

public class CameraUtils {
    public static final int CAMERA_FACING_BACK = 0;
    public static final int CAMERA_FACING_FRONT = 1;
    public static final int CAMERA_NONE = 2;

    public static int HasBackCamera()
    {
        int numberOfCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo cameraInfo = new  Camera.CameraInfo();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == CAMERA_FACING_BACK) {
                return i;
            }
        }
        return 2;
    }

    public static int HasFrontCamera()
    {
        int numberOfCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == CAMERA_FACING_FRONT) {
                return i;
            }
        }
        return 2;
    }

    public static int[] getCameraPixels(int paramInt)
    {

        if (paramInt == 2) {
            return new int[]{0,0};
        }
        Camera localCamera = Camera.open(paramInt);
        Camera.Parameters localParameters = localCamera.getParameters();
        localParameters.set("camera-id", 1);
        List<Camera.Size> localList = localParameters.getSupportedPictureSizes();

        if (localList != null)
        {
            int width=0,height=0;
            for (int i = 0; i < localList.size(); i++)
            {
                Camera.Size size = (Camera.Size) localList.get(i);
                if(size.width>width){
                    width=size.width;
                }
                if(size.height>height){
                    height=size.height;
                }
            }


            localCamera.release();
            return new int[]{width,height};

        }
        return new int[]{0,0};
    }


}
