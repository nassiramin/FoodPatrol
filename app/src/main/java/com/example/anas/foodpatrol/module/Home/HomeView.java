package com.example.anas.foodpatrol.module.Home;

/**
 * Created by Anas on 05/10/2017.
 */

public interface HomeView {
    void onProgress();
    void onFinished();
    void onError(String msg);
    void onSuccess();

}
