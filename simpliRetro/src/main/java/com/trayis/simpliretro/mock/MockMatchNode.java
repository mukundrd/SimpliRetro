package com.trayis.simpliretro.mock;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mudesai on 9/18/16.
 */
public class MockMatchNode implements Parcelable {

    String url;

    String json;

    private MockMatchNode(Parcel in) {
        url = in.readString();
        json = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(json);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MockMatchNode> CREATOR = new Creator<MockMatchNode>() {
        @Override
        public MockMatchNode createFromParcel(Parcel in) {
            return new MockMatchNode(in);
        }

        @Override
        public MockMatchNode[] newArray(int size) {
            return new MockMatchNode[size];
        }
    };
}
