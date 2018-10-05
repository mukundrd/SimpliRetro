package com.trayis.simpliretro;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trayis.simpliretro.mock.MockInterceptor;

import java.lang.reflect.ParameterizedType;
import java.net.ConnectException;
import java.util.HashSet;
import java.util.Set;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mukund Desai on 03/08/17.
 */
public class BaseFactory<S> {

    private static OkHttpClient okHttpClient;

    private Retrofit retrofit;

    private final String baseUrl;

    private Set<Interceptor> interceptors = new HashSet<>();

    private S service;

    private Context context;

    protected BaseFactory(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Initialize retrofit service by invoking this method.
     */
    public void init(Context context) {
        this.context = context.getApplicationContext();
        Retrofit retrofit = getRetrofit(context, baseUrl);
        ParameterizedType paramType = (ParameterizedType) getClass().getGenericSuperclass();
        @SuppressWarnings("unchecked")
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
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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
     * @param context this c
     * @return
     */
    private OkHttpClient getClient(Context context) {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
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
     */
    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    protected S getService() {
        return service;
    }

    protected <T> Observable<T> prepareObservable(Observable<T> observable) {
        if (!isConnectionAvailable()) {
            return Observable.error(new ConnectException("Connection Not Available"));
        }
        return ReactiveUtil.prepareObservable(observable);
    }

    protected <T> Single<T> prepareSingle(Single<T> single) {
        if (!isConnectionAvailable()) {
            return Single.error(new ConnectException("Connection Not Available"));
        }
        return ReactiveUtil.prepareSingle(single);
    }

    protected <T> Completable prepareCompletable(Completable completable) {
        if (!isConnectionAvailable()) {
            return Completable.error(new ConnectException("Connection Not Available"));
        }
        return ReactiveUtil.prepareCompletable(completable);
    }

    protected <T> Flowable<T> prepareFlowable(Flowable<T> flowable) {
        if (!isConnectionAvailable()) {
            return Flowable.error(new ConnectException("Connection Not Available"));
        }
        return ReactiveUtil.prepareFlowable(flowable);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean isConnectionAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) return false;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
