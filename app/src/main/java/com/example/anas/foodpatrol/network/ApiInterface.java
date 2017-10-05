package com.example.anas.foodpatrol.network;

import com.example.anas.foodpatrol.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by AnasBayu on 15/08/2017.
 */

public interface ApiInterface {
    @FormUrlEncoded
    @POST(ApiConfig.LOGIN)
    Call<ResponseModel> login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST(ApiConfig.REGISTER)
    Call<ResponseModel> register(@Field("username") String username, @Field("password") String password,
                                 @Field("nama_user") String nama_user,@Field("confirm_password") String confirm_password,
                                 @Field("id_jenis_pengguna") String id_jenis_pengguna
                                );


    @POST(ApiConfig.KATEGORI)
    Call<ResponseModel> AmbilKategori ();
}
