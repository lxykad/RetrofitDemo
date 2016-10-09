package com.lxy.retrofit.img;

import android.content.res.AssetFileDescriptor;
import android.databinding.DataBindingUtil;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Toast;

import com.lxy.retrofit.R;
import com.lxy.retrofit.databinding.ActivityImgBinding;

import java.io.IOException;

public class ImgActivity extends AppCompatActivity {

    private ActivityImgBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_img);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_img);
        mBinding.setPresenter(new Presenter());


    }



    public class Presenter {

        public void onClick(View view) {
            Toast.makeText(ImgActivity.this, "test", Toast.LENGTH_SHORT).show();
        }
    }

}
