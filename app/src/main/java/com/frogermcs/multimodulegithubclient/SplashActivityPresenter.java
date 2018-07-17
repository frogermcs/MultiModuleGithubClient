package com.frogermcs.multimodulegithubclient;

import com.frogermcs.multimodulegithubclient.base.data.api.UserManager;
import com.frogermcs.multimodulegithubclient.base.utils.AnalyticsManager;
import com.frogermcs.multimodulegithubclient.base.utils.Validator;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
public class SplashActivityPresenter {
    public String username;

    private final SplashActivity splashActivity;
    private final Validator validator;
    private final UserManager userManager;
    private final AnalyticsManager analyticsManager;

    public SplashActivityPresenter(SplashActivity splashActivity,
                                   Validator validator,
                                   UserManager userManager,
                                   AnalyticsManager analyticsManager) {
        this.splashActivity = splashActivity;
        this.validator = validator;
        this.userManager = userManager;
        this.analyticsManager = analyticsManager;
    }

    public void init() {
        analyticsManager.logScreenView(splashActivity.getScreenName());
    }

    public void onShowRepositoriesClick() {
        if (validator.validUsername(username)) {
            splashActivity.showLoading(true);
            userManager.getUser(username)
                    .doOnTerminate(() -> splashActivity.showLoading(false))
                    .subscribe(
                            splashActivity::showRepositoriesListForUser,
                            throwable -> splashActivity.showValidationError());
        } else {
            splashActivity.showValidationError();
        }
    }
}
