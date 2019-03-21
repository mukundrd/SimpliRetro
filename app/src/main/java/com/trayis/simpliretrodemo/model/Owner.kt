package com.trayis.simpliretrodemo.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Mukund on 05-03-2017.
 */
class Owner : Parcelable {

    override fun writeToParcel(parcel: Parcel, flags: Int) {
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Owner> {
        override fun createFromParcel(parcel: Parcel): Owner {
            return Owner()
        }

        override fun newArray(size: Int): Array<Owner?> {
            return arrayOfNulls(size)
        }
    }

}
