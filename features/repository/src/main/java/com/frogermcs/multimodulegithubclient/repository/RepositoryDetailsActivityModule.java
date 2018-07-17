package com.frogermcs.multimodulegithubclient.repository;

import com.frogermcs.multimodulegithubclient.base.ActivityScope;
import com.frogermcs.multimodulegithubclient.base.data.model.Repository;
import com.frogermcs.multimodulegithubclient.base.utils.AnalyticsManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@Module
public class RepositoryDetailsActivityModule {
    private final RepositoryDetailsActivity repositoryDetailsActivity;
    private final String username;
    private final Repository repository;

    public RepositoryDetailsActivityModule(RepositoryDetailsActivity repositoryDetailsActivity,
                                           String username,
                                           Repository repository) {
        this.repositoryDetailsActivity = repositoryDetailsActivity;
        this.username = username;
        this.repository = repository;
    }

    @Provides
    @ActivityScope
    RepositoryDetailsActivity provideRepositoryDetailsActivity() {
        return this.repositoryDetailsActivity;
    }

    @Provides
    @ActivityScope
    @Named("github-username")
    String provideUsername() {
        return this.username;
    }

    @Provides
    @ActivityScope
    Repository provideRepository() {
        return this.repository;
    }

    @Provides
    @ActivityScope
    RepositoryDetailsActivityPresenter provideRepositoryDetailsActivityPresenter(
            RepositoryDetailsActivity repositoryDetailsActivity,
            @Named("github-username") String username,
            AnalyticsManager analyticsManager,
            Repository repository
    ) {
        return new RepositoryDetailsActivityPresenter(
                repositoryDetailsActivity,
                username,
                analyticsManager,
                repository
        );
    }
}