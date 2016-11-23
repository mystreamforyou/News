package com.jack.news.api;

import com.jack.news.model.news.BaseModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/11/10
 **/


public interface NewsApi {

    /**
     * @param type 类型,,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
     * @param key
     * @return
     */
    @FormUrlEncoded
    @POST("/toutiao/index")
    Observable<BaseModel> queryNewsByType(@Field("type") String type, @Field("key") String key);


}
