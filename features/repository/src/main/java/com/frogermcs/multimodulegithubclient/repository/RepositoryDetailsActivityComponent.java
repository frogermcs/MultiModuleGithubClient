package com.frogermcs.multimodulegithubclient.repository;

import com.frogermcs.multimodulegithubclient.base.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@ActivityScope
@Subcomponent(
        modules = RepositoryDetailsActivityModule.class
)
public interface RepositoryDetailsActivityComponent {
    RepositoryDetailsActivity inject(RepositoryDetailsActivity repositoriesListActivity);
}