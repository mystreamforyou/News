package com.jack.news.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jack.common.base.BaseActivity;
import com.jack.news.R;
import com.jack.news.ui.fragment.SettingFragment;

import butterknife.BindView;

/**
 * Created by Em on 2015/12/9.
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        this.initActionBar();
        this.initFragment();
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.this.supportFinishAfterTransition();
            }
        });
    }

    private void initFragment() {
        getFragmentManager().beginTransaction().replace(R.id.content, SettingFragment.newInstance()).commit();
    }

    public void onBack() {
        onBackPressed();
        overridePendingTransition(0, R.anim.push_f_right_out);
    }
}
