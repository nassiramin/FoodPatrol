package com.example.anas.foodpatrol.module.register;

import android.content.Context;

/**
 * Created by Anas on 29/09/2017.
 */

public class RegisterPresenter {
    private RegisterView mView;

    public RegisterPresenter(RegisterView mView){
        this.mView= mView;
    }

    public void register (String nama_user, String username, String password, String confirm_password, Context konteks){
        mView.onProgress();


    }

}
