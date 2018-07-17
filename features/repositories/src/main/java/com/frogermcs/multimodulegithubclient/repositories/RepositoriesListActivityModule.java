package com.frogermcs.multimodulegithubclient.repositories;

import android.support.v7.widget.LinearLayoutManager;

import com.frogermcs.multimodulegithubclient.base.ActivityScope;
import com.frogermcs.multimodulegithubclient.base.data.api.RepositoriesManager;
import com.frogermcs.multimodulegithubclient.base.data.model.Repository;
import com.frogermcs.multimodulegithubclient.base.utils.AnalyticsManager;

import java.util.Map;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntKey;
import dagger.multibindings.IntoMap;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@Module
public class RepositoriesListActivityModule {
    private RepositoriesListActivity repositoriesListActivity;
    private String username;

    public RepositoriesListActivityModule(RepositoriesListActivity repositoriesListActivity, String username) {
        this.repositoriesListActivity = repositoriesListActivity;
        this.username = username;
    }

    @Provides
    @ActivityScope
    @Named("github-username")
    public String provideUsername() {
        return this.username;
    }


    @Provides
    @ActivityScope
    RepositoriesListActivity provideRepositoriesListActivity() {
        return repositoriesListActivity;
    }

    @Provides
    @ActivityScope
    RepositoriesListActivityPresenter provideRepositoriesListActivityPresenter(RepositoriesManager repositoriesManager,
                                                                               RepositoriesListActivity repositoriesListActivity,
                                                                               @Named("github-username") String username,
                                                                               AnalyticsManager analyticsManager) {
        return new RepositoriesListActivityPresenter(
                repositoriesListActivity,
                repositoriesManager,
                username,
                analyticsManager
        );
    }

    @Provides
    @ActivityScope
    RepositoriesListAdapter provideRepositoriesListAdapter(RepositoriesListActivity repositoriesListActivity,
                                                           Map<Integer, RepositoriesListViewHolderFactory> viewHolderFactories) {
        return new RepositoriesListAdapter(repositoriesListActivity, viewHolderFactories);
    }

    @Provides
    @ActivityScope
    LinearLayoutManager provideLinearLayoutManager(RepositoriesListActivity repositoriesListActivity) {
        return new LinearLayoutManager(repositoriesListActivity);
    }

    @Provides
    @IntoMap
    @IntKey(Repository.TYPE_NORMAL)
    RepositoriesListViewHolderFactory provideViewHolderNormal() {
        return new RepositoryViewHolderNormalFactory();
    }

    @Provides
    @IntoMap
    @IntKey(Repository.TYPE_BIG)
    RepositoriesListViewHolderFactory provideViewHolderBig() {
        return new RepositoryViewHolderBigFactory();
    }

    @Provides
    @IntoMap
    @IntKey(Repository.TYPE_FEATURED)
    RepositoriesListViewHolderFactory provideViewHolderFeatured() {
        return new RepositoryViewHolderFeaturedFactory();
    }
}