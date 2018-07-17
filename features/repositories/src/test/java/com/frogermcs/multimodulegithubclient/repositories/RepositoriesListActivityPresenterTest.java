package com.frogermcs.multimodulegithubclient.repositories;

import com.frogermcs.multimodulegithubclient.base.data.api.RepositoriesManager;
import com.frogermcs.multimodulegithubclient.base.data.model.Repository;
import com.frogermcs.multimodulegithubclient.base.utils.AnalyticsManager;
import com.google.common.collect.ImmutableList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RepositoriesListActivityPresenterTest {
    RepositoriesListActivityPresenter presenter;

    @Mock
    RepositoriesListActivity repositoriesListActivityMock;
    @Mock
    RepositoriesManager repositoriesManagerMock;
    @Mock
    AnalyticsManager analyticsManagerMock;

    String username = "User1";


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new RepositoriesListActivityPresenter(
                repositoriesListActivityMock,
                repositoriesManagerMock,
                username,
                analyticsManagerMock
        );
    }

    @Test
    public void testInit_shouldLogLaunchedScreenIntoAnalytics() {
        String expectedScreenName = "screenName";
        when(repositoriesListActivityMock.getScreenName()).thenReturn(expectedScreenName);
        when(repositoriesManagerMock.getUsersRepositories(any())).thenReturn(Observable.empty());

        presenter.init();

        verify(analyticsManagerMock).logScreenView(expectedScreenName);
    }

    @Test
    public void testInit_shouldLoadRepositoriesForGivenUser() {
        when(repositoriesManagerMock.getUsersRepositories(username)).thenReturn(Observable.empty());
        presenter.init();
        verify(repositoriesManagerMock).getUsersRepositories(username);
    }

    @Test
    public void testRepositories_whenRepositoriesAreLoaded_thenShouldBePresented() {
        Repository repository1 = new Repository();
        repository1.id = 1;
        Repository repository2 = new Repository();
        repository2.id = 2;
        ImmutableList<Repository> expectedRepos = ImmutableList.of(repository1, repository2);
        when(repositoriesManagerMock.getUsersRepositories(username)).thenReturn(Observable.just(expectedRepos));

        presenter.init();

        verify(repositoriesListActivityMock).setRepositories(expectedRepos);
    }

    @Test
    public void testNavigation_whenRepositoryClicked_thenShouldLaunchRepositoryDetails() {
        Repository repository = new Repository();
        repository.id = 1;

        presenter.onRepositoryClick(repository);

        verify(repositoriesListActivityMock).openRepositoryDetailsScreen(repository, username);
    }
}