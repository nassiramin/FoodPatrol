package com.example.anas.foodpatrol.module.Home;

import android.util.Log;

import com.example.anas.foodpatrol.model.DataStorage;
import com.example.anas.foodpatrol.model.ResponseModel;
import com.example.anas.foodpatrol.network.ApiClient;
import com.example.anas.foodpatrol.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Anas on 05/10/2017.
 */

public class HomePresenter {
    private HomeView mView;

    public HomePresenter(HomeView mView) {
        this.mView = mView;

    }
    public void getData (){
        mView.onProgress();


        ApiInterface iApi = ApiClient.buatRequest();
        Call<ResponseModel> data = iApi.AmbilKategori();
        data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Log.d("LOG_RESPONSE", response.toString());
                Log.d("LOG_RESPONSE_BODY", response.body().toString());
                Log.d("LOG_RESPONSE_PESAN", response.body().getPesan().toString());

                if (response.body().getStatus() == true){
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

    }

}
