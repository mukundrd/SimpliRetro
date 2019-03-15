package com.trayis.simpliretrodemo.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by mudesai on 9/18/16.
 */
data class Repo protected constructor(
        @SerializedName("teams_url")
        val teamsUrl: String? = "",
        val description: String? = "",
        @SerializedName("pushed_at")
        val pushedAt: String? = "",
        val homepage: String? = "",
        val url: String? = "",
        val size: String? = "",
        @SerializedName("updated_at")
        val updatedAt: String? = "",
        val owner: Owner? = null,
        val language: String? = "",
        @SerializedName("commits_url")
        val commitsUrl: String? = "",
        @SerializedName("private")
        val isPrivate: Boolean = false,
        @SerializedName("default_branch")
        val defaultBranch: String? = "",
        val id: String? = "",
        @SerializedName("downloads_url")
        val downloadsUrl: String? = "",
        val name: String? = "",
        @SerializedName("created_at")
        val createdAt: String? = "",
        @SerializedName("languages_url")
        val languagesUrl: String? = "",
        @SerializedName("ssh_url")
        val sshUrl: String? = "",
        @SerializedName("html_url")
        val htmlUrl: String? = "",
        @SerializedName("git_url")
        val gitUrl: String? = "",
        @SerializedName("full_name")
        val fullName: String?) : Parcelable {

    private constructor (parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Owner::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte().toInt() != 0,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(teamsUrl)
        parcel.writeString(description)
        parcel.writeString(pushedAt)
        parcel.writeString(homepage)
        parcel.writeString(url)
        parcel.writeString(size)
        parcel.writeString(updatedAt)
        parcel.writeParcelable(owner, flags)
        parcel.writeString(language)
        parcel.writeString(commitsUrl)
        parcel.writeByte(if (isPrivate) 1 else 0)
        parcel.writeString(defaultBranch)
        parcel.writeString(id)
        parcel.writeString(downloadsUrl)
        parcel.writeString(name)
        parcel.writeString(createdAt)
        parcel.writeString(languagesUrl)
        parcel.writeString(sshUrl)
        parcel.writeString(htmlUrl)
        parcel.writeString(gitUrl)
        parcel.writeString(fullName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Repo> {
        override fun createFromParcel(parcel: Parcel): Repo {
            return Repo(parcel)
        }

        override fun newArray(size: Int): Array<Repo?> {
            return arrayOfNulls(size)
        }
    }

}
