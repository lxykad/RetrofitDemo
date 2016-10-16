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
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class OperationTestActivity extends AppCompatActivity {

    ActivityOperationTestBinding mBinding;
    Observable mObservable;

    private String[] strs = {"hello", "world", "hehe"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_operation_test);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_operation_test);
        mBinding.setPresenter(new Presenter());

        String mainName = Thread.currentThread().getName();
        System.out.println("5555555===mainName===" + mainName);

    }


    public class Presenter {

        //from操作符
        public void clickFrom(View view) {

            Observable.from(strs)
                    .subscribeOn(Schedulers.io())//指定 subscribe() 发生在 IO 线程
                    .observeOn(AndroidSchedulers.mainThread())//指定observe 回调在主线程
                    .map(new Func1<String, String>() {//map转换，把string转换为string并返回

                        @Override
                        public String call(String s) {
                            return s + ": map";
                        }
                    })
                    .subscribe(new Subscriber<String>() {
                        @Override
                        public void onStart() {
                            //super.onStart();
                            String nameStart = Thread.currentThread().getName();
                            System.out.println("5555555===nameStart===" + nameStart);
                        }

                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(String s) {
                            String nameNext = Thread.currentThread().getName();
                            System.out.println("5555555===nameNext===" + nameNext);
                            System.out.println("5555555======" + s);
                        }
                    });

        }

        public void clickJust(View view) {

        }

        public void clickCreate(View view) {

            Action1<String> action1 = new Action1<String>() {
                @Override
                public void call(String s) {
                    System.out.println("5555555====action1==" + s);
                }
            };
            Observable.from(strs).subscribe(action1);

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


    @Override
    protected void onStop() {
        super.onStop();

    }
}
