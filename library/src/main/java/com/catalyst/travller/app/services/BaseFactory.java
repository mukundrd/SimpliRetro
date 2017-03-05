package com.catalyst.travller.app.services;

import android.content.Context;
import android.support.annotation.NonNull;

import com.catalyst.travller.app.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trayis.mock.MockInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mudesai on 9/20/16.
 */
public class BaseFactory {

    private static OkHttpClient okHttpClient;

    @NonNull
    protected static Retrofit getRetrofit(Context context, String baseUrl) {
        OkHttpClient client = getClient(context);

        Converter.Factory converterFactory = getConverterFactory();

        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(converterFactory)
                .baseUrl(baseUrl)
                .build();
    }

    @NonNull
    private static GsonConverterFactory getConverterFactory() {
        Gson gson = new GsonBuilder()
                .setDateFormat("dd-MM-yyyy 'T' HH:mm:ss Z")
                .setPrettyPrinting()
                .create();

        return GsonConverterFactory.create(gson);
    }

    private static synchronized OkHttpClient getClient(Context context) {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (MockInterceptor.isConfigured(context)) {
                MockInterceptor interceptor = MockInterceptor.getInstance(context);
                builder.addInterceptor(interceptor);
            }
            okHttpClient = builder.build();
        }

        return okHttpClient;
    }
}
