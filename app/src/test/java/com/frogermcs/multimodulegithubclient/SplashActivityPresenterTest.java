package com.frogermcs.multimodulegithubclient;

import com.frogermcs.multimodulegithubclient.base.data.api.UserManager;
import com.frogermcs.multimodulegithubclient.base.data.model.User;
import com.frogermcs.multimodulegithubclient.base.utils.AnalyticsManager;
import com.frogermcs.multimodulegithubclient.base.utils.Validator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SplashActivityPresenterTest {
    SplashActivityPresenter presenter;

    @Mock
    SplashActivity splashActivityMock;
    @Mock
    Validator validatorMock;
    @Mock
    UserManager userManagerMock;
    @Mock
    AnalyticsManager analyticsManagerMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new SplashActivityPresenter(
                splashActivityMock,
                validatorMock,
                userManagerMock,
                analyticsManagerMock
        );
    }

    @Test
    public void testInit_shouldLogLaunchedScreenIntoAnalytics() {
        String expectedScreenName = "screenName";
        when(splashActivityMock.getScreenName()).thenReturn(expectedScreenName);

        presenter.init();

        verify(analyticsManagerMock).logScreenView(expectedScreenName);
    }

    @Test
    public void testValidation_whenUserNameValid_thenShouldLoadUser() {
        String expectedUsername = "user1";
        when(validatorMock.validUsername(expectedUsername)).thenReturn(true);
        User expepectedUser = mock(User.class);
        when(userManagerMock.getUser(expectedUsername)).thenReturn(Observable.just(expepectedUser));

        presenter.username = expectedUsername;
        presenter.onShowRepositoriesClick();

        verify(userManagerMock).getUser(expectedUsername);
    }

    @Test
    public void testValidation_whenUserNameIsInvalid_thenShouldShowValidationError() {
        String expectedUsername = "user1";
        when(validatorMock.validUsername(expectedUsername)).thenReturn(false);

        presenter.username = expectedUsername;
        presenter.onShowRepositoriesClick();

        verify(splashActivityMock).showValidationError();
    }

    @Test
    public void testErrorHandling_whenErrorOccuredWhileLoadingUser_thenShouldShowValidationError() {
        String expectedUsername = "user1";
        when(validatorMock.validUsername(expectedUsername)).thenReturn(true);
        when(userManagerMock.getUser(expectedUsername)).thenReturn(Observable.error(new RuntimeException()));

        presenter.username = expectedUsername;
        presenter.onShowRepositoriesClick();

        verify(splashActivityMock).showValidationError();
    }

    @Test
    public void testNavigation_whenUserLoaded_thenShouldNavigateToRepositoriesList() {
        String expectedUsername = "user1";
        when(validatorMock.validUsername(expectedUsername)).thenReturn(true);
        User expepectedUser = mock(User.class);
        when(userManagerMock.getUser(expectedUsername)).thenReturn(Observable.just(expepectedUser));

        presenter.username = expectedUsername;
        presenter.onShowRepositoriesClick();

        verify(splashActivityMock).showRepositoriesListForUser(expepectedUser);
    }
}