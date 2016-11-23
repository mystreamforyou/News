package com.jack.news.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.jack.common.lce.MvpLceFragment;
import com.jack.news.R;
import com.jack.news.model.news.News;
import com.jack.news.model.news.NewsList;
import com.jack.news.presenter.NewsPresenter;
import com.jack.news.ui.adapter.NewsAdapter;
import com.jack.news.view.NewsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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

    NewsAdapter adapter;

    List<News> newses;

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRylView();
        loadData(false);
    }

    private void initRylView() {
        newses = new ArrayList<>();
        adapter = new NewsAdapter(newses, getContext());
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(true);
            }
        });
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_channel;
    }

    @Override
    public void setData(NewsList data) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        Log.i(TAG, data.toString());
        if (newses.size() > 0) {
            newses.clear();
        }
        newses.addAll(data.data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().getNewsByType(type);
    }

    @Override
    public NewsPresenter createPresenter() {
        return new NewsPresenter();
    }
}
