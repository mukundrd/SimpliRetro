package com.trayis.simpliretrodemo.services;

import com.trayis.simpliretro.BaseFactory;
import com.trayis.simpliretrodemo.model.Repo;

import retrofit2.http.Path;
import rx.Observable;

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


    public Observable<Repo[]> getRepos(String user) {
        return prepareObservable(getService().getRepos(user));
    }

}
