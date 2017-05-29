package com.trayis.simpliretrodemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.trayis.simpliretrodemo.model.Repo;
import com.trayis.simpliretrodemo.services.GitServiceFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

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
        getGit();
    }

    private void getGit() {
        GitServiceFactory.getInstance().getService().getRepos("mukundrd").subscribe(new Subscriber<Repo[]>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "Did not work " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Repo[] repos) {
                if (repos != null) {
                    Log.v(TAG, "Data length : " + repos.length);
                }

                Log.v(TAG, "Data : ");
                for (Repo data : repos) {
                    Log.v(TAG, data.toString());
                }
            }
        });
        ;
    }
}
