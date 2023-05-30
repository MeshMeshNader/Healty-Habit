package com.mohamednader.healthyhabit.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mohamednader.healthyhabit.Models.CategoriesModels.Categories;
import com.mohamednader.healthyhabit.Models.MealsModels.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient implements RemoteSource{

    ApiService apiService;
    private static ApiClient apiClient = null;
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

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
    public void startCallToGetMealsByFirstLetter(NetworkDelegateAPI networkDelegate, Character character) {
        Callback<Meals> responseCallBack = new Callback<Meals>(){

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful()){
                    networkDelegate.onSuccessResponseMeal(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());
            }
        };
        Call<Meals> request = apiService.getMealsByFirstLetter(character);

        request.enqueue(responseCallBack);
    }

    @Override
    public void startCallToGetRandomMeal(NetworkDelegateAPI networkDelegate) {
        Callback<Meals> responseCallBack = new Callback<Meals>(){

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful()){
                    networkDelegate.onSuccessResponseMeal(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());
            }
        };
        Call<Meals> request = apiService.getRandomMeal();

        request.enqueue(responseCallBack);
    }

    @Override
    public void startCallToGetMealDetailsByID(NetworkDelegateAPI networkDelegate, int id) {
        Callback<Meals> responseCallBack = new Callback<Meals>(){

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful()){
                    networkDelegate.onSuccessResponseMeal(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());
            }
        };
        Call<Meals> request = apiService.getMealDetailsByID(id);

        request.enqueue(responseCallBack);
    }

    @Override
    public void startCallToGetListCategoriesNames(NetworkDelegateAPI networkDelegate) {
        Callback<Meals> responseCallBack = new Callback<Meals>(){

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful()){
                    networkDelegate.onSuccessResponseMeal(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());
            }
        };
        Call<Meals> request = apiService.getListCategoriesNames("list");

        request.enqueue(responseCallBack);
    }

    @Override
    public void startCallToGetListAreasNames(NetworkDelegateAPI networkDelegate ) {
        Callback<Meals> responseCallBack = new Callback<Meals>(){

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful()){
                    networkDelegate.onSuccessResponseMeal(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());
            }
        };
        Call<Meals> request = apiService.getListAreasNames("list");

        request.enqueue(responseCallBack);
    }

    @Override
    public void startCallToGetListIngredientsNames(NetworkDelegateAPI networkDelegate ) {
        Callback<Meals> responseCallBack = new Callback<Meals>(){

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful()){
                    networkDelegate.onSuccessResponseMeal(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());
            }
        };
        Call<Meals> request = apiService.getListIngredientsNames("list");

        request.enqueue(responseCallBack);
    }

    @Override
    public void startCallToGetMealsByCategory(NetworkDelegateAPI networkDelegate, String category) {
        Callback<Meals> responseCallBack = new Callback<Meals>(){

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful()){
                    networkDelegate.onSuccessResponseMeal(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());
            }
        };
        Call<Meals> request = apiService.getMealsByCategory(category);

        request.enqueue(responseCallBack);
    }

    @Override
    public void startCallToGetMealsByArea(NetworkDelegateAPI networkDelegate, String area) {
        Callback<Meals> responseCallBack = new Callback<Meals>(){

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful()){
                    networkDelegate.onSuccessResponseMeal(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());
            }
        };
        Call<Meals> request = apiService.getMealsByArea(area);

        request.enqueue(responseCallBack);
    }

    @Override
    public void startCallToGetMealsByIngredient(NetworkDelegateAPI networkDelegate, String ingredient) {
        Callback<Meals> responseCallBack = new Callback<Meals>(){

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful()){
                    networkDelegate.onSuccessResponseMeal(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());
            }
        };
        Call<Meals> request = apiService.getMealsByIngredient(ingredient);

        request.enqueue(responseCallBack);
    }

    @Override
    public void startCallToGetListCategoriesDetails(NetworkDelegateAPI networkDelegate) {
        Callback<Categories> responseCallBack = new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if (response.isSuccessful()){
                    networkDelegate.onSuccessResponseCategory(response.body().getCategories());
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                networkDelegate.onFailureResponse(t.getMessage());
            }
        };

        Call<Categories> request = apiService.getListCategoriesDetails();

        request.enqueue(responseCallBack);

    }


}
