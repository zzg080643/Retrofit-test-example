package com.example.maplezhang.retrofit.RxPackage;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.Single;

/**
 * Created by maplezhang on 17/1/3.
 */

public class RxCallAdapterFactory extends CallAdapter.Factory {

    private final Scheduler  scheduler;

    public RxCallAdapterFactory(Scheduler scheduler) {
            this.scheduler = scheduler;
    }
    public static RxCallAdapterFactory create() {
        return new RxCallAdapterFactory(null);
    }


    @Override
    public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Class<?> rawType = getRawType(returnType);
        boolean isSingle = rawType == Single.class;
        boolean isCompletable = "rx.Completable".equals(rawType.getCanonicalName());
        if (rawType != Observable.class && !isSingle && !isCompletable) {
            return null;
        }

        if (isCompletable) {
            return new RxCallAdapter<Observable>(Void.class, scheduler);
        }

        Type responseType;
        if (!(returnType instanceof ParameterizedType)) {
            String name = isSingle ? "Single" : "Observable";
            throw new IllegalStateException(name + " return type must be parameterized"
                    + " as " + name + "<Foo> or " + name + "<? extends Foo>");
        }
        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        responseType = observableType;
        return new RxCallAdapter<Observable>(responseType, scheduler);
    }




}
