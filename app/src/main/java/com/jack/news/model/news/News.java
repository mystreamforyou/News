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
    private String uniquekey;
    /**
     * 标题
     */
    @Expose
    @SerializedName("title")
    private String title;
    /**
     * 日期
     */
    @Expose
    @SerializedName("date")
    private String date;
    /**
     * 出处
     */
    @Expose
    @SerializedName("author_name")
    private String authorName;
    /**
     * 图片1
     */
    @Expose
    @SerializedName("thumbnail_pic_s")
    private String thumbnailPicS;
    /**
     * 图片2
     */
    @Expose
    @SerializedName("thumbnail_pic_s02")
    private String thumbnailPicS02;
    /**
     * 图片3
     */
    @Expose
    @SerializedName("thumbnail_pic_s03")
    private String thumbnailPicS03;
    /**
     * 跳转地址 （客户端做判断 如果为 空字符串，不跳转）
     */
    @Expose
    @SerializedName("url")
    private String url;
    /**
     * 类别
     */
    @Expose
    @SerializedName("type")
    private String type;
    /**
     * 区域
     */
    @Expose
    @SerializedName("realtype")
    private String realtype;

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

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getThumbnailPicS() {
        return thumbnailPicS;
    }

    public void setThumbnailPicS(String thumbnailPicS) {
        this.thumbnailPicS = thumbnailPicS;
    }

    public String getThumbnailPicS02() {
        return thumbnailPicS02;
    }

    public void setThumbnailPicS02(String thumbnailPicS02) {
        this.thumbnailPicS02 = thumbnailPicS02;
    }

    public String getThumbnailPicS03() {
        return thumbnailPicS03;
    }

    public void setThumbnailPicS03(String thumbnailPicS03) {
        this.thumbnailPicS03 = thumbnailPicS03;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRealtype() {
        return realtype;
    }

    public void setRealtype(String realtype) {
        this.realtype = realtype;
    }
}
