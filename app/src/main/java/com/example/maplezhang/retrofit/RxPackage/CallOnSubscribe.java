package com.example.maplezhang.retrofit.RxPackage;

import com.example.maplezhang.retrofit.Contributor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by maplezhang on 17/1/3.
 */

final class CallOnSubscribe<T> implements Observable.OnSubscribe<T> {

    @Override
    public void call(final Subscriber<? super T> subscriber) {
        originalCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                subscriber.onNext(response.body());
                subscriber.onCompleted();
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                subscriber.onCompleted();
            }
        });
        subscriber.onCompleted();
    }

    private final Call<T> originalCall;

    CallOnSubscribe(Call<T> originalCall) {
        this.originalCall = originalCall;
    }
}
