package com.jack.news.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jack.common.base.BaseActivity;
import com.jack.common.utils.NetWorkUtils;
import com.jack.news.R;
import com.jack.news.widget.TopActionBar;

import butterknife.BindView;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/1/6
 **/

public class WebViewActivity extends BaseActivity implements TopActionBar.BackListener {
    @BindView(R.id.wv_view)
    WebView webView;

    @BindView(R.id.topbar)
    TopActionBar topActionBar;

    @BindView(R.id.loadingView)
    ProgressBar progressBar;

    @BindView(R.id.errorView)
    LinearLayout errorView;

    @BindView(R.id.errorTextView)
    TextView errorTextView;

    String url;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_problem, null);
        setContentView(view);
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        url = intent.getStringExtra("url");
        android.util.Log.i("WebView", "url : " + url);

        initTitleBar();
        initWebView();
    }

    private void initTitleBar() {
        topActionBar.setTitle(R.string.detail);
        topActionBar.setBackListener(this);
    }

    private void initWebView() {
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        errorTextView.setText("无网络连接");
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //WebView加载web资源
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                android.util.Log.i("WebView", "url : " + url);
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (!progressBar.isShown())
                    progressBar.setVisibility(View.VISIBLE);
                if (NetWorkUtils.isNetConnected(WebViewActivity.this)) {
                    errorView.setVisibility(View.GONE);

                } else {
                    webView.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (progressBar.isShown()) {
                    progressBar.setVisibility(View.GONE);
                }
                if (NetWorkUtils.isNetConnected(WebViewActivity.this)) {
                    webView.setVisibility(View.VISIBLE);
                    errorView.setVisibility(View.GONE);
                } else {
                    webView.setVisibility(View.GONE);
                    errorView.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                errorView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        webView.reload();
                        errorView.setVisibility(View.GONE);
                    }
                });
            }
        });

        webView.setOnKeyListener(new View.OnKeyListener() {
                                     @Override
                                     public boolean onKey(View v, int keyCode, KeyEvent event) {
                                         if (event.getAction() == KeyEvent.ACTION_UP
                                                 && keyCode == KeyEvent.KEYCODE_BACK
                                                 && webView.canGoBack()) {
                                             webView.goBack();// 返回前一个页面
                                             return true;
                                         }
                                         return false;
                                     }
                                 }

        );
        webView.loadUrl(url);
    }

    @Override
    public void onStop() {
        super.onStop();
        webView.stopLoading();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    public void onBack() {
        finish();
    }
}
