package com.frogermcs.multimodulegithubclient.repositories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.frogermcs.multimodulegithubclient.base.BaseActivity;
import com.frogermcs.multimodulegithubclient.base.data.model.Repository;
import com.frogermcs.multimodulegithubclient.feature.R;
import com.frogermcs.multimodulegithubclient.feature.R2;
import com.frogermcs.multimodulegithubclient.repository.RepositoryDetailsActivity;
import com.google.common.collect.ImmutableList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RepositoriesListActivity extends BaseActivity {
    private final static String ARG_USERNAME = "arg_username";

    @BindView(R2.id.rvRepositories)
    RecyclerView rvRepositories;
    @BindView(R2.id.pbLoading)
    ProgressBar pbLoading;

    @Inject
    RepositoriesListActivityPresenter presenter;
    @Inject
    RepositoriesListAdapter repositoriesListAdapter;
    @Inject
    LinearLayoutManager linearLayoutManager;

    public static void startRepositoriesListActivity(String username, Context context) {
        Intent intent = new Intent(context, RepositoriesListActivity.class);
        intent.putExtra(ARG_USERNAME, username);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories_list);
        setupRepositoriesListView();
        presenter.init();
    }

    private void setupRepositoriesListView() {
        rvRepositories.setAdapter(repositoriesListAdapter);
        rvRepositories.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void setupActivityComponent() {
        String username = getIntent().getStringExtra(ARG_USERNAME);
        RepositoriesFeatureComponentWrapper
                .getAppComponent(getApplication())
                .plus(new RepositoriesListActivityModule(this, username))
                .inject(this);
    }

    @Override
    public String getScreenName() {
        return "RepositoriesList";
    }

    public void showLoading(boolean loading) {
        rvRepositories.setVisibility(loading ? View.GONE : View.VISIBLE);
        pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    public void setRepositories(ImmutableList<Repository> repositories) {
        repositoriesListAdapter.updateRepositoriesList(repositories);
    }

    public void onRepositoryClick(Repository repository) {
        presenter.onRepositoryClick(repository);
    }

    public void openRepositoryDetailsScreen(Repository repository, String username) {
        RepositoryDetailsActivity.startWithRepository(repository, username, this);
    }
}
