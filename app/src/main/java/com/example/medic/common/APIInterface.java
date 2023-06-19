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
    Call<RefreshAccess> SignUp(@Body User user);

    @GET("catalog/")
    Call<AnalysisResult> getAnalyses();

    @GET("category/")
    Call<CategoriesResult> getCategories();

    @GET("news/")
    Call<NewsResult>getNews();

    @POST("signin/")
    Call<RefreshAccess> SignIn(@Body User user);
    @POST("profiles/")
    Call<CardPatient> CreateCardPatient(@Header("Authorization")String autho,@Body CardPatient cardPatient);

    @GET("profiles/{id}")
    Call<CardPatient> GetProfile(@Header("Authorization")String autho,@Path("id")Integer id);
    @POST("token/refresh/")
    Call<RefreshAccess> getAccess(@Body Refresh refresh);

}
