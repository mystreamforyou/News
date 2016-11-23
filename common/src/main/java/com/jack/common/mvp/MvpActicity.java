package com.jack.common.mvp;

import android.os.Bundle;

import com.jack.common.base.BaseActivity;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/11/18
 **/


public abstract class MvpActicity<V extends MvpView, P extends MvpPresenter<V>> extends BaseActivity implements IMvp<V, P>, MvpView {

    protected P presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null) {
            presenter = createPresenter();
        }
        presenter.attachView(getMvpView());
    }

    @Override
    public abstract P createPresenter();

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    public boolean isRetainingInstance() {
        return false;
    }
}
