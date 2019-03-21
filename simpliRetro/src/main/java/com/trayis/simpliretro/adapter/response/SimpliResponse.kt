package com.trayis.simpliretro.adapter.response

import android.os.Parcelable

/**
 * This file is created by,
 * @author mukundrd
 * created on 21/03/19
 */

abstract class SimpliResponse<P : Any>(val code: Int, var data: P? = null, var errorMessage: String? = null, var error: Throwable? = null) {

    fun isSuccessful() = (code in 200..299)

}