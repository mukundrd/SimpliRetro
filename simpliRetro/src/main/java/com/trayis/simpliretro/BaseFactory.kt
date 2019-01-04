package com.trayis.simpliretro

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.trayis.simpliretro.mock.MockInterceptor
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.ParameterizedType
import java.net.ConnectException
import java.util.*

/**
 * Created by Mukund Desai on 03/08/17.
 */
open class BaseFactory<S> protected constructor(private val baseUrl: String) {

    private lateinit var retrofit: Retrofit

    private lateinit var okHttpClient: OkHttpClient

    private lateinit var context: Context

    private lateinit var gson: Gson

    protected var service: S? = null

    private val interceptors = HashSet<Interceptor>()

    /**
     * Gson factory for marshalling & unmarshalling data.
     *
     * @return
     */
    private val converterFactory: GsonConverterFactory
        get() {
            if (!::gson.isInitialized) {
                gson = GsonBuilder()
                        .setDateFormat(dateFormat)
                        .setPrettyPrinting()
                        .setLenient()
                        .create()
            }

            return GsonConverterFactory.create(gson)
        }

    protected val dateFormat: String
        get() = "dd-MM-yyyy 'T' HH:mm:ss Z"

    private val isConnectionAvailable: Boolean
        get() {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    /**
     * Initialize retrofit service by invoking this method.
     */
    fun init(context: Context) {
        this.context = context.applicationContext
        val retrofit = getRetrofit(context, baseUrl)
        val paramType = javaClass.genericSuperclass as ParameterizedType
        val sClass = paramType.actualTypeArguments[0] as Class<S>
        service = retrofit.create(sClass)
    }

    /**
     * Create Retrofit object for service operation.
     *
     * @param context
     * @param baseUrl
     * @return
     */
    @Synchronized
    private fun getRetrofit(context: Context, baseUrl: String): Retrofit {
        if (!::retrofit.isInitialized) {
            val client = getClient(context)

            val converterFactory = converterFactory

            retrofit = Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(converterFactory)
                    .baseUrl(baseUrl)
                    .build()

        }

        return retrofit
    }

    /**
     * Initializing OKHttp Retrofit for handling requests.
     *
     * @param context this c
     * @return
     */
    private fun getClient(context: Context): OkHttpClient {
        if (!::okHttpClient.isInitialized) {
            val builder = OkHttpClient.Builder()

            for (interceptor in interceptors) {
                builder.addInterceptor(interceptor)
            }

            if (MockInterceptor.isConfigured(context)) {
                val interceptor = MockInterceptor.getInstance(context)
                builder.addInterceptor(interceptor)
            }
            okHttpClient = builder.build()
        }

        return okHttpClient
    }

    /**
     * Adding custom interceptor for the rest client.
     */
    fun addInterceptor(interceptor: Interceptor) {
        interceptors.add(interceptor)
    }

    protected fun <T> prepareObservable(observable: Observable<T>): Observable<T> {
        return if (!isConnectionAvailable) {
            Observable.error(ConnectException("Connection Not Available"))
        } else ReactiveUtil.prepareObservable(observable)
    }

    protected fun <T> prepareSingle(single: Single<T>): Single<T> {
        return if (!isConnectionAvailable) {
            Single.error(ConnectException("Connection Not Available"))
        } else ReactiveUtil.prepareSingle(single)
    }

    protected fun prepareCompletable(completable: Completable): Completable {
        return if (!isConnectionAvailable) {
            Completable.error(ConnectException("Connection Not Available"))
        } else ReactiveUtil.prepareCompletable(completable)
    }

    protected fun <T> prepareFlowable(flowable: Flowable<T>): Flowable<T> {
        return if (!isConnectionAvailable) {
            Flowable.error(ConnectException("Connection Not Available"))
        } else ReactiveUtil.prepareFlowable(flowable)
    }


}
