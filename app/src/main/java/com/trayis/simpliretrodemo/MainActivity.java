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
        Call<Repo[]> repos = GitServiceFactory.getInstance().getService().getRepos("mukundrd");
        repos.enqueue(new Callback<Repo[]>() {
            @Override
            public void onResponse(Call<Repo[]> call, Response<Repo[]> response) {
                if (response.isSuccessful()) {
                    Repo[] reposData = response.body();
                    if (reposData != null) {
                        Log.v(TAG, "Data length : " + reposData.length);
                    }

                    Log.v(TAG, "Data : ");
                    for (Repo data : reposData) {
                        Log.v(TAG, data.toString());
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error code " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Repo[]> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Did not work " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
