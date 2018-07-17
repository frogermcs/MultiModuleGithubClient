package com.frogermcs.multimodulegithubclient;

import android.app.Application;

import com.frogermcs.multimodulegithubclient.base.BaseComponent;
import com.frogermcs.multimodulegithubclient.base.BaseComponentWrapper;

public class AppComponentWrapper {

    private static AppComponentWrapper appComponentWrapper;

    private AppComponentWrapper() {

    }

    public static AppComponentWrapper getInstance(Application application) {
        if (appComponentWrapper == null) {
            synchronized (AppComponentWrapper.class) {
                if (appComponentWrapper == null) {
                    appComponentWrapper = new AppComponentWrapper();
                    appComponentWrapper.initializeComponent(BaseComponentWrapper.getBaseComponent(application));
                }
            }
        }
        return appComponentWrapper;
    }

    private AppComponent appComponent;

    public static AppComponent getAppComponent(Application application) {
        AppComponentWrapper appComponentWrapper = getInstance(application);
        return appComponentWrapper.appComponent;
    }

    public AppComponent initializeComponent(BaseComponent baseComponent) {
        appComponent = DaggerAppComponent.builder()
                .baseComponent(baseComponent)
                .build();
        return appComponent;
    }
}
