package com.trayis.simpliretrodemo.services;

import com.trayis.simpliretrodemo.model.Repo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Mukund Desai on 03/08/17.
 */
public interface GitService {

    @GET("users/{user}/repos")
    Call<Repo[]> getRepos(@Path("user") String user);

}
