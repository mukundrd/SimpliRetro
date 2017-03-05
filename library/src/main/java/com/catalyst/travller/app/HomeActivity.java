package com.catalyst.travller.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.catalyst.travller.app.model.MakesData;
import com.catalyst.travller.app.model.Repo;
import com.catalyst.travller.app.services.empprofile.EmpProfileServiceFactory;
import com.catalyst.travller.app.services.empprofile.model.EmployeeProfile;
import com.catalyst.travller.app.services.gears.GearsServiceFactory;
import com.catalyst.travller.app.services.git.GitServiceFactory;

import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {
//        implements View.OnClickListener {

//    private static final String TAG = "HomeActivity";
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_home);
//        findViewById(R.id.click_git).setOnClickListener(this);
//        findViewById(R.id.click_gears).setOnClickListener(this);
//        findViewById(R.id.click_profile).setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.click_git:
//                getGit();
//                break;
//            case R.id.click_gears:
//                getGears();
//                break;
//            case R.id.click_profile:
//                getEmployees();
//                break;
//        }
//    }
//
//    private void getGit() {
//        Observer<Repo[]> observer = new Observer<Repo[]>() {
//            @Override
//            public void onCompleted() {
//                Log.i(TAG, "Completed");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e(TAG, e.getMessage(), e);
//            }
//
//            @Override
//            public void onNext(Repo[] reposData) {
//                if (reposData != null) {
//                    Log.v(TAG, "Data length : " + reposData.length);
//                }
//                Log.v(TAG, "Data : " + reposData.toString());
//            }
//        };
//
//        Observable<Repo[]> repos = GitServiceFactory.getService().getRepos("mukundrd");
//        repos.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//    }
//
//    private void getGears() {
//        Observer<MakesData> observer = new Observer<MakesData>() {
//            @Override
//            public void onCompleted() {
//                Log.i(TAG, "Completed");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e(TAG, e.getMessage(), e);
//            }
//
//            @Override
//            public void onNext(MakesData makesData) {
//                Log.v(TAG, "Data : " + makesData.toString());
//            }
//        };
//
//        Observable<MakesData> repos = GearsServiceFactory.getService().getRepos();
//        repos.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//    }
//
//    private void getEmployees(){
//        Observer<EmployeeProfile> observer=new Observer<EmployeeProfile>() {
//            @Override
//            public void onCompleted() {
//                Log.i(TAG, "Completed");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e(TAG, e.getMessage(), e);
//            }
//
//            @Override
//            public void onNext(EmployeeProfile employeeProfile) {
//                Log.v(TAG, "Data : " + employeeProfile.toString());
//            }
//        };
//
//        Observable<EmployeeProfile> empProfile= EmpProfileServiceFactory.getService().getEmployeeProfile();
//        empProfile.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//    }
}
