package com.catalyst.travller.app.services.git;

import android.content.Context;
import android.support.annotation.NonNull;

import com.catalyst.travller.app.model.Repo;
import com.catalyst.travller.app.services.BaseFactory;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
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
