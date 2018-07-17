package com.frogermcs.multimodulegithubclient.repositories;

import com.frogermcs.multimodulegithubclient.base.data.api.RepositoriesManager;
import com.frogermcs.multimodulegithubclient.base.data.model.Repository;
import com.frogermcs.multimodulegithubclient.base.utils.AnalyticsManager;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
public class RepositoriesListActivityPresenter {
    private final RepositoriesListActivity repositoriesListActivity;
    private final RepositoriesManager repositoriesManager;
    private final String username;
    private final AnalyticsManager analyticsManager;

    public RepositoriesListActivityPresenter(RepositoriesListActivity repositoriesListActivity,
                                             RepositoriesManager repositoriesManager,
                                             String username,
                                             AnalyticsManager analyticsManager) {
        this.repositoriesListActivity = repositoriesListActivity;
        this.repositoriesManager = repositoriesManager;
        this.username = username;
        this.analyticsManager = analyticsManager;
    }

    public void init() {
        analyticsManager.logScreenView(repositoriesListActivity.getScreenName());
        loadRepositories();
    }

    public void loadRepositories() {
        repositoriesListActivity.showLoading(true);
        repositoriesManager.getUsersRepositories(username)
                .doOnTerminate(() -> repositoriesListActivity.showLoading(false))
                .subscribe(
                        repositoriesListActivity::setRepositories,
                        Throwable::printStackTrace
                );
    }

    public void onRepositoryClick(Repository repository) {
        repositoriesListActivity.openRepositoryDetailsScreen(repository, username);
    }
}
