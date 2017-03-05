package com.catalyst.travller.app.services.gears;

import android.content.Context;

import com.catalyst.travller.app.services.BaseFactory;

import retrofit2.Retrofit;

/**
 * Created by mudesai on 9/20/16.
 */
public class GearsServiceFactory extends BaseFactory {

    private static final String BASE_URL = "http://gears.trayis.com";

    private static GearsService service;

    public static void init(Context context) {
        Retrofit retrofit = getRetrofit(context, BASE_URL);
        service = retrofit.create(GearsService.class);
    }

    public static GearsService getService() {
        return service;
    }
}
