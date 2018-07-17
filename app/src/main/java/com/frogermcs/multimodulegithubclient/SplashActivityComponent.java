package com.frogermcs.multimodulegithubclient;

import com.frogermcs.multimodulegithubclient.base.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@ActivityScope
@Subcomponent(
        modules = SplashActivityModule.class
)
public interface SplashActivityComponent {

    SplashActivity inject(SplashActivity splashActivity);

}