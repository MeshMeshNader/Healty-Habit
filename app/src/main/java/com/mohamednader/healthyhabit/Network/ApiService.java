package com.mohamednader.healthyhabit.Network;

import com.mohamednader.healthyhabit.Models.CategoriesModels.Categories;
import com.mohamednader.healthyhabit.Models.MealsModels.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search.php")
    public Call<Meals> getMealsByFirstLetter(@Query("f") char letter);

    @GET("lookup.php")
    public Call<Meals> getMealDetailsByID(@Query("i") int id);

    @GET("random.php")
    public Call<Meals> getRandomMeal();

    @GET("list.php")
    public Call<Meals> getListCategoriesNames(@Query("c") String listWord);

    @GET("list.php")
    public Call<Meals> getListAreasNames(@Query("a") String listWord);

    @GET("list.php")
    public Call<Meals> getListIngredientsNames(@Query("i") String listWord);

    @GET("filter.php")
    public Call<Meals> getMealsByCategory(@Query("c") String category);

    @GET("filter.php")
    public Call<Meals> getMealsByArea(@Query("a") String area);

    @GET("filter.php")
    public Call<Meals> getMealsByIngredient(@Query("i") String ingredient);

    @GET("categories.php")
    public Call<Categories> getListCategoriesDetails();


}
