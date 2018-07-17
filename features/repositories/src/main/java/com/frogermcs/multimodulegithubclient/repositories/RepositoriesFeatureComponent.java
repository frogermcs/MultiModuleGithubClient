package com.frogermcs.multimodulegithubclient.repositories;

import com.frogermcs.multimodulegithubclient.base.BaseComponent;

import dagger.Component;

@RepositoriesFeatureScope
@Component(
        dependencies = BaseComponent.class,
        modules = RepositoriesModule.class
)
public interface RepositoriesFeatureComponent {
    RepositoriesListActivityComponent plus(RepositoriesListActivityModule module);
}
