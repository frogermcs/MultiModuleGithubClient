package com.frogermcs.multimodulegithubclient;

import com.frogermcs.multimodulegithubclient.base.ActivityScope;
import com.frogermcs.multimodulegithubclient.base.data.api.UserManager;
import com.frogermcs.multimodulegithubclient.base.utils.AnalyticsManager;
import com.frogermcs.multimodulegithubclient.base.utils.Validator;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@Module
public class SplashActivityModule {
    private SplashActivity splashActivity;

    public SplashActivityModule(SplashActivity splashActivity) {
        this.splashActivity = splashActivity;
    }

    @Provides
    @ActivityScope
    SplashActivity provideSplashActivity() {
        return splashActivity;
    }

    @Provides
    @ActivityScope
    SplashActivityPresenter
    provideSplashActivityPresenter(Validator validator,
                                   UserManager userManager,
                                   AnalyticsManager analyticsManager) {
        return new SplashActivityPresenter(
                splashActivity,
                validator,
                userManager,
                analyticsManager
        );
    }
}