package com.trayis.simpliretrodemo.services;

import com.trayis.simpliretro.BaseFactory;
import com.trayis.simpliretrodemo.model.Repo;

import io.reactivex.Single;

/**
 * Created by mudesai on 9/20/16.
 */
public class GitServiceFactory extends BaseFactory<GitService> {

    private static final String BASE_URL = "https://api.github.com";

    private static GitServiceFactory instance;

    private GitServiceFactory() {
        super(BASE_URL);
    }

    public static synchronized GitServiceFactory getInstance() {
        if (instance == null) {
            instance = new GitServiceFactory();
        }
        return instance;
    }


    public Single<Repo[]> getRepos(String user) {
        return prepareSingle(getService().getRepos(user));
    }

}
