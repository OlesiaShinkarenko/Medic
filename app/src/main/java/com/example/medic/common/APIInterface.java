package com.example.medic.common;

import com.example.medic.AnalysisFragment.CategoriesResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {
    @POST("signup/")
    Call<Refresh> SignUp(@Body User user);

    @GET("catalog/")
    Call<AnalysisResult> getAnalyses();

    @GET("category/")
    Call<CategoriesResult> getCategories();

    @GET("news/")
    Call<NewsResult>getNews();

    @POST("signin/")
    Call<Refresh> SignIn(@Body User user);

    @POST("profiles/")
    Call<CardPatient> CreateCardPatient(@Body CardPatient cardPatient, @Header("Authorization")String auto);

    @GET("profiles/{id}")
    Call<CardPatient> GetProfile(@Path("id")Integer id);
}
