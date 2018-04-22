package com.trayis.simpliretrodemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mukund on 05-03-2017.
 */

class Owner implements Parcelable {

    public static final Creator<Owner> CREATOR = new Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel in) {
            return new Owner(in);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };

    protected Owner(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
