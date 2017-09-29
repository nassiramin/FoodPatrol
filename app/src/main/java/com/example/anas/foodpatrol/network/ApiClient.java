package com.example.anas.foodpatrol.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Anas on 28/09/2017.
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

