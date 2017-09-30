package com.example.anas.foodpatrol.module.login;

/**
 * Created by AnasBayu on 28/09/2017.
 */

public interface LoginView {
    void onProgress();
    void onFinished();
    void onError(String msg);
    void onSuccess();
}
