package com.catalyst.travller.app.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mudesai on 9/18/16.
 */
public class ReposData implements Parcelable {

    public Repo[] repos;

    protected ReposData(Parcel in) {
    }

    public static final Creator<ReposData> CREATOR = new Creator<ReposData>() {
        @Override
        public ReposData createFromParcel(Parcel in) {
            return new ReposData(in);
        }

        @Override
        public ReposData[] newArray(int size) {
            return new ReposData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
