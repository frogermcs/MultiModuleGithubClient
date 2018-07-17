package com.frogermcs.multimodulegithubclient.base.data.api;


import com.frogermcs.multimodulegithubclient.base.data.model.User;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
public class UserManager {

    private GithubApiService githubApiService;

    public UserManager(GithubApiService githubApiService) {
        this.githubApiService = githubApiService;
    }

    public Observable<User> getUser(String username) {
        return githubApiService.getUser(username)
                .map(userResponse -> {
                    User user = new User();
                    user.login = userResponse.login;
                    user.id = userResponse.id;
                    user.url = userResponse.url;
                    user.email = userResponse.email;
                    return user;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
