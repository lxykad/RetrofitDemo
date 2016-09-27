package com.lxy.retrofit.rx;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lxy.retrofit.BR;
import com.lxy.retrofit.R;

public class RxActivity extends AppCompatActivity {

    private ViewDataBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_rx);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_rx);
        mBinding.setVariable(BR.presenter,new Presenter());





    }




    public class Presenter{

        public void onClick(View view){
            //Observable释放Hello，World字符串，接着停止执行：
            
        }
    }
}
