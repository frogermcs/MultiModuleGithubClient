package com.frogermcs.multimodulegithubclient.repository;

import android.app.Application;

import com.frogermcs.multimodulegithubclient.base.BaseComponent;
import com.frogermcs.multimodulegithubclient.base.BaseComponentWrapper;

public class RepositoryFeatureComponentWrapper {

    private static RepositoryFeatureComponentWrapper repositoryFeatureComponentWrapper;

    private RepositoryFeatureComponentWrapper() {

    }

    public static RepositoryFeatureComponentWrapper getInstance(Application application) {
        if (repositoryFeatureComponentWrapper == null) {
            synchronized (RepositoryFeatureComponentWrapper.class) {
                if (repositoryFeatureComponentWrapper == null) {
                    repositoryFeatureComponentWrapper = new RepositoryFeatureComponentWrapper();
                    repositoryFeatureComponentWrapper.initializeComponent(BaseComponentWrapper.getBaseComponent(application));
                }
            }
        }
        return repositoryFeatureComponentWrapper;
    }

    private RepositoryFeatureComponent repositoryFeatureComponent;

    public static RepositoryFeatureComponent getRepositoryFeatureComponent(Application application) {
        RepositoryFeatureComponentWrapper appComponentWrapper = getInstance(application);
        return appComponentWrapper.repositoryFeatureComponent;
    }

    public RepositoryFeatureComponent initializeComponent(BaseComponent baseComponent) {
        repositoryFeatureComponent = DaggerRepositoryFeatureComponent.builder()
                .baseComponent(baseComponent)
                .build();
        return repositoryFeatureComponent;
    }
}
