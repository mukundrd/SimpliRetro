package com.trayis.simpliretrodemo.services

import com.trayis.simpliretro.BaseFactory
import com.trayis.simpliretrodemo.model.Repo

import io.reactivex.Single

/**
 * Created by mudesai on 9/20/16.
 */
class GitServiceFactory private constructor() : BaseFactory<GitService>(BASE_URL) {

    fun getRepos(user: String): Single<Array<Repo>> {
        return prepareSingle(service!!.getRepos(user))
    }

    companion object {

        private val BASE_URL = "https://api.github.com"

        var inst: GitServiceFactory? = null

        @Synchronized
        fun getInstance(): GitServiceFactory {
            if (inst == null) {
                inst = GitServiceFactory()
            }
            return inst!!
        }
    }

}
