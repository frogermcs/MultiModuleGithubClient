package com.frogermcs.multimodulegithubclient.repository;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.frogermcs.multimodulegithubclient.base.BaseActivity;
import com.frogermcs.multimodulegithubclient.base.data.model.Repository;
import com.frogermcs.multimodulegithubclient.base.utils.AnalyticsManager;

import javax.inject.Inject;

import butterknife.BindView;


public class RepositoryDetailsActivity extends BaseActivity {
    private static final String ARG_REPOSITORY = "arg_repository";
    private static final String ARG_USERNAME = "arg_username";

    @BindView(R2.id.tvRepoName)
    TextView tvRepoName;
    @BindView(R2.id.tvRepoDetails)
    TextView tvRepoDetails;
    @BindView(R2.id.tvUserName)
    TextView tvUserName;

    @Inject
    AnalyticsManager analyticsManager;
    @Inject
    RepositoryDetailsActivityPresenter presenter;

    public static void startWithRepository(Repository repository, String username, Activity startingActivity) {
        Intent intent = new Intent(startingActivity, RepositoryDetailsActivity.class);
        intent.putExtra(ARG_REPOSITORY, repository);
        intent.putExtra(ARG_USERNAME, username);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_details);
        presenter.init();
    }

    public void setRepositoryDetails(String name, String url) {
        tvRepoName.setText(name);
        tvRepoDetails.setText(url);
    }

    @Override
    protected void setupActivityComponent() {
        String username = getIntent().getStringExtra(ARG_USERNAME);
        Repository repository = getIntent().getParcelableExtra(ARG_REPOSITORY);
        RepositoryFeatureComponentWrapper
                .getRepositoryFeatureComponent(getApplication())
                .plus(new RepositoryDetailsActivityModule(this, username, repository))
                .inject(this);

    }

    @Override
    public String getScreenName() {
        return "RepositoryDetails";
    }

    public void setupUserName(String userName) {
        tvUserName.setText(userName);
    }
}