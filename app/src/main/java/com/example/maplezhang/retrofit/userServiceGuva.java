package com.example.maplezhang.retrofit;


import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by maplezhang on 16/12/20.
 */

public interface userServiceGuva {
    @GET("repos/{owner}/{repo}/contributors")
    ListenableFuture<List<Contributor>> contributorsByAddConverterGetCall (@Path("owner") String owner, @Path("repo") String repo);
}
