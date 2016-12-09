package com.jack.news.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jack.news.R;

public class TopActionBar extends RelativeLayout implements View.OnClickListener {
    private static final String TAG = "View";
    public static final byte BACK_IMAGE = 0;
    public static final byte BACK_TXT = 1;

    ImageView ivBack;
    TextView tvTitle;
    ImageView ivAction;

    private BackListener backListener;
    private ActionListener actionListener;

    public TopActionBar(Context context) {
        super(context);
        initView(null);
    }

    public TopActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public TopActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }


    private void initView(AttributeSet attrs) {
        Context context = getContext();
        Log.i(TAG, "initView: ");
        View.inflate(context, R.layout.top_bar, this);
        Log.i(TAG, "initView: inflate finish!");

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivAction = (ImageView) findViewById(R.id.iv_action);

        if (attrs != null) {
            @SuppressLint("Recycle")
            TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.TopActionBar);
            String text = t.getString(R.styleable.TopActionBar_text);
            t.recycle();

            if (!TextUtils.isEmpty(text)) {
                tvTitle.setText(text);
            }
        }

        this.setBackListener(new BackListener() {
            @Override
            public void onBack() {
            }
        });
        ivBack.setOnClickListener(this);
        ivAction.setOnClickListener(this);
    }

    public void setTitle(@StringRes int resid) {
        tvTitle.setText(resid);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void hideAction() {
        ivAction.setVisibility(View.GONE);
    }

    public void setActionImageResource(@DrawableRes int resId) {
        ivAction.setVisibility(View.VISIBLE);
        ivAction.setImageResource(resId);
    }

    public void setBackImageResource(@DrawableRes int resId) {
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setImageResource(resId);
    }

    //    @OnClick({R.id.ivBack, R.id.tvBack, R.id.tvAction, R.id.ivAction})
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            if (backListener != null) {
                backListener.onBack();
            }

        } else if (id == R.id.iv_action) {
            if (actionListener != null) {
                actionListener.onAction();
            }

        }
    }

    public void setBackListener(BackListener backListener) {
        this.backListener = backListener;
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public interface BackListener {

        void onBack();
    }

    public interface ActionListener {
        void onAction();
    }

}
