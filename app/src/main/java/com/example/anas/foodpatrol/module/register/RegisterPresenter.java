package com.example.anas.foodpatrol.module.register;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.example.anas.foodpatrol.model.DataStorage;
import com.example.anas.foodpatrol.model.ResponseModel;
import com.example.anas.foodpatrol.network.ApiClient;
import com.example.anas.foodpatrol.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Anas on 29/09/2017.
 */

public class RegisterPresenter {
    private RegisterView mView;

    public RegisterPresenter(RegisterView mView){
        this.mView= mView;
    }

    public void register (String nama_user, String username, String password, String confirm_password,
                          Context konteks){
        mView.onProgress();
        if(cekKoneksi(konteks)){
            if(validateRegister (nama_user,username, password,confirm_password)){
                ApiInterface iApi = ApiClient.buatRequest();
                Call<ResponseModel> data = iApi.register(nama_user, username, password,confirm_password, "2");
                data.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        Log.d("LOG_RESPONSE", response.toString());
                        Log.d("LOG_RESPONSE_BODY", response.body().toString());
                        Log.d("LOG_RESPONSE_PESAN", response.body().getPesan().toString());

                        if(response.body().getStatus()==true) {
                            mView.onSuccess();
                        }
                        else{
                            mView.onError(response.body().getPesan());
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        t.printStackTrace();
                        Log.d("LOG_RESPONSE_ERR", "ERR");
                        mView.onError("gagal register");
                    }


                });
            }else{
                mView.onError("data kurang lengkap");
            }
        }else{
            mView.onError("Tidak ada koneksi");
        }


    }

    private boolean validateRegister(String nama_user ,String username, String password,String confirm_password) {
        if (nama_user.trim().length() > 0 && username.trim().length() > 0 && password.trim().length() > 0 && confirm_password.trim().length() >0) {
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
