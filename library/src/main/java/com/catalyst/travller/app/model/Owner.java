package com.catalyst.travller.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mudesai on 9/18/16.
 */
public class Owner implements Parcelable {

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("gravatar_id")
    private String gravatarId;

    @SerializedName("starred_url")
    private String starredUrl;

    private String type;

    private String url;

    private String id;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("following_url")
    private String followingUrl;

    private String login;

    @SerializedName("repos_url")
    private String reposUrl;


    protected Owner(Parcel in) {
        avatarUrl = in.readString();
        gravatarId = in.readString();
        starredUrl = in.readString();
        type = in.readString();
        url = in.readString();
        id = in.readString();
        htmlUrl = in.readString();
        followingUrl = in.readString();
        login = in.readString();
        reposUrl = in.readString();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(avatarUrl);
        dest.writeString(gravatarId);
        dest.writeString(starredUrl);
        dest.writeString(type);
        dest.writeString(url);
        dest.writeString(id);
        dest.writeString(htmlUrl);
        dest.writeString(followingUrl);
        dest.writeString(login);
        dest.writeString(reposUrl);
    }

    @Override
    public String toString() {
        return "Owner {" +
                "\n\tavatarUrl='" + avatarUrl + '\'' +
                "\n\tgravatarId='" + gravatarId + '\'' +
                "\n\tstarredUrl='" + starredUrl + '\'' +
                "\n\ttype='" + type + '\'' +
                "\n\turl='" + url + '\'' +
                "\n\tid='" + id + '\'' +
                "\n\thtmlUrl='" + htmlUrl + '\'' +
                "\n\tfollowingUrl='" + followingUrl + '\'' +
                "\n\tlogin='" + login + '\'' +
                "\n\treposUrl='" + reposUrl + '\'' +
                "\n}";
    }
}
