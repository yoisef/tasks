package com.example.youssef.mytasks;



import com.example.youssef.mytasks.category.category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface myinterface {

    @GET("GetCountries")
    Call<List<Countries>> getcountries();

    @GET("GetCities")
    Call<List<City>> getcities(@Query("countryId") int coid);

    @GET("GetCategories?categoryId=0&countryId=1")
    Call<List<category>> getcategories();

    @GET("GetCategories?countryId=1")
    Call<List<category>> getsubcategories(@Query("categoryId")int catid);



}
