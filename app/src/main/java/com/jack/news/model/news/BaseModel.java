package com.jack.news.model.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/11/8
 **/
public class BaseModel {

    /**
     * 状态信息
     */
    @Expose
    @SerializedName("reason")
    public String reason;
    /**
     * result
     */
    @Expose
    @SerializedName("result")
    public NewsList result;
    /**
     * error code
     */
    @Expose
    @SerializedName("error_code")
    public String errorCode;
}
