package com.jack.news.config;

import com.jack.common.rest.CommonConfig;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/11/17
 **/


public class AppConfig implements CommonConfig {

    String API_BASE_URL = "http://v.juhe.cn";

    String APP_KEY = "a7d972b364c67fed392ceeac81629699";

    private static AppConfig instance;

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    @Override
    public String getApiBaseUrl() {
        return API_BASE_URL;
    }

    public String getAPP_KEY() {
        return APP_KEY;
    }

    public static final String SHARE_PREFERENCES_NAME = ".newme.pre";
}
