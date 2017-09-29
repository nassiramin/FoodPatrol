package com.example.anas.foodpatrol.network;

import com.example.anas.foodpatrol.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Anas on 28/09/2017.
 */

public interface ApiInterface {
    @FormUrlEncoded
    @POST(ApiConfig.LOGIN)
    Call<ResponseModel> login(@Field("username") String username, @Field("password") String pass);

    @FormUrlEncoded
    @POST(ApiConfig.REGISTER)
    Call<ResponseModel> register(@Field("username") String username, @Field("pass") String pass);
}
