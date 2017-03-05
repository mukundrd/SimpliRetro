package com.catalyst.travller.app.services.gears;

import android.content.Context;
import android.support.annotation.NonNull;

import com.catalyst.travller.app.BuildConfig;
import com.catalyst.travller.app.model.MakesData;
import com.catalyst.travller.app.model.Repo;
import com.catalyst.travller.app.services.BaseFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trayis.mock.MockInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by mudesai on 9/17/16.
 */
public interface GearsService {

    @GET("rest/list_makes")
    Observable<MakesData> getRepos();

}
