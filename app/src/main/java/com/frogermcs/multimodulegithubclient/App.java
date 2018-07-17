package com.frogermcs.multimodulegithubclient;

import android.app.Application;

import com.frogermcs.multimodulegithubclient.base.BuildConfig;

import timber.log.Timber;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}