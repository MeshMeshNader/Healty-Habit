package com.mohamednader.healthyhabit.Network;

import com.mohamednader.healthyhabit.Models.MealsModels.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search.php")
    public Call<Meals> getMealsByFirstLetter(@Query("f") char letter);

}
