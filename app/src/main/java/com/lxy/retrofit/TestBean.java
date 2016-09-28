package com.lxy.retrofit;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayMap;

/**
 * Created by C on 16/9/28.
 */

public class TestBean extends BaseObservable{

    public String bean_name;
    public String bean_age;

    //public ObservableArrayMap<String, String> user = new ObservableArrayMap<>();

    public TestBean(String n){
        bean_name = n;
    }

    @Bindable
    public String getName() {
        return bean_name;
    }
    @Bindable
    public String getAge() {
        return bean_age;
    }

    public void setName(String name) {
        bean_name = name;
        notifyPropertyChanged(com.lxy.retrofit.BR.name);
    }

    public void setAge(String age) {
        this.bean_age = age;
        notifyPropertyChanged(com.lxy.retrofit.BR.age);
    }
}
