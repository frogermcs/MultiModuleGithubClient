package com.frogermcs.multimodulegithubclient;

import com.frogermcs.multimodulegithubclient.base.BaseComponent;

import dagger.Component;

@AppScope
@Component(
        modules = GithubClientModule.class,
        dependencies = BaseComponent.class
)
public interface AppComponent {
    SplashActivityComponent plus(SplashActivityModule module);
}
