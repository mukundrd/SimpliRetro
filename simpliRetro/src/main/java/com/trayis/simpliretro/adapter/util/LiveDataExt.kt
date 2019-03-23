package com.trayis.simpliretro.adapter.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * This file is created by,
 * @author mukundrd
 * created on 23/03/19
 */

fun <T1, T2, R> LiveData<Any>.zip(src1: LiveData<T1>, src2: LiveData<T2>, zipper: (T1, T2) -> R): LiveData<R> {

    return MediatorLiveData<R>().apply {
        var src1Version = 0
        var src2Version = 0

        var lastSrc1: T1? = null
        var lastSrc2: T2? = null

        fun updateValueIfNeeded() {
            if (src1Version > 0 && src2Version > 0 &&
                    lastSrc1 != null && lastSrc2 != null) {
                value = zipper(lastSrc1!!, lastSrc2!!)
                src1Version = 0
                src2Version = 0
            }
        }

        addSource(src1) {
            lastSrc1 = it
            src1Version++
            updateValueIfNeeded()
        }

        addSource(src2) {
            lastSrc2 = it
            src2Version++
            updateValueIfNeeded()
        }
    }
}

fun <T> LiveData<T>.observeOnAny(src1: LiveData<T>, src2: LiveData<T>): LiveData<T> {
    return object : MediatorLiveData<T>() {

        override fun onActive() {
            super.onActive()
            this.addSource(src1) {
                this.postValue(it)
            }
            this.addSource(src2) {
                this.postValue(it)
            }
        }

    }
}