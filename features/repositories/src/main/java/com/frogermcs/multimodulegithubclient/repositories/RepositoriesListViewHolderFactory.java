package com.frogermcs.multimodulegithubclient.repositories;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Miroslaw Stanek on 11.06.2016.
 */

public interface RepositoriesListViewHolderFactory {
    RecyclerView.ViewHolder createViewHolder(ViewGroup parent);
}
