package com.example.maplezhang.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.example.maplezhang.retrofit.RxPackage.ForwardingSubscriber;
import com.example.maplezhang.retrofit.RxPackage.RxCallAdapterFactory;
import com.example.maplezhang.retrofit.gson.GsonConverterFactory;
import com.google.common.util.concurrent.AbstractScheduledService;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.Future;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //DefaultMehtod();
        GuavaMethod();
        //RxMethod();


    }
    public void  RxMethod() {
        Subscriber<List<Contributor>> delegate = new Subscriber<List<Contributor>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Contributor> contributors) {
                for(Contributor contributor : contributors) {
                    Log.d("Guva_login", contributor.getLogin());
                    Log.d("Guva_contributions", contributor.getContributions() + "");
                }
            }
        };
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxCallAdapterFactory.create())
                .build();
        uerServiceRx reto = retrofit.create(uerServiceRx.class);
        reto.contributorsByAddConverterGetCall("square", "retrofit").subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io()).subscribe(new ForwardingSubscriber<List<Contributor>>(delegate));

    }
    public void GuavaMethod() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(GuavaCallAdapterFactory.create())
                .build();
        userServiceGuva reto = retrofit.create(userServiceGuva.class);
        final ListenableFuture<List<Contributor>> future = reto.contributorsByAddConverterGetCall("square", "retrofit");
        Futures.addCallback(future, new FutureCallback<List<Contributor>>() {
            @Override
            public void onSuccess(List<Contributor> result) {
                for(Contributor contributor : result) {
                    Log.d("Guva_login", contributor.getLogin());
                    Log.d("Guva_contributions", contributor.getContributions() + "");
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


      /*  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Contributor> result = future.get();
                    for(Contributor contributor : result) {
                        Log.d("Guva_login", contributor.getLogin());
                        Log.d("Guva_contributions", contributor.getContributions() + "");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();*/





    }

    public void DefaultMehtod() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        uerService repo = retrofit.create(uerService.class);
        Call<List<Contributor>> call = repo.contributorsByAddConverterGetCall("square", "retrofit");
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                List<Contributor> contributorList= response.body();
                for(Contributor contributor : contributorList) {
                    Log.d("login", contributor.getLogin());
                    Log.d("contributions", contributor.getContributions() + "");
                }
            }
            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {

            }
        });
    }
}


