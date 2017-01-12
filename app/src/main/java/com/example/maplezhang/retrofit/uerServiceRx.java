package com.example.maplezhang.retrofit;


import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by maplezhang on 17/1/3.
 */

public interface uerServiceRx {
    @GET("repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> contributorsByAddConverterGetCall (@Path("owner") String owner, @Path("repo") String repo);
}
