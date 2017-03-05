package com.trayis.simpliretro;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trayis.mock.MockInterceptor;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mudesai on 9/20/16.
 */
public class BaseFactory<S> {

    private static OkHttpClient okHttpClient;

    private final String baseUrl;
    private S service;

    public BaseFactory(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void init(Context context) {
        Retrofit retrofit = getRetrofit(context, baseUrl);
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        Class<S> sClass = (Class<S>) paramType.getActualTypeArguments()[0];
        service = retrofit.create(sClass);
    }

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

    public S getService() {
        return service;
    }
}
