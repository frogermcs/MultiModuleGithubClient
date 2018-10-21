package com.frogermcs.multimodulegithubclient.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected abstract void setupActivityComponent();

    public abstract String getScreenName();

    /*
    This method isn't used anywhere. But it won't disappear during minification process. It's
    because we use added proguard configuration for module telling that this method should be
    kept. See features/base/build.gradle -> consumerProguardFiles for more details.
     */
    public void notUsedMethod() {
        int a = 1;
        int b = 3;
        int ab = a + b;
    }
}