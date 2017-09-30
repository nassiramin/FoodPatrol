package com.example.anas.foodpatrol.module.register;

/**
 * Created by Anas on 29/09/2017.
 */

public interface RegisterView {
    void onProgress();
    void onFinished();
    void onError(String msg);
    void onSuccess();
}
