package com.jack.common.rest;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jack.common.CommonApp;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/11/11
 **/


public class RestUtils {

    public interface ParameterKeys {
        /**
         * 协议header字段
         */
        //********************* HEADER ******************************
        String KEY_PLATFORM = "platform";
        String KEY_TIME = "time";
        String KEY_USERID = "userId";
        String KEY_SIGN = "sign";
        String KEY_TOKEN = "token";
        String KEY_DEVICEID = "deviceId";

    }

    private static final String TAG = "RestUtils";
    //---------------OkHttp配置-----------------------
    private static String RESPONSE_CACHE = "netCache";

    private static long RESPONSE_CACHE_SIZE = 10 * 1024 * 1024;

    private static long HTTP_CONNECT_TIMEOUT = 1000 * 30;

    private static long HTTP_READ_TIMEOUT = HTTP_CONNECT_TIMEOUT;

    private static RestUtils Instance;

    private Retrofit retrofit;

    private CommonConfig commonConfig;

    public static void init(CommonConfig config) {
        Log.i(TAG, "-------- init");
        Instance = new RestUtils(config);
    }

    private RestUtils(CommonConfig config) {
        Log.i(TAG, "-------- RestUtils");
        commonConfig = config;
        retrofit = getRetrofit();
    }

    public static <T> T createApi(Class<T> clazz) {
        if (Instance == null) {
            throw new RuntimeException("init RestUtils frist!");
        }
        return Instance.retrofit.create(clazz);
    }

    private OkHttpClient getOkHttpClient() {
        Context context = CommonApp.getInstance();
        File cacheDir = new File(context.getCacheDir(), RESPONSE_CACHE);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(new Cache(cacheDir, RESPONSE_CACHE_SIZE))
                .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(getTokenInterceptor());
//        if (true) {
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//            builder.addInterceptor(logging);
//            builder.addNetworkInterceptor(new StethoInterceptor());
//        }
        return builder.build();
    }


    private Retrofit getRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        builder.baseUrl(commonConfig.getApiBaseUrl());//设置远程地址
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        builder.client(getOkHttpClient());
        return builder.build();
    }

    private Interceptor getTokenInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder newRequestBuilder = request.newBuilder();
                request = newRequestBuilder.build();
                Response response = chain.proceed(request);
                return response;
            }
        };
    }
}
