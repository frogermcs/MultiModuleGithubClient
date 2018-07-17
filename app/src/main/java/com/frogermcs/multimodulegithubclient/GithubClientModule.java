package com.frogermcs.multimodulegithubclient;

import com.frogermcs.multimodulegithubclient.base.data.api.GithubApiService;
import com.frogermcs.multimodulegithubclient.base.data.api.UserManager;

import dagger.Module;
import dagger.Provides;

@Module
public class GithubClientModule {

    @Provides
    @AppScope
    public UserManager provideUserManager(GithubApiService githubApiService) {
        return new UserManager(githubApiService);
    }
}
