package com.trayis.simpliretrodemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.trayis.simpliretrodemo.model.Repo;
import com.trayis.simpliretrodemo.services.GitServiceFactory;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.catalyst.travller.app.R.layout.activity_home);
        findViewById(com.catalyst.travller.app.R.id.click_git).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case com.catalyst.travller.app.R.id.click_git:
                getGit();
                break;
        }
    }

    private void getGit() {
        Observer<Repo[]> observer = new Observer<Repo[]>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "Completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.getMessage(), e);
            }

            @Override
            public void onNext(Repo[] reposData) {
                if (reposData != null) {
                    Log.v(TAG, "Data length : " + reposData.length);
                }

                Log.v(TAG, "Data : ");
                for (Repo data : reposData) {
                    Log.v(TAG, data.toString());
                }
            }
        };

        Observable<Repo[]> repos = GitServiceFactory.getInstance().getService().getRepos("mukundrd");
        repos.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
