package com.trayis.simpliretrodemo.services

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.trayis.simpliretro.adapter.response.SimpliResponse
import com.trayis.simpliretrodemo.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Mukund Desai on 03/08/17.
 */
interface GitService {

    @WorkerThread
    @GET("users/{user}/repos")
    fun getRepos(@Path("user") user: String): LiveData<SimpliResponse<Array<Repo>>>

}
