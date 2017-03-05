package com.catalyst.travller.app.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mudesai on 9/20/16.
 */
public class MakesData implements Parcelable {
    public String count;

    public String isBike;

    public Data[] data;

    protected MakesData(Parcel in) {
        count = in.readString();
        isBike = in.readString();
        data = in.createTypedArray(Data.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(count);
        dest.writeString(isBike);
        dest.writeTypedArray(data, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MakesData> CREATOR = new Creator<MakesData>() {
        @Override
        public MakesData createFromParcel(Parcel in) {
            return new MakesData(in);
        }

        @Override
        public MakesData[] newArray(int size) {
            return new MakesData[size];
        }
    };

    @Override
    public String toString() {
        return "ClassPojo [count = " + count + ", isBike = " + isBike + ", data = " + data + "]";
    }
}
