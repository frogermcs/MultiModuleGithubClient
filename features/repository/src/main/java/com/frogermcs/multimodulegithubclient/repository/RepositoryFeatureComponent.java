package com.frogermcs.multimodulegithubclient.repository;

import com.frogermcs.multimodulegithubclient.base.BaseComponent;

import dagger.Component;

@RepositoryFeatureScope
@Component(
        dependencies = BaseComponent.class
)
public interface RepositoryFeatureComponent {
    RepositoryDetailsActivityComponent plus(RepositoryDetailsActivityModule module);
}
