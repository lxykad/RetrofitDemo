package com.lxy.retrofit;

import com.lxy.retrofit.rx.RxActivity;
import com.lxy.retrofit.rx.RxBean;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HTTP;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lxy
 * 对创建Retrofit的过程进行封装
 */

public class HttpHelper {

    public static String BASE_URL = "http://140.207.75.158/";
    public static String GANK_BASE_URL = "http://gank.io/api/data/Android/";

    //设置默认timeout时间
    private static final int DEFAULT_TIMEOUT = 10;

    //
    private Retrofit retrofit;

    //
    private RxActivity.RxService rxService;

    private static HttpHelper mHelper = new HttpHelper();

    //私有化构造方法
    private HttpHelper() {

        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://gank.io/api/data/Android/")
                .build();
        rxService = retrofit.create(RxActivity.RxService.class);

    }

    public static HttpHelper getInstance() {
        return mHelper;
    }

    public void loadData(Subscriber<RxBean> subscriber) {
        rxService.loadData("", "")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
