package com.frogermcs.multimodulegithubclient.repositories;

import com.frogermcs.multimodulegithubclient.base.data.api.GithubApiService;
import com.frogermcs.multimodulegithubclient.base.data.api.RepositoriesManager;

import dagger.Module;
import dagger.Provides;

@Module
class RepositoriesModule {
    @Provides
    RepositoriesManager provideRepositoriesManager(GithubApiService githubApiService) {
        return new RepositoriesManager(githubApiService);
    }
}
