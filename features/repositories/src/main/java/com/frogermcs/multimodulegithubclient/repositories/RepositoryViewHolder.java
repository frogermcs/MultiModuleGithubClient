package com.frogermcs.multimodulegithubclient.repositories;

import android.view.View;

import com.frogermcs.multimodulegithubclient.base.data.model.Repository;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Miroslaw Stanek on 11.06.2016.
 */

public abstract class RepositoryViewHolder extends RecyclerView.ViewHolder {
    public RepositoryViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(Repository repository);
}
