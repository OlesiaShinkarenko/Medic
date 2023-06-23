package com.example.medic.common;

import com.example.medic.AnalysisFragment.CategoriesResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @POST("signup/")
    Call<RefreshAccess> SignUp(@Body User user);

    @GET("catalog/")
    Call<AnalysisResult> getAnalyses();
  @GET("catalog/")
    Call<AnalysisResult> searchAnalyses(@Query("search")String search);

    @GET("category/")
    Call<CategoriesResult> getCategories();

    @GET("news/")
    Call<NewsResult>getNews();

    @POST("signin/")
    Call<RefreshAccess> SignIn(@Body User user);
    @POST("profiles/")
    Call<CardPatient> CreateCardPatient(@Header("Authorization")String autho,@Body CardPatient cardPatient);
    @GET("profiles/")
    Call<ProfilesModelResponse> getAllCardPatientUser(@Header("Authorization")String autho);

    @GET("profiles/{id}/")
    Call<CardPatient> GetProfile(@Header("Authorization")String autho,@Path("id")Integer id);

    @PUT("profiles/{id}/")
    Call<CardPatient>UpdateProfile(@Header("Authorization")String autho,@Path("id")Integer id,@Body CardPatient cardPatient);
    @POST("token/refresh/")
    Call<RefreshAccess> getAccess(@Body Refresh refresh);

}
