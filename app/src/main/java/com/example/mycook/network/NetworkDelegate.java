package com.example.mycook.network;

import com.example.mycook.model.Meals;

public interface NetworkDelegate {
    public void onSuccessResult(Meals meals);

    public void onFailureResult(String errorMsg);
}
