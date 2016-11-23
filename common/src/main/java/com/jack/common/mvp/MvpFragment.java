package com.jack.common.mvp;

import android.os.Bundle;
import android.view.View;

import com.jack.common.base.BaseFragment;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/11/18
 **/


public abstract class MvpFragment<V extends MvpView, P extends MvpPresenter> extends BaseFragment implements IMvp<V, P>, MvpView {

    /**
     * The presenter for this view. Will be instantiated with {@link #createPresenter()}
     */
    protected P presenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (presenter == null) {
            presenter = createPresenter();
        }
        presenter.attachView(getMvpView());
    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    /**
     * Creates a new presenter instance, if needed. Will reuse the previous presenter instance if
     * {@link #setRetainInstance(boolean)} is set to true. This method will be called from
     * {@link #onViewCreated(View, Bundle)}
     */
    @Override
    public abstract P createPresenter();

    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    public boolean isRetainingInstance() {
        return getRetainInstance();
    }
}
