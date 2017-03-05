package com.catalyst.travller.app.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mudesai on 9/20/16.
 */
public class Data implements Parcelable {

    public String id;

    public String imagePath;

    public String name;

    protected Data(Parcel in) {
        id = in.readString();
        imagePath = in.readString();
        name = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", imagePath = " + imagePath + ", name = " + name + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(imagePath);
        dest.writeString(name);
    }
}
