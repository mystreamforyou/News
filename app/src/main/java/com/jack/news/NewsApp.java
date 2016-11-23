package com.jack.news;

import com.facebook.stetho.Stetho;
import com.jack.common.CommonApp;
import com.jack.common.rest.RestUtils;
import com.jack.news.config.AppConfig;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/11/17
 **/


public class NewsApp extends CommonApp {

    public void onCreate() {
        super.onCreate();
        RestUtils.init(AppConfig.getInstance());
        Stetho.initializeWithDefaults(this);
    }
}
