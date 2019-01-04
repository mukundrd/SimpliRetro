package com.trayis.simpliretro.mock

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mudesai on 9/18/16.
 */
data class MockMatchNode protected constructor(val url: String? = null, val json: String? = null) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(json)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MockMatchNode> {
        override fun createFromParcel(parcel: Parcel): MockMatchNode {
            return MockMatchNode(parcel)
        }

        override fun newArray(size: Int): Array<MockMatchNode?> {
            return arrayOfNulls(size)
        }
    }

}
