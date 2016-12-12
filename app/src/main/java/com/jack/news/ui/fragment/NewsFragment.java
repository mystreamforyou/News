package com.jack.news.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.jack.common.lce.MvpLceFragment;
import com.jack.common.utils.RetrofitErrorFilter;
import com.jack.news.R;
import com.jack.news.model.news.News;
import com.jack.news.model.news.NewsList;
import com.jack.news.presenter.NewsPresenter;
import com.jack.news.ui.adapter.NewsAdapter;
import com.jack.news.view.NewsView;

import butterknife.BindView;
import io.realm.Realm;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/11/21
 **/

public class NewsFragment extends MvpLceFragment<NewsList, NewsView, NewsPresenter> implements NewsView {

    private static final String TAG = "NewsFragment";
    private static final String BUNDLE_ID = "id";
    private static final String BUNDLE_NAME = "name";


    @BindView(R.id.refresher)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.content)
    RecyclerView recyclerView;

    private Realm realm;

    private String type = "top";

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setType(String type) {
        this.type = type;
        loadData(false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRylView();
        loadData(false);
    }

    private void initRylView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.i(TAG, "initRylView size " + realm.where(News.class).findAll().size());
        recyclerView.setAdapter(new NewsAdapter(realm.where(News.class).findAllAsync(), getContext()));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(true);
            }
        });
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        //java.net.UnknownHostException: Unable to resolve host "v.juhe.cn": No address associated with hostname
        Log.i(TAG, e.toString());
        return RetrofitErrorFilter.filterError(e).getMessage();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_channel;
    }

    @Override
    public void setData(final NewsList data) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        Log.i(TAG, "setData " + data.toString());
        if (null != data.data && data.data.size() > 0) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.delete(News.class);
                    realm.insert(data.data);
                }
            });
        }
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        showLoading(pullToRefresh);
        Log.i(TAG, "loadData type " + type);
        getPresenter().getNewsByType(type);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        Log.i(TAG, "showError size " + realm.where(News.class).findAll().size());
        loadingView.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
        showTip(getErrorMessage(e, pullToRefresh));
    }

    @Override
    public NewsPresenter createPresenter() {
        return new NewsPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!realm.isClosed())
            realm.close();
    }
}
