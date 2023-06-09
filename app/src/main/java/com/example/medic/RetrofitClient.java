package com.example.medic;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private  static final String BASE_URL = "https://virtserver.swaggerhub.com/serk87/APIfood/FRBHWRIOJAFIDSNKJF/api/";
    private static Retrofit retrofit = null;

    public static  APIInterface getRetrofitClient(){
        if ( retrofit ==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(APIInterface.class);
    }

}
