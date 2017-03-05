package com.trayis.simpliretrodemo.services;

import com.trayis.simpliretrodemo.model.Repo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by mudesai on 9/17/16.
 */
public interface GitService {

    @GET("users/{user}/repos")
    Observable<Repo[]> getRepos(@Path("user") String user);

}
