package com.trayis.simplimvpdemo;

import android.os.Bundle;

import com.trayis.simplimvp.view.SimpliActivity;
import com.trayis.simplimvpdemo.presenter.MainPresenter;

public class MainActivity extends SimpliActivity<MainPresenter, MainView> implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initializePresenter() {
    }
}
