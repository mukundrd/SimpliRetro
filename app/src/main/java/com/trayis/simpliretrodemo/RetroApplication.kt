package com.trayis.simpliretrodemo

import android.app.Application

import com.trayis.simpliretrodemo.services.GitServiceFactory

/**
 * Created by Mukund on 05-03-2017.
 */

class RetroApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        GitServiceFactory.getInstance().init(this)
    }

}
