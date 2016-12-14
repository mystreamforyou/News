package com.jack.news.utils;

import android.content.Context;
import android.util.Log;

import com.jack.news.model.channel.Channel;
import com.jack.news.model.news.NewsType;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/12/14
 **/


public class RealmUtils {

    /**
     * 2016.12.7
     * 版本号。如果版本号比现有的高，必须提供一个 migration，否则抛异常
     */
    private static final long DB_VERSION = 1;

    private static final String TAG = "Realm";

    private static final String PUBLIC_DB = "_news.realm";

    private static Map<String, Realm> realmMap = new HashMap<>();

    public static void init(Context context) {
        Log.i(TAG, "realm init start " + System.currentTimeMillis());
        Realm.init(context);
        realmMap.put(Channel.TOP, Realm.getInstance(realmConfigFactory(Channel.TOP)));
        realmMap.put(Channel.SHEHUI, Realm.getInstance(realmConfigFactory(Channel.SHEHUI)));
        realmMap.put(Channel.GUONEI, Realm.getInstance(realmConfigFactory(Channel.GUONEI)));
        realmMap.put(Channel.GUOJI, Realm.getInstance(realmConfigFactory(Channel.GUOJI)));
        realmMap.put(Channel.YULE, Realm.getInstance(realmConfigFactory(Channel.YULE)));
        realmMap.put(Channel.TIYU, Realm.getInstance(realmConfigFactory(Channel.TIYU)));
        realmMap.put(Channel.JUNSHI, Realm.getInstance(realmConfigFactory(Channel.JUNSHI)));
        realmMap.put(Channel.KEJI, Realm.getInstance(realmConfigFactory(Channel.KEJI)));
        realmMap.put(Channel.CAIJING, Realm.getInstance(realmConfigFactory(Channel.CAIJING)));
        realmMap.put(Channel.SHISHANG, Realm.getInstance(realmConfigFactory(Channel.SHISHANG)));
        Log.i(TAG, "realm map size " + realmMap.size());
        Log.i(TAG, "realm init end " + System.currentTimeMillis());
    }

    public static Realm getRealm(final int index) {
        Realm realm = Realm.getDefaultInstance();
        switch (index) {
            case NewsType.TYPE_TOP:
                realm = realmMap.get(Channel.TOP);
                break;
            case NewsType.TYPE_SHEHUI:
                realm = realmMap.get(Channel.SHEHUI);
                break;
            case NewsType.TYPE_GUONEI:
                realm = realmMap.get(Channel.GUONEI);
                break;
            case NewsType.TYPE_GUOJI:
                realm = realmMap.get(Channel.GUOJI);
                break;
            case NewsType.TYPE_YULE:
                realm = realmMap.get(Channel.YULE);
                break;
            case NewsType.TYPE_TIYU:
                realm = realmMap.get(Channel.TIYU);
                break;
            case NewsType.TYPE_JUNSHI:
                realm = realmMap.get(Channel.JUNSHI);
                break;
            case NewsType.TYPE_KEJI:
                realm = realmMap.get(Channel.KEJI);
                break;
            case NewsType.TYPE_CAIJING:
                realm = realmMap.get(Channel.CAIJING);
                break;
            case NewsType.TYPE_SHISHANG:
                realm = realmMap.get(Channel.SHISHANG);
                break;
        }
        return realm;
    }

    public static RealmConfiguration realmConfigFactory(String name) {
        return new RealmConfiguration.Builder()
                .name(name + PUBLIC_DB)
                .schemaVersion(DB_VERSION)
                .build();
    }

    public static void closeRealms() {
        if (realmMap.containsKey(Channel.TOP) && realmMap.get(Channel.TOP).isClosed()) {
            realmMap.get(Channel.TOP).close();
        }
        if (realmMap.containsKey(Channel.SHEHUI) && realmMap.get(Channel.SHEHUI).isClosed()) {
            realmMap.get(Channel.SHEHUI).close();
        }
        if (realmMap.containsKey(Channel.GUONEI) && realmMap.get(Channel.GUONEI).isClosed()) {
            realmMap.get(Channel.GUONEI).close();
        }
        if (realmMap.containsKey(Channel.GUOJI) && realmMap.get(Channel.GUOJI).isClosed()) {
            realmMap.get(Channel.GUOJI).close();
        }
        if (realmMap.containsKey(Channel.YULE) && realmMap.get(Channel.YULE).isClosed()) {
            realmMap.get(Channel.YULE).close();
        }
        if (realmMap.containsKey(Channel.TIYU) && realmMap.get(Channel.TIYU).isClosed()) {
            realmMap.get(Channel.TIYU).close();
        }
        if (realmMap.containsKey(Channel.JUNSHI) && realmMap.get(Channel.JUNSHI).isClosed()) {
            realmMap.get(Channel.JUNSHI).close();
        }
        if (realmMap.containsKey(Channel.KEJI) && realmMap.get(Channel.KEJI).isClosed()) {
            realmMap.get(Channel.KEJI).close();
        }
        if (realmMap.containsKey(Channel.CAIJING) && realmMap.get(Channel.CAIJING).isClosed()) {
            realmMap.get(Channel.CAIJING).close();
        }
        if (realmMap.containsKey(Channel.SHISHANG) && realmMap.get(Channel.SHISHANG).isClosed()) {
            realmMap.get(Channel.SHISHANG).close();
        }
    }
}
