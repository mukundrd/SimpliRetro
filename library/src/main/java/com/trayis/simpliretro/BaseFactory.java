package com.trayis.simpliretro;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trayis.mock.MockInterceptor;

import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mukund Desai on 03/08/17.
 */
public class BaseFactory<S> {

    private static OkHttpClient okHttpClient;

    private Retrofit retrofit;

    private final String baseUrl;

    private Set<Interceptor> interceptors = new HashSet();

    private S service;

    protected BaseFactory(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Initialize retrofit service by invoking this method.
     *
     * @param context
     */
    public void init(Context context) {
        Retrofit retrofit = getRetrofit(context, baseUrl);
        ParameterizedType paramType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<S> sClass = (Class<S>) paramType.getActualTypeArguments()[0];
        service = retrofit.create(sClass);
    }

    /**
     * Create Retrofit object for service operation.
     *
     * @param context
     * @param baseUrl
     * @return
     */
    @NonNull
    private synchronized Retrofit getRetrofit(Context context, String baseUrl) {
        if (retrofit == null) {
            OkHttpClient client = getClient(context);

            Converter.Factory converterFactory = getConverterFactory();

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(converterFactory)
                    .baseUrl(baseUrl)
                    .build();
        }

        return retrofit;
    }

    /**
     * Gson factory for marshalling & unmarshalling data.
     *
     * @return
     */
    @NonNull
    private static GsonConverterFactory getConverterFactory() {
        Gson gson = new GsonBuilder()
                .setDateFormat("dd-MM-yyyy 'T' HH:mm:ss Z")
                .setPrettyPrinting()
                .setLenient()
                .create();

        return GsonConverterFactory.create(gson);
    }

    /**
     * Initializing OKHttp Retrofit for handling requests.
     *
     * @param context
     * @return
     */
    private OkHttpClient getClient(Context context) {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            if (interceptors != null) {
                for (Interceptor interceptor : interceptors) {
                    builder.addInterceptor(interceptor);
                }
            }

            if (MockInterceptor.isConfigured(context)) {
                MockInterceptor interceptor = MockInterceptor.getInstance(context);
                builder.addInterceptor(interceptor);
            }
            okHttpClient = builder.build();
        }

        return okHttpClient;
    }

    /**
     * Adding custom interceptor for the rest client.
     *
     * @param interceptor
     */
    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    /**
     * Retrieve service instance out from the process.
     *
     * @return
     */
    public S getService() {
        return service;
    }
}
