package com.trayis.simpliretrodemo;

import android.app.Application;

import com.trayis.simpliretrodemo.services.GitServiceFactory;

/**
 * Created by Mukund on 05-03-2017.
 */

public class RetroApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        GitServiceFactory.getInstance().init(this);
    }

}
