package com.frogermcs.multimodulegithubclient.base;

import com.frogermcs.multimodulegithubclient.base.data.api.GithubApiService;
import com.frogermcs.multimodulegithubclient.base.data.api.NetworkingModule;
import com.frogermcs.multimodulegithubclient.base.utils.AnalyticsManager;
import com.frogermcs.multimodulegithubclient.base.utils.Validator;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
@Singleton
@Component(
        modules = {
                BaseModule.class,
                NetworkingModule.class
        }
)
public interface BaseComponent {

    AnalyticsManager analyticsManager();

    Validator validator();

    GithubApiService githubApiService();
}