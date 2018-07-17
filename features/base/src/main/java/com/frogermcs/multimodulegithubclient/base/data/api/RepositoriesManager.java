package com.frogermcs.multimodulegithubclient.base.data.api;

import com.frogermcs.multimodulegithubclient.base.data.api.response.RepositoryResponse;
import com.frogermcs.multimodulegithubclient.base.data.model.Repository;
import com.google.common.collect.ImmutableList;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
public class RepositoriesManager {
    private GithubApiService githubApiService;

    public RepositoriesManager(GithubApiService githubApiService) {
        this.githubApiService = githubApiService;
    }

    public Observable<ImmutableList<Repository>> getUsersRepositories(String username) {
        return githubApiService.getUsersRepositories(username)
                .map(repositoriesListResponse -> {
                    final ImmutableList.Builder<Repository> listBuilder = ImmutableList.builder();
                    for (RepositoryResponse repositoryResponse : repositoriesListResponse) {
                        Repository repository = new Repository();
                        repository.id = repositoryResponse.id;
                        repository.name = repositoryResponse.name;
                        repository.url = repositoryResponse.url;
                        repository.stargazers_count = repositoryResponse.stargazers_count;
                        repository.forks_count = repositoryResponse.forks_count;
                        listBuilder.add(repository);
                    }
                    return listBuilder.build();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
