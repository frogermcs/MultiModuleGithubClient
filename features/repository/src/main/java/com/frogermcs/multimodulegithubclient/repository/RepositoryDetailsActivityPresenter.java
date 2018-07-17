package com.frogermcs.multimodulegithubclient.repository;

import com.frogermcs.multimodulegithubclient.base.data.model.Repository;
import com.frogermcs.multimodulegithubclient.base.utils.AnalyticsManager;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
public class RepositoryDetailsActivityPresenter {
    private final RepositoryDetailsActivity repositoryDetailsActivity;
    private final String username;
    private final AnalyticsManager analyticsManager;
    private final Repository repository;

    public RepositoryDetailsActivityPresenter(RepositoryDetailsActivity repositoryDetailsActivity,
                                              String username,
                                              AnalyticsManager analyticsManager,
                                              Repository repository) {
        this.repositoryDetailsActivity = repositoryDetailsActivity;
        this.username = username;
        this.analyticsManager = analyticsManager;
        this.repository = repository;
    }

    public void init() {
        analyticsManager.logScreenView(repositoryDetailsActivity.getScreenName());
        repositoryDetailsActivity.setupUserName(username);
        repositoryDetailsActivity.setRepositoryDetails(repository.name, repository.url);
    }
}
