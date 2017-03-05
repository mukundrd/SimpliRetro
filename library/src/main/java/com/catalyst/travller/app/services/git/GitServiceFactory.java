package com.catalyst.travller.app.services.git;

import android.content.Context;

import com.catalyst.travller.app.services.BaseFactory;

import retrofit2.Retrofit;

/**
 * Created by mudesai on 9/20/16.
 */
public class GitServiceFactory extends BaseFactory {

    private static final String BASE_URL = "https://api.github.com";

    private static GitService service;

    public static void init(Context context) {
        Retrofit retrofit = getRetrofit(context, BASE_URL);
        service = retrofit.create(GitService.class);
    }

    public static GitService getService() {
        return service;
    }
}
