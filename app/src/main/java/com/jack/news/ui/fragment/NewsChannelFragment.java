package com.jack.news.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.jack.common.base.BaseFragment;
import com.jack.news.R;
import com.jack.news.model.channel.Channel;
import com.jack.news.ui.adapter.ChannelPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/11/18
 **/


public class NewsChannelFragment extends BaseFragment {

    private static final String TAG = "NewsChannelFragment";
    private static final String BUNDLE_ID = "id";
    private static final String BUNDLE_NAME = "name";

    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;

    NewsFragment fragment;

    private ChannelPagerAdapter adapter;
    private List<NewsFragment> fragments;

    public static NewsChannelFragment newInstance() {
        NewsChannelFragment fragment = new NewsChannelFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initChannel();
        initFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_news;
    }

    private void initFragment() {
        Log.i(TAG, "-------- initFragment");
        adapter = new ChannelPagerAdapter(getActivity().getSupportFragmentManager(), getActivity());
        viewPager.setAdapter(adapter);
        fragment = NewsFragment.newInstance();
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.add(fragment);
        adapter.setmFragments(fragments);
        adapter.notifyDataSetChanged();
        Log.i(TAG, "-------- fragments");
    }

    private void initChannel() {
        for (int i = 0; i < Channel.getChannelList().size(); i++) {
            if (0 == i) {
                tabLayout.addTab(tabLayout.newTab().setText(Channel.getChannelList().get(i).name), true);
            } else {
                tabLayout.addTab(tabLayout.newTab().setText(Channel.getChannelList().get(i).name));
            }
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int i = tabLayout.getSelectedTabPosition();
                Log.i(TAG, "-------- onTabSelected " + i + " " + tab.getText().toString() + " " + Channel.getChannelList().get(i).channelId.toString());
                fragment.setType(Channel.getChannelList().get(i).channelId.toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
