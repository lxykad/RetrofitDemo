package com.lxy.retrofit.rx;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lxy.retrofit.BR;
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


        loadData();
    }

    public interface RxService {
        @GET("10/1")
        Observable<RxBean> loadData();

    }


    public class Presenter {

        public void onClick(View view) {

        }
    }

    public void loadData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RxService rxService = retrofit.create(RxService.class);
        rxService.loadData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RxBean>() {
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
                });

    }

}


