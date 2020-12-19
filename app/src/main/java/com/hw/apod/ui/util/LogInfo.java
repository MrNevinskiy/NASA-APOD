package com.hw.apod.ui.util;

import android.util.Log;

import com.hw.apod.mvp.view.log.ILogInfo;

public class LogInfo implements ILogInfo {

    public void getLog(String TAG, String info){
        Log.d(TAG, info);
    }

}
