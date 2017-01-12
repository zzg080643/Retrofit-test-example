package com.example.maplezhang.retrofit.RxPackage;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import rx.Scheduler;
import rx.Observable;


/**
 * Created by maplezhang on 17/1/3.
 */

public class RxCallAdapter<R> implements CallAdapter<R> {



    private final Type responseType;
    private final Scheduler scheduler;


   public RxCallAdapter(Type responseType, Scheduler scheduler) {
        this.responseType = responseType;
        this.scheduler = scheduler;
    }
    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public <R1> R adapt(Call<R1> call) {
        Observable<?> observable = Observable.create(new CallOnSubscribe<>(call));
        if(scheduler != null ){
            observable = observable.subscribeOn(scheduler);
        }
        return (R)observable;
    }
}
