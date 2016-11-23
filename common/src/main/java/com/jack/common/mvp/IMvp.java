package com.jack.common.mvp;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/11/18
 **/


public interface IMvp<V extends MvpView, P extends MvpPresenter> {

    /**
     * Creates the presenter instance
     *
     * @return the created presenter instance
     */
    P createPresenter();

    /**
     * Get the presenter. If null is returned, then a internally a new presenter instance gets
     * created
     * by calling {@link #createPresenter()}
     *
     * @return the presenter instance. can be null.
     */
    P getPresenter();

    /**
     * Get the MvpView for the presenter
     *
     * @return The view associated with the presenter
     */
    V getMvpView();

    /**
     * Is the view retaining? This boolean flag is used for {@link MvpPresenter#detachView(boolean)}
     * as parameter.
     *
     * @return true if the view is retaining, hence the presenter should be retaining as well.
     */
    boolean isRetainingInstance();

}
