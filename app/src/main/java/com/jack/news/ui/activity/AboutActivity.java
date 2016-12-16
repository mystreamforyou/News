package com.jack.news.ui.activity;

import android.os.Bundle;

import com.jack.common.base.BaseActivity;
import com.jack.news.R;
import com.jack.news.widget.TopActionBar;

import butterknife.BindView;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络技术有限公司
 * Author     : jiaxiaowei
 * Date       : 2016/1/5 9:59
 */
public class AboutActivity extends BaseActivity implements TopActionBar.BackListener{

    @BindView(R.id.topbar)
    TopActionBar topActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_about);
        super.onCreate(savedInstanceState);
        initTopbar();
    }

    private void initTopbar() {
        topActionBar.setTitle(R.string.about);
        topActionBar.setBackListener(this);
    }

    @Override
    public void onBack() {
        finish();
    }
}
