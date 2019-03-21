package com.trayis.simpliretro.adapter.response

import android.os.Parcelable

/**
 * This file is created by,
 * @author mukundrd
 * created on 21/03/19
 */
class SuccessResponse<P : Any>(code: Int, data: P? = null) : SimpliResponse<P>(code = code, data = data)