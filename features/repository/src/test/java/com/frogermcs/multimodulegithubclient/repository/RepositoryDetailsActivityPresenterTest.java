package com.frogermcs.multimodulegithubclient.repository;

import com.frogermcs.multimodulegithubclient.base.data.model.Repository;
import com.frogermcs.multimodulegithubclient.base.utils.AnalyticsManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RepositoryDetailsActivityPresenterTest {
    RepositoryDetailsActivityPresenter presenter;

    @Mock
    RepositoryDetailsActivity repositoryDetailsActivityMock;
    @Mock
    AnalyticsManager analyticsManagerMock;

    Repository repository = new Repository();
    String username = "User1";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new RepositoryDetailsActivityPresenter(
                repositoryDetailsActivityMock,
                username,
                analyticsManagerMock,
                repository
        );
    }

    @Test
    public void testInit_shouldLogLaunchedScreenIntoAnalytics() {
        String expectedScreenName = "screenName";
        when(repositoryDetailsActivityMock.getScreenName()).thenReturn(expectedScreenName);

        presenter.init();

        verify(analyticsManagerMock).logScreenView(expectedScreenName);
    }

    @Test
    public void testInit_shouldSetUserName() {
        presenter.init();
        verify(repositoryDetailsActivityMock).setupUserName(username);
    }

    @Test
    public void testUnit_shouldSetRepositoryDetails() {
        repository.name = "repoName";
        repository.url = "url";
        presenter.init();
        verify(repositoryDetailsActivityMock).setRepositoryDetails(repository.name, repository.url);
    }
}