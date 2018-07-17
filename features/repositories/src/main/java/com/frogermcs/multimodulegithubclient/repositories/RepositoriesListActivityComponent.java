package com.frogermcs.multimodulegithubclient.repositories;

import com.frogermcs.multimodulegithubclient.base.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@ActivityScope
@Subcomponent(
        modules = RepositoriesListActivityModule.class
)
public interface RepositoriesListActivityComponent {

    RepositoriesListActivity inject(RepositoriesListActivity repositoriesListActivity);

}