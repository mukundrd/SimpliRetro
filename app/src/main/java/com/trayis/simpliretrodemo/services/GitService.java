package com.trayis.simpliretrodemo.services;

import androidx.annotation.WorkerThread;

import com.trayis.simpliretrodemo.model.Repo;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Mukund Desai on 03/08/17.
 */
public interface GitService {

    @WorkerThread
    @GET("users/{user}/repos")
    Single<Repo[]> getRepos(@Path("user") String user);

}
