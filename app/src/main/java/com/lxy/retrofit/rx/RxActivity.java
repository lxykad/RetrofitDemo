package com.lxy.retrofit.rx;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.common.eventbus.Subscribe;
import com.lxy.retrofit.BR;
import com.lxy.retrofit.HttpHelper;
import com.lxy.retrofit.R;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RxActivity extends AppCompatActivity {

    private ViewDataBinding mBinding;
    private String mBaseUrl = "http://gank.io/api/data/Android/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_rx);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_rx);
        mBinding.setVariable(BR.presenter, new Presenter());


        loadData("20", "1");
    }

    public interface RxService {
        @GET("10/1")
        Observable<RxBean> loadData(@Query("count") String count, @Query("page") String page);

    }


    public class Presenter {

        public void onClick(View view) {

        }
    }

    public void loadData(String count, String page) {

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(mBaseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        RxService rxService = retrofit.create(RxService.class);
//        rxService.loadData(count,page)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<RxBean>() {
//                    @Override
//                    public void onCompleted() {
//                        System.out.println("0000000======onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("0000000======onError==="+e.toString());
//                    }
//
//                    @Override
//                    public void onNext(RxBean bean) {
//                        System.out.println("0000000======onNext==="+bean.getResults().size());
//                    }
//                });

        //封装后
        Subscriber subscriber = new Subscriber<RxBean>() {
            @Override
            public void onStart() {
                //super.onStart();
                System.out.println("0000000======onStart");
            }

            @Override
            public void onCompleted() {
                System.out.println("0000000======onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("0000000======onError==="+e.toString());
            }

            @Override
            public void onNext(RxBean bean) {
                System.out.println("0000000======onNext==="+bean.getResults().size());
            }
        };

        HttpHelper.getInstance().loadData(subscriber);

        //取消请求,Subscriber一旦调用了unsubscribe方法之后，就没有用了,每次发送请求都要创建新的Subscriber对象
        //subscriber.unsubscribe();

    }

}


