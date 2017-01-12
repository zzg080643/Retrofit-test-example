package com.example.maplezhang.retrofit;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by maplezhang on 16/12/20.
 */

public interface uerService {
    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributorsByAddConverterGetCall(@Path("owner") String owner, @Path("repo") String repo);
}
