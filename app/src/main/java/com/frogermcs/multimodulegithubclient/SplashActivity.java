package com.frogermcs.multimodulegithubclient;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.frogermcs.multimodulegithubclient.base.BaseActivity;
import com.frogermcs.multimodulegithubclient.base.data.model.User;
import com.frogermcs.multimodulegithubclient.base.utils.AnalyticsManager;
import com.frogermcs.multimodulegithubclient.repositories.RepositoriesListActivity;
import com.jakewharton.rxbinding.widget.RxTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;


public class SplashActivity extends BaseActivity {

    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;
    @BindView(R.id.btnShowRepositories)
    Button btnShowRepositories;

    @Inject
    SplashActivityPresenter presenter;
    @Inject
    AnalyticsManager analyticsManager;

    private Subscription textChangeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textChangeSubscription = RxTextView.textChangeEvents(etUsername)
                .subscribe(textViewTextChangeEvent -> {
                    presenter.username = textViewTextChangeEvent.text().toString();
                    etUsername.setError(null);
                });
        presenter.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textChangeSubscription.unsubscribe();
    }

    @Override
    protected void setupActivityComponent() {
        AppComponentWrapper
                .getAppComponent(getApplication())
                .plus(new SplashActivityModule(this))
                .inject(this);
    }

    @Override
    public String getScreenName() {
        return "Splash";
    }

    @OnClick(R.id.btnShowRepositories)
    public void onShowRepositoriesClick() {
        presenter.onShowRepositoriesClick();
    }

    public void showRepositoriesListForUser(User user) {
        RepositoriesListActivity.startRepositoriesListActivity(user.login, this);
    }

    public void showValidationError() {
        etUsername.setError("Validation error");
    }

    public void showLoading(boolean loading) {
        btnShowRepositories.setVisibility(loading ? View.GONE : View.VISIBLE);
        pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
    }
}
