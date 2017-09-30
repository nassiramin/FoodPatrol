package com.example.anas.foodpatrol.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AnasBayu on 15/08/2017.
 */

public class ApiClient {
    private static Retrofit retrofit;

    private static Retrofit buatClient(){
        retrofit = new Retrofit.Builder().baseUrl(ApiConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static ApiInterface buatRequest(){
        return buatClient().create(ApiInterface.class);
    }
}
