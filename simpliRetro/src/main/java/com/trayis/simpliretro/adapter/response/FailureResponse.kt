package com.trayis.simpliretro.adapter.response

import android.os.Parcelable

/**
 * This file is created by,
 * @author mukundrd
 * created on 21/03/19
 */
class FailureResponse<P : Parcelable>(code: Int, errorMessage: String? = "", error: Throwable? = null) : SimpliResponse<P>(code = code, errorMessage = errorMessage, error = error)