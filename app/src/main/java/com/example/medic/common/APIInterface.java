package com.example.medic.common;

import com.example.medic.AnalysisFragment.CategoriesResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {
    @POST("signup/")
    Call<User> SignUp(@Body User user);

    @GET("catalog/")
    Call<AnalysisResult> getAnalyses();

    @GET("category/")
    Call<CategoriesResult> getCategories();

    @GET("news/")
    Call<NewsResult>getNews();
}
