package com.trayis.simpliretro.mock

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mudesai on 9/20/16.
 */
data class Domain constructor(var nodes: Array<MockMatchNode>? = null,
                              var url: String? = null) : Parcelable {

    constructor(`in`: Parcel) : this(`in`.createTypedArray(MockMatchNode.CREATOR), `in`.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedArray(nodes, flags)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Domain

        return url === other.url
    }

    override fun hashCode(): Int {
        var result = nodes?.contentHashCode() ?: 0
        result = 31 * result + (url?.hashCode() ?: 0)
        return result
    }

    companion object CREATOR : Parcelable.Creator<Domain> {
        override fun createFromParcel(parcel: Parcel): Domain {
            return Domain(parcel)
        }

        override fun newArray(size: Int): Array<Domain?> {
            return arrayOfNulls(size)
        }
    }
}
