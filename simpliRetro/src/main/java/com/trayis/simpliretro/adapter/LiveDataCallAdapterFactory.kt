package com.trayis.simpliretro.adapter

import android.os.Parcelable
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type
import androidx.lifecycle.LiveData
import com.trayis.simpliretro.adapter.response.SimpliResponse
import java.lang.reflect.ParameterizedType


/**
 * This file is created by,
 * @author mukundrd
 * created on 21/03/19
 */
class LiveDataCallAdapterFactory<P : Parcelable> : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (CallAdapter.Factory.getRawType(returnType) != LiveData::class.java) {
            return null
        }
        val observableType = CallAdapter.Factory.getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = CallAdapter.Factory.getRawType(observableType)
        if (rawObservableType != SimpliResponse::class.java) {
            throw IllegalArgumentException("type must be a resource")
        }
        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }
        val bodyType = CallAdapter.Factory.getParameterUpperBound(0, observableType)
        return LiveDataAdapter<P>(bodyType)
    }
}