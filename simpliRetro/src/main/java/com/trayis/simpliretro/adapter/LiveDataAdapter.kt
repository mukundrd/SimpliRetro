package com.trayis.simpliretro.adapter

import android.os.Parcelable
import androidx.lifecycle.LiveData
import com.trayis.simpliretro.adapter.response.FailureResponse
import com.trayis.simpliretro.adapter.response.SimpliResponse
import com.trayis.simpliretro.adapter.response.SuccessResponse
import retrofit2.*
import java.io.IOException
import java.lang.reflect.Type

/**
 * This file is created by,
 * @author mukundrd
 * created on 21/03/19
 */
class LiveDataAdapter<P : Parcelable>(val type: Type) : CallAdapter<P, LiveData<SimpliResponse<P>>> {

    override fun responseType() = type

    override fun adapt(call: Call<P>): LiveData<SimpliResponse<P>> {
        return object : LiveData<SimpliResponse<P>>() {
            override fun onActive() {
                super.onActive()
                call.enqueue(object : Callback<P> {
                    override fun onFailure(call: Call<P>, t: Throwable) {
                        postValue(FailureResponse(if (t is HttpException) {
                            t.code()
                        } else {
                            500
                        }, t.message, t))
                    }

                    override fun onResponse(call: Call<P>, response: Response<P>) {
                        postValue(if (response.isSuccessful) {
                            SuccessResponse(response.code(), response.body())
                        } else {
                            var message: String? = null
                            if (response.errorBody() != null) {
                                try {
                                    message = response.errorBody()?.string()
                                } catch (ignored: IOException) {
                                }

                            }
                            if (message == null || message.trim { it <= ' ' }.isEmpty()) {
                                message = response.message()
                            }
                            FailureResponse(response.code(), message, null)
                        })
                    }

                })
            }

            override fun onInactive() {
                super.onInactive()
                call.cancel()
            }
        }
    }

}