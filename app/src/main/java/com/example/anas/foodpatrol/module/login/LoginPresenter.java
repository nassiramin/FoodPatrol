package com.example.anas.foodpatrol.module.login;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.example.anas.foodpatrol.model.DataStorage;
import com.example.anas.foodpatrol.model.ResponseModel;
import com.example.anas.foodpatrol.network.ApiClient;
import com.example.anas.foodpatrol.network.ApiInterface;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by AnasBayu on 28/09/2017.
 */

public class LoginPresenter {
    private LoginView mView;

    public LoginPresenter(LoginView mView) {
        this.mView = mView;
    }

    public void login(String username, String password, Context konteks){
        mView.onProgress();

        if(cekKoneksi(konteks)){
            if(validateLogin(username, password)){
                ApiInterface iApi = ApiClient.buatRequest();
                Call<ResponseModel> data = iApi.login(username, password);
                data.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        Log.d("LOG_RESPONSE", response.toString());
                        Log.d("LOG_RESPONSE_BODY", response.body().toString());
                        Log.d("LOG_RESPONSE_PESAN", response.body().getPesan().toString());

                        if (response.body().getStatus() == true){
                            DataStorage.setUsername("username");
                            mView.onSuccess();
                        }else{
                            mView.onError(response.body().getPesan());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        t.printStackTrace();
                        Log.d("LOG_RESPONSE_ERR", "ERR");
                        mView.onError("gagal login");
                    }
                });
            }else{
                mView.onError("Password atau username kurang lengkap");
            }
        }else{
            mView.onError("Tidak ada koneksi");
        }
    }

    private boolean validateLogin(String username, String password) {
        if (username.trim().length() > 0 && password.trim().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean cekKoneksi(Context konteks){
        ConnectivityManager conMgr = (ConnectivityManager) konteks.getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
                return true;
            } else {
                return false;
            }
        }
    }
}
