package com.trayis.simpliretrodemo.services

import com.trayis.simpliretrodemo.model.Repo

import androidx.annotation.WorkerThread
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Mukund Desai on 03/08/17.
 */
interface GitService {

    @WorkerThread
    @GET("users/{user}/repos")
    fun getRepos(@Path("user") user: String): Single<Array<Repo>>

}
