package com.frogermcs.multimodulegithubclient.base;

import android.app.Application;

public class BaseComponentWrapper {

    private static BaseComponentWrapper baseComponentWrapper;

    private BaseComponentWrapper() {

    }

    public static BaseComponentWrapper getInstance(Application application) {
        if (baseComponentWrapper == null) {
            synchronized (BaseComponentWrapper.class) {
                if (baseComponentWrapper == null) {
                    baseComponentWrapper = new BaseComponentWrapper();
                    baseComponentWrapper.initializeComponent(application);
                }
            }
        }
        return baseComponentWrapper;
    }

    private BaseComponent baseComponent;

    public static BaseComponent getBaseComponent(Application application) {
        BaseComponentWrapper appComponentWrapper = getInstance(application);
        return appComponentWrapper.baseComponent;
    }

    public BaseComponent initializeComponent(Application application) {
        baseComponent = DaggerBaseComponent.builder()
                .baseModule(new BaseModule(application))
                .build();
        return baseComponent;
    }
}
