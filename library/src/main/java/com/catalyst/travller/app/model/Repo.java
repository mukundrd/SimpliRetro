package com.catalyst.travller.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mudesai on 9/18/16.
 */
public class Repo implements Parcelable {

    @SerializedName("teams_url")
    private String teamsUrl;

    private String description;

    @SerializedName("pushed_at")
    private String pushedAt;

    private String homepage;

    private String url;

    private String size;

    @SerializedName("updated_at")
    private String updatedAt;

    private Owner owner;

    private String language;

    @SerializedName("commits_url")
    private String commitsUrl;

    @SerializedName("private")
    private boolean isPrivate;

    @SerializedName("default_branch")
    private String defaultBranch;

    private String id;

    @SerializedName("downloads_url")
    private String downloadsUrl;

    private String name;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("languages_url")
    private String languagesUrl;

    @SerializedName("ssh_url")
    private String sshUrl;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("git_url")
    private String gitUrl;

    @SerializedName("full_name")
    private String fullName;

    protected Repo(Parcel in) {
        teamsUrl = in.readString();
        description = in.readString();
        pushedAt = in.readString();
        homepage = in.readString();
        url = in.readString();
        size = in.readString();
        updatedAt = in.readString();
        owner = in.readParcelable(Owner.class.getClassLoader());
        language = in.readString();
        commitsUrl = in.readString();
        isPrivate = in.readByte() != 0;
        defaultBranch = in.readString();
        id = in.readString();
        downloadsUrl = in.readString();
        name = in.readString();
        createdAt = in.readString();
        languagesUrl = in.readString();
        sshUrl = in.readString();
        htmlUrl = in.readString();
        gitUrl = in.readString();
        fullName = in.readString();
    }

    public static final Creator<Repo> CREATOR = new Creator<Repo>() {
        @Override
        public Repo createFromParcel(Parcel in) {
            return new Repo(in);
        }

        @Override
        public Repo[] newArray(int size) {
            return new Repo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(teamsUrl);
        dest.writeString(description);
        dest.writeString(pushedAt);
        dest.writeString(homepage);
        dest.writeString(url);
        dest.writeString(size);
        dest.writeString(updatedAt);
        dest.writeParcelable(owner, flags);
        dest.writeString(language);
        dest.writeString(commitsUrl);
        dest.writeByte((byte) (isPrivate ? 1 : 0));
        dest.writeString(defaultBranch);
        dest.writeString(id);
        dest.writeString(downloadsUrl);
        dest.writeString(name);
        dest.writeString(createdAt);
        dest.writeString(languagesUrl);
        dest.writeString(sshUrl);
        dest.writeString(htmlUrl);
        dest.writeString(gitUrl);
        dest.writeString(fullName);
    }

    @Override
    public String toString() {
        return "Repo{" +
                "\n\tteamsUrl='" + teamsUrl + '\'' +
                "\n\tdescription='" + description + '\'' +
                "\n\tpushedAt='" + pushedAt + '\'' +
                "\n\thomepage='" + homepage + '\'' +
                "\n\turl='" + url + '\'' +
                "\n\tsize='" + size + '\'' +
                "\n\tupdatedAt='" + updatedAt + '\'' +
                "\n\towner=" + owner +
                "\n\tlanguage='" + language + '\'' +
                "\n\tcommitsUrl='" + commitsUrl + '\'' +
                "\n\tisPrivate=" + isPrivate +
                "\n\tdefaultBranch='" + defaultBranch + '\'' +
                "\n\tid='" + id + '\'' +
                "\n\tdownloadsUrl='" + downloadsUrl + '\'' +
                "\n\tname='" + name + '\'' +
                "\n\tcreatedAt='" + createdAt + '\'' +
                "\n\tlanguagesUrl='" + languagesUrl + '\'' +
                "\n\tsshUrl='" + sshUrl + '\'' +
                "\n\thtmlUrl='" + htmlUrl + '\'' +
                "\n\tgitUrl='" + gitUrl + '\'' +
                "\n\tfullName='" + fullName + '\'' +
                "\n}";
    }
}
