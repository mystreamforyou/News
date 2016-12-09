package com.jack.news.model.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/11/10
 **/

public class News extends RealmObject {

    /**
     * uniquekey
     */
    @Expose
    @SerializedName("uniquekey")
    public String uniquekey;
    /**
     * 标题
     */
    @Expose
    @SerializedName("title")
    public String title;
    /**
     * 日期
     */
    @Expose
    @SerializedName("date")
    public String date;
    /**
     * 出处
     */
    @Expose
    @SerializedName("author_name")
    public String authorName;
    /**
     * 图片1
     */
    @Expose
    @SerializedName("thumbnail_pic_s")
    public String thumbnailPicS;
    /**
     * 图片2
     */
    @Expose
    @SerializedName("thumbnail_pic_s02")
    public String thumbnailPicS02;
    /**
     * 图片3
     */
    @Expose
    @SerializedName("thumbnail_pic_s03")
    public String thumbnailPicS03;
    /**
     * 跳转地址 （客户端做判断 如果为 空字符串，不跳转）
     */
    @Expose
    @SerializedName("url")
    public String url;
    /**
     * 类别
     */
    @Expose
    @SerializedName("type")
    public String type;
    /**
     * 区域
     */
    @Expose
    @SerializedName("realtype")
    public String realtype;

    @Override
    public String toString() {
        return "News{" +
                "uniquekey='" + uniquekey + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", authorName='" + authorName + '\'' +
                ", thumbnailPicS='" + thumbnailPicS + '\'' +
                ", thumbnailPicS02='" + thumbnailPicS02 + '\'' +
                ", thumbnailPicS03='" + thumbnailPicS03 + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", realtype='" + realtype + '\'' +
                '}';
    }
}
