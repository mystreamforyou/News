package com.jack.news.presenter;

import android.util.Log;

import com.jack.common.mvp.MvpPresenter;
import com.jack.common.rest.RestUtils;
import com.jack.news.api.NewsApi;
import com.jack.news.config.AppConfig;
import com.jack.news.model.channel.Channel;
import com.jack.news.model.news.BaseModel;
import com.jack.news.model.news.NewsType;
import com.jack.news.view.NewsView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/11/21
 **/


public class NewsPresenter extends MvpPresenter<NewsView> {


    public void getNewsByType(String type) {
        RestUtils.createApi(NewsApi.class).queryNewsByType(type, AppConfig.getInstance().getAPP_KEY())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<BaseModel>() {
            @Override
            public void onCompleted() {
                Log.i("NewsFragment", "-------- onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("NewsFragment", "-------- onError " + e.getLocalizedMessage());
                getView().showError(e, false);
            }

            @Override
            public void onNext(BaseModel newsModel) {
                Log.i("NewsFragment", "-------- " + newsModel.toString());
                if (isViewAttached()) {
                    getView().setData(newsModel.result);
                    getView().showContent();
                }
            }
        });
    }

    public int getNewsType(String type) {
        if (Channel.TOP.equals(type)) {
            return NewsType.TYPE_TOP;
        } else if (Channel.SHEHUI.equals(type)) {
            return NewsType.TYPE_SHEHUI;
        } else if (Channel.GUONEI.equals(type)) {
            return NewsType.TYPE_GUONEI;
        } else if (Channel.GUOJI.equals(type)) {
            return NewsType.TYPE_GUOJI;
        } else if (Channel.YULE.equals(type)) {
            return NewsType.TYPE_YULE;
        } else if (Channel.TIYU.equals(type)) {
            return NewsType.TYPE_TIYU;
        } else if (Channel.JUNSHI.equals(type)) {
            return NewsType.TYPE_JUNSHI;
        } else if (Channel.KEJI.equals(type)) {
            return NewsType.TYPE_KEJI;
        } else if (Channel.CAIJING.equals(type)) {
            return NewsType.TYPE_CAIJING;
        } else {
            return NewsType.TYPE_SHISHANG;
//            Channel.SHISHANG.equals(type)
        }
    }

}
