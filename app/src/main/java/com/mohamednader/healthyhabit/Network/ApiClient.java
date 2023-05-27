package com.mohamednader.healthyhabit.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mohamednader.healthyhabit.Models.MealsModels.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient implements RemoteSource{

    ApiService apiService;
    private static ApiClient apiClient = null;
    private static final String BASE_URL = " www.themealdb.com/api/json/v1/1/";

    private ApiClient() {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static synchronized ApiClient getInstance(){
        if (apiClient == null){
            apiClient = new ApiClient();
        }
        return apiClient;
    }

    @Override
    public void startCall(NetworkDelegate networkDelegate) {
        Callback<Meals> responseCallBack = new Callback<Meals>(){

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful()){
                    networkDelegate.onSuccessResponse();
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());
            }
        };
        Call<Meals> mealsByFirstLetter = apiService.getMealsByFirstLetter('a');

        mealsByFirstLetter.enqueue(responseCallBack);
    }

}
