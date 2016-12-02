package com.jack.news.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;

import com.jack.common.utils.LogUtils;
import com.jack.news.R;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Em on 2015/12/17.
 */
public class SettingFragment extends PreferenceFragment {

    private static final String TAG = "SettingFragment";

    private Observable<String> observableCache;

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
    }

    /**
     * 清除缓存
     */
    private void clearCache() {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.clear_cache)
                .setMessage(R.string.clear_cache_tip)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        observableCache.subscribe(new Action1<String>() {
                            @Override
                            public void call(String result) {
                                LogUtils.d(TAG, getString(R.string.success_clear_cache));
                                showMsgShort(getString(R.string.success_clear_cache));
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable e) {
                                LogUtils.e(TAG, getString(R.string.error), e);
                                showMsgShort(getString(R.string.error_clear_cache));
                            }
                        });
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    /**
     * snack show an short message
     * @param msg
     */
    protected void showMsgShort(String msg) {
        showMsg(msg, Snackbar.LENGTH_SHORT);
    }

    /**
     * snack show an long message
     * @param msg
     */
    protected void showMsgLong(String msg) {
        showMsg(msg, Snackbar.LENGTH_SHORT);
    }

    private void showMsg(String msg, int duration) {
    }

    /**
     *
     */
    private static class ChoiceOnClickListener implements DialogInterface.OnClickListener {

        private int which = 0;
        @Override
        public void onClick(DialogInterface dialogInterface, int which) {
            this.which = which;
        }

        public int getWhich() {
            return which;
        }
    }
}
