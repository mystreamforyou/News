package com.jack.common;


import android.app.Application;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/11/17
 **/


public abstract class CommonApp extends Application {

    private static CommonApp sInstance;

    public static CommonApp getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        sInstance = this;
        super.onCreate();
    }
}
