package com.lxy.retrofit.rx.RxOperation;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lxy.retrofit.R;
import com.lxy.retrofit.databinding.ActivityOperationTestBinding;

import rx.Observable;
import rx.Observer;

public class OperationTestActivity extends AppCompatActivity {

    ActivityOperationTestBinding mBinding;

    private String[] strs = {"hello","world","hehe"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_operation_test);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_operation_test);
        mBinding.setPresenter(new Presenter());

    }


    public class Presenter {

        public void clickFrom(View view) {
            Observable<String> from = Observable.from(strs);
            Observer observer = new Observer<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    System.out.println("33333333======"+s);
                }


            };
            from.subscribe(observer);

        }

        public void clickJust(View view) {

        }

        public void clickCreate(View view) {

        }

        public void clickInterval(View view) {

        }

        public void clickTimer(View view) {

        }

        public void clickRange(View view) {

        }

        public void clickRmpty(View view) {

        }

        public void clickNever(View view) {

        }

        public void clickDefer(View view) {

        }

        public void clickRepeat(View view) {

        }

        public void clickRepeatWhen(View view) {

        }

        public void clickRetry(View view) {

        }

        public void clickRetryWhen(View view) {

        }

        public void clickMap(View view) {

        }

        public void clickFlatMap(View view) {

        }

        public void clickZip(View view) {

        }
    }
}
