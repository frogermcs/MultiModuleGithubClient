package com.frogermcs.multimodulegithubclient.repositories;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Miroslaw Stanek on 11.06.2016.
 */

public interface RepositoriesListViewHolderFactory {
    RecyclerView.ViewHolder createViewHolder(ViewGroup parent);
}
