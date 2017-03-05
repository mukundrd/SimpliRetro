package com.catalyst.travller.app;

import android.app.Application;

import com.catalyst.travller.app.services.gears.GearsServiceFactory;
import com.catalyst.travller.app.services.git.GitServiceFactory;

/**
 * Created by mudesai on 9/18/16.
 */
public class TravelApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        GearsServiceFactory.init(TravelApplication.this);
        GitServiceFactory.init(TravelApplication.this);
    }

}
