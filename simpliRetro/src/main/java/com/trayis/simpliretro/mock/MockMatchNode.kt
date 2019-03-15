package com.trayis.simpliretro.mock

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mudesai on 9/18/16.
 */
data class MockMatchNode constructor(var url: String? = null,
                                     var json: String? = null,
                                     var method: String? = null) : Parcelable {

    constructor(`in`: Parcel) : this(`in`.readString(), `in`.readString(), `in`.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(json)
        parcel.writeString(method)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<MockMatchNode> {
        override fun createFromParcel(parcel: Parcel) = MockMatchNode(parcel)

        override fun newArray(size: Int): Array<MockMatchNode?> {
            return arrayOfNulls(size)
        }
    }

}
