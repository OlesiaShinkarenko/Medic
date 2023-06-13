package com.example.medic;

import com.example.medic.AnalysisFragment.DiscountAndNews;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("news")
    Call<List<DiscountAndNews>> getDiscounts();
}
