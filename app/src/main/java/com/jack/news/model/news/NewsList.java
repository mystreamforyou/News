package com.jack.news.model.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/11/10
 **/


public class NewsList {

    /**
     * stat
     */
    @Expose
    @SerializedName("stat")
    public String stat;
    /**
     * data
     */
    @Expose
    @SerializedName("data")
    public List<News> data;

    @Override
    public String toString() {
        return "NewsList{" +
                "stat='" + stat + '\'' +
                ", data=" + data +
                '}';
    }
}
