package com.catalyst.travller.app.services.empprofile.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vhebbar on 12/5/16.
 */

public class EmployeeProfile implements Parcelable{

    public String employeeName;
    public String dpUrl;
    public String pickupPoint;
    public String phoneNumber;
    public String bloodGroup;
    public String pickUpTime;

    protected EmployeeProfile(Parcel in) {
        employeeName = in.readString();
        dpUrl = in.readString();
        pickupPoint = in.readString();
        phoneNumber = in.readString();
        bloodGroup = in.readString();
        pickUpTime = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(employeeName);
        dest.writeString(dpUrl);
        dest.writeString(pickupPoint);
        dest.writeString(phoneNumber);
        dest.writeString(bloodGroup);
        dest.writeString(pickUpTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EmployeeProfile> CREATOR = new Creator<EmployeeProfile>() {
        @Override
        public EmployeeProfile createFromParcel(Parcel in) {
            return new EmployeeProfile(in);
        }

        @Override
        public EmployeeProfile[] newArray(int size) {
            return new EmployeeProfile[size];
        }
    };

    @Override
    public String toString() {
        return "EmployeeProfile{" +
                "employeeName='" + employeeName + '\'' +
                ", dpUrl='" + dpUrl + '\'' +
                ", pickupPoint='" + pickupPoint + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", pickUpTime='" + pickUpTime + '\'' +
                '}';
    }
}
