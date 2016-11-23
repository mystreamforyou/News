package com.jack.news.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jack.news.ui.fragment.NewsFragment;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/11/21
 **/


public class ChannelPagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    private List<NewsFragment> mFragments;

    public ChannelPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    public void setmFragments(List<NewsFragment> mFragments) {
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments == null ? null : mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }
}
