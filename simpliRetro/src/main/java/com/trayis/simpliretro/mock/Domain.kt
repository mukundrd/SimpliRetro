package com.trayis.simpliretro.mock

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mudesai on 9/20/16.
 */
data class Domain(val nodes: Array<MockMatchNode>? = null, val url: String? = null) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.createTypedArray(MockMatchNode.CREATOR),
            parcel.readString())


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Domain

        if (nodes != null) {
            if (other.nodes == null) return false
            if (!nodes.contentEquals(other.nodes)) return false
        } else if (other.nodes != null) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nodes?.contentHashCode() ?: 0
        result = 31 * result + (url?.hashCode() ?: 0)
        return result
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedArray(nodes, flags)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
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
