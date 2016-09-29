package com.lxy.retrofit.binding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.lxy.retrofit.R;
import com.lxy.retrofit.databinding.ActivityBindingBinding;

public class BindingActivity extends AppCompatActivity {

    private ActivityBindingBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_binding);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_binding);

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BindAdapter adapter = new BindAdapter();
        mBinding.recyclerView.setAdapter(adapter);
    }
}
