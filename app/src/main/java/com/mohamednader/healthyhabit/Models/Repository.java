package com.mohamednader.healthyhabit.Models;

import android.content.Context;

import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.Network.NetworkDelegate;

public class Repository {

    Context context;
    private NetworkDelegate networkDelegate;
    private static Repository repo = null;

    private Repository(Context context, NetworkDelegate networkDelegate) {
        this.context = context;
        this.networkDelegate = networkDelegate;
    }

    public static Repository getInstance(Context context, NetworkDelegate networkDelegate) {
        if (repo == null) {
            repo = new Repository(context, networkDelegate);
        }
        return repo;
    }

    public void getAllMealsByFirstLetterFromNetwork(){
        ApiClient.getInstance().startCall(networkDelegate);
    }

}
