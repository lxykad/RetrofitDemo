package com.lxy.retrofit;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;

import com.lxy.retrofit.binding.BindingActivity;
import com.lxy.retrofit.databinding.ActivityMainBinding;
import com.lxy.retrofit.img.ImgActivity;
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
    private ActivityMainBinding mBinding;
    private TestBean mBean = new TestBean("name");

    private MediaPlayer mMediaPlayer;

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
        mMediaPlayer = new MediaPlayer();

        initSurfaceView();

    }

    public void initSurfaceView() {
        //
        mBinding.surfaceView.getHolder().setKeepScreenOn(true);

        //添加回调
        mBinding.surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {

            //在surface创建时触发，一般在这里调用画图的线程。
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                play();
            }

            //在surface的大小发生改变时激发
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            //销毁时触发，一般在这里将画图的线程停止、释放。
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                //mMediaPlayer.stop();
               // mMediaPlayer.release();
            }
        });
    }

    private void play() {

        try {
            //设置音频流的类型
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            //存储在Assets下的媒体文件
            AssetFileDescriptor fd = getAssets().openFd("test.mp4");
            //设置播放资源
            mMediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            //设置是否循环播放
            mMediaPlayer.setLooping(true);
            //设置显示方式(使用SurfaceView)
            mMediaPlayer.setDisplay(mBinding.surfaceView.getHolder());
            //准备播放异步音频
            mMediaPlayer.prepareAsync();
            //当MediaPlayer调用prepare()方法时触发该监听器。
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //开始或恢复播放。
                    mp.start();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

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

        public void onClickImg(View view) {
            Intent intent = new Intent(MainActivity.this, ImgActivity.class);
            startActivity(intent);
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //mBean.bean_name = s.toString();
            mBean.setName(s.toString());
            System.out.println("0000000======" + mBean.bean_name);

        }

    }

}
