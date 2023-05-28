package com.mohamednader.healthyhabit.Models;

import android.content.Context;

import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.Network.NetworkDelegate;
import com.mohamednader.healthyhabit.Network.RemoteSource;

public class Repository implements RepositoryInterface{

    Context context;
    RemoteSource remoteSource;
    private static Repository repo = null;

    private Repository(Context context, RemoteSource remoteSource) {
        this.context = context;
        this.remoteSource = remoteSource;
    }

    public static Repository getInstance(Context context, RemoteSource remoteSource) {
        if (repo == null) {
            repo = new Repository(context, remoteSource);
        }
        return repo;
    }


    @Override
    public void startCallToGetMealsByFirstLetter(NetworkDelegate networkDelegate, Character character) {
        remoteSource.startCallToGetMealsByFirstLetter(networkDelegate, character);
    }
}
