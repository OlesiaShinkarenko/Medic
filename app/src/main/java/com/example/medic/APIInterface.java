package com.example.medic;

import com.example.medic.AnalysisFragment.DiscountAndNews;

import java.util.List;


import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIInterface {
    @GET("news")
    Call<List<DiscountAndNews>> getDiscounts();

    @POST("sendCode")
    Call<String> sendEmail(@Header("email") String email);
}
