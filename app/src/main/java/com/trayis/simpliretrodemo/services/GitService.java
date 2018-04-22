package com.trayis.simpliretrodemo.services;

import android.support.annotation.WorkerThread;

import com.trayis.simpliretrodemo.model.Repo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Single;

/**
 * Created by Mukund Desai on 03/08/17.
 */
public interface GitService {

    @WorkerThread
    @GET("users/{user}/repos")
    Single<Repo[]> getRepos(@Path("user") String user);

}
