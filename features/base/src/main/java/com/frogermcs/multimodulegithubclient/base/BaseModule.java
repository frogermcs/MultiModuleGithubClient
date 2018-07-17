package com.frogermcs.multimodulegithubclient.base;

import android.app.Application;

import com.frogermcs.multimodulegithubclient.base.utils.AnalyticsManager;
import com.frogermcs.multimodulegithubclient.base.utils.Validator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BaseModule {
    private Application application;

    public BaseModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    AnalyticsManager provideAnalyticsManager() {
        return new AnalyticsManager(application);
    }

    @Provides
    @Singleton
    Validator provideValidator() {
        return new Validator();
    }
}