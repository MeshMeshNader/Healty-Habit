package com.mohamednader.healthyhabit.Network;

import java.util.List;

public interface NetworkDelegate {
    public void onSuccessResponse();
    public void onFailureResponse(String errorMsg);
}

