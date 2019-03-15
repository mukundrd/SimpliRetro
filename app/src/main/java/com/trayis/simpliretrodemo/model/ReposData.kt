package com.trayis.simpliretrodemo.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mudesai on 9/18/16.
 */
data class ReposData protected constructor(val repos: Array<Repo>? = null) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.createTypedArray(Repo)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedArray(repos, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ReposData

        if (repos != null) {
            if (other.repos == null) return false
            if (!repos.contentEquals(other.repos)) return false
        } else if (other.repos != null) return false

        return true
    }

    override fun hashCode(): Int {
        return repos?.contentHashCode() ?: 0
    }

    companion object CREATOR : Parcelable.Creator<ReposData> {
        override fun createFromParcel(parcel: Parcel): ReposData {
            return ReposData(parcel)
        }

        override fun newArray(size: Int): Array<ReposData?> {
            return arrayOfNulls(size)
        }
    }

}
