package com.mohamednader.healthyhabit.Network;

public interface RemoteSource {
    public void startCallToGetMealsByFirstLetter(NetworkDelegate networkDelegate , Character character);
}
