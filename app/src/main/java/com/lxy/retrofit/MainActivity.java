package com.lxy.retrofit;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lxy.retrofit.binding.BindingActivity;
import com.lxy.retrofit.databinding.ActivityMainBinding;
import com.lxy.retrofit.rx.RxActivity;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    private String mHomeUrl = "http://140.207.75.158/article/list?page=0";
    private ActivityMainBinding mBinding = null;
    private TestBean mBean= new TestBean("name");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setTestbean(mBean);
        mBinding.setPresenter(new Presenter());
        //mBinding.setVariable(BR.presenter,new MainActivity().new Presenter());
        //mBinding.setVariable(com.lxy.retrofit.BR.presenter, new Presenter());
       // mBinding.setVariable(com.lxy.retrofit.BR.testbean,mBean);


       // loadHomeData(2);

    }

    public interface HomeInterface {
        @GET("article/list")
        Call<String> loadData(@Query("page") int page);
    }

    public void loadHomeData(int page) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpHelper.BASE_URL)
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .build();

        HomeInterface homeInterface = retrofit.create(HomeInterface.class);
        Call<String> homeBeanCall = homeInterface.loadData(page);

        homeBeanCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Gson gson = new Gson();
//                HomeBean homeBean = gson.fromJson(response.body().toString(), HomeBean.class);
//                int size = homeBean.results.size();
//                System.out.println("111111111====desc==="+homeBean.results.get(0).desc);
                System.out.println("111111111=======success=====" + response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("111111111=======failure");
            }
        });

    }


    /**
     * addQueryParameter就是添加请求参数的具体代码，这种方式比较适用于所有的请求都需要添加的参数，
     * 一般现在的网络请求都会添加token作为用户标识，那么这种方式就比较适合。
     * 创建完成自定义的Interceptor后，还需要在Retrofit创建client处完成添加 addInterceptor(new CustomInterceptor())
     */
    //第二种添加参数的方式
    public class MyInterceptor implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {

            Request request = chain.request();

            HttpUrl httpUrl = request.url().newBuilder()
                    .addQueryParameter("page", "value")
                    .build();
            request = request.newBuilder().url(httpUrl).build();


            return chain.proceed(request);
        }
    }

    public class Presenter {

        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, RxActivity.class);
            startActivity(intent);
        }

        public void onClickBinding(View view) {
            Intent intent = new Intent(MainActivity.this, BindingActivity.class);
            startActivity(intent);
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //mBean.bean_name = s.toString();
            mBean.setName(s.toString());
            System.out.println("0000000======"+mBean.bean_name);

        }

    }

}
