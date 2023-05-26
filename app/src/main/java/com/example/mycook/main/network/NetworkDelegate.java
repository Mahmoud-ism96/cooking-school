package com.example.mycook.main.network;

import com.example.mycook.main.model.Meals;

public interface NetworkDelegate {
    public void onSuccessResult(Meals meals);

    public void onFailureResult(String errorMsg);
}
