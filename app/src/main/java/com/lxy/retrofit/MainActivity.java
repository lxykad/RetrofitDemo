package com.lxy.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadHomeData("10","3");
    }

    public interface HomeInterface {
        @GET("10/2")
        Call<String> loadData(@Query("month") String month,@Query("date") String date);
    }
    
    public void loadHomeData(String month,String date) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpHelper.GANK_BASE_URL)
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        HomeInterface homeInterface = retrofit.create(HomeInterface.class);
        Call<String> homeBeanCall = homeInterface.loadData(month,date);

        homeBeanCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Gson gson = new Gson();
                HomeBean homeBean = gson.fromJson(response.body().toString(), HomeBean.class);
                int size = homeBean.results.size();
                System.out.println("111111111====desc==="+homeBean.results.get(0).desc);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("111111111=======failure");
            }
        });

    }
}
