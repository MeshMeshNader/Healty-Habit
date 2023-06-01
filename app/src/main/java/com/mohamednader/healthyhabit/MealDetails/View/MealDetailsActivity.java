package com.mohamednader.healthyhabit.MealDetails.View;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.mohamednader.healthyhabit.Adapters.IngredientsAdapter;
import com.mohamednader.healthyhabit.Category.View.CategoryFragment;
import com.mohamednader.healthyhabit.Database.ConcreteLocalSource;
import com.mohamednader.healthyhabit.MealDetails.Presenter.MealDetailsPresenter;
import com.mohamednader.healthyhabit.Models.MealsModels.Ingredient;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.Repository;
import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.R;
import com.mohamednader.healthyhabit.Utils.Utils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class MealDetailsActivity extends AppCompatActivity implements MealDetailsViewInterface {

    Toolbar toolbar;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView mealThumb;
    TextView category, country, instructions;
    ProgressBar progressBar;
    Meal meal;
    ArrayList<Ingredient> ingredientArrayList;
    IngredientsAdapter ingredientsAdapter;

    MealDetailsPresenter mealDetailsPresenter;
    RecyclerView recyclerView;


    private YouTubePlayerView youtubePlayerView;
    private String videoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);

        initViews();
        initIntent();
        setupActionBar();
    }

    private void initViews() {


        toolbar = findViewById(R.id.toolbar);
        appBarLayout = findViewById(R.id.appbar_meal_details);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        mealThumb = findViewById(R.id.mealThumb);
        category = findViewById(R.id.category);
        country = findViewById(R.id.country);
        instructions = findViewById(R.id.instructions);
        progressBar = findViewById(R.id.progressBar);
        youtubePlayerView = findViewById(R.id.youtube_player_view);
        ingredientArrayList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ingredientsAdapter = new IngredientsAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(ingredientsAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        youtubePlayerView.release();
    }

    private void initIntent() {

        Intent intent = getIntent();
        int meal_id = intent.getIntExtra(CategoryFragment.EXTRA_MEAL_ID, 0);

        mealDetailsPresenter = new MealDetailsPresenter(this,
                Repository.getInstance(this, ApiClient.getInstance(), ConcreteLocalSource.getInstance(this)));

        mealDetailsPresenter.getMealDetailsByID(meal_id);

    }


    private void setupActionBar() {
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.white));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.primary_color));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.white));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    void setupColorActionBarIcon(Drawable favoriteItemColor) {
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if ((collapsingToolbarLayout.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsingToolbarLayout))) {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.primary_color), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.primary_color), PorterDuff.Mode.SRC_ATOP);

            } else {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        MenuItem favoriteItem = menu.findItem(R.id.favorite);

        if (!(Utils.getSp(this).getBoolean(Utils.IsLoggedOn, false))) {
            favoriteItem.setVisible(false);
        } else {
            favoriteItem.setVisible(true);
        }

        Drawable favoriteItemColor = favoriteItem.getIcon();
        setupColorActionBarIcon(favoriteItemColor);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.favorite:
                meal.setStrIngredient20(Utils.getSp(this)
                        .getString(Utils.UserID, ""));
                mealDetailsPresenter.addMealToFav(meal);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void showMealDetailsByID(List<Meal> list) {
        meal = list.get(0);
        Glide.with(this)
                .load(meal.getStrMealThumb())
                .into(mealThumb);

        collapsingToolbarLayout.setTitle(meal.getStrMeal());
        category.setText(meal.getStrCategory());
        country.setText(meal.getStrArea());
        instructions.setText(meal.getStrInstructions());
        setupActionBar();

        String imgURLBase = "https://www.themealdb.com/images/ingredients/";
        String imgURLEnd = "-Small.png";

        if (meal.getStrIngredient1() != null && !meal.getStrIngredient1().isEmpty()) {
            ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(0).setTitle(meal.getStrIngredient1());
            ingredientArrayList.get(0).setPhoto(imgURLBase + meal.getStrIngredient1() + imgURLEnd);
        }


        if (meal.getStrIngredient2() != null && !meal.getStrIngredient2().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(1).setTitle(meal.getStrIngredient2());
            ingredientArrayList.get(1).setPhoto(imgURLBase + meal.getStrIngredient2() + imgURLEnd);
        }

        if (meal.getStrIngredient3() != null && !meal.getStrIngredient3().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(2).setTitle(meal.getStrIngredient3());
            ingredientArrayList.get(2).setPhoto(imgURLBase + meal.getStrIngredient3() + imgURLEnd);
        }


        if (meal.getStrIngredient4() != null && !meal.getStrIngredient4().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(3).setTitle(meal.getStrIngredient4());
            ingredientArrayList.get(3).setPhoto(imgURLBase + meal.getStrIngredient4() + imgURLEnd);
        }

        if (meal.getStrIngredient5() != null && !meal.getStrIngredient5().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(4).setTitle(meal.getStrIngredient5());
            ingredientArrayList.get(4).setPhoto(imgURLBase + meal.getStrIngredient5() + imgURLEnd);
        }

        if (meal.getStrIngredient6() != null && !meal.getStrIngredient6().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(5).setTitle(meal.getStrIngredient6());
            ingredientArrayList.get(5).setPhoto(imgURLBase + meal.getStrIngredient6() + imgURLEnd);
        }


        if (meal.getStrIngredient7() != null && !meal.getStrIngredient7().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(6).setTitle(meal.getStrIngredient7());
            ingredientArrayList.get(6).setPhoto(imgURLBase + meal.getStrIngredient7() + imgURLEnd);
        }


        if (meal.getStrIngredient8() != null && !meal.getStrIngredient8().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(7).setTitle(meal.getStrIngredient8());
            ingredientArrayList.get(7).setPhoto(imgURLBase + meal.getStrIngredient8() + imgURLEnd);
        }


        if (meal.getStrIngredient9() != null && !meal.getStrIngredient9().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(8).setTitle(meal.getStrIngredient9());
            ingredientArrayList.get(8).setPhoto(imgURLBase + meal.getStrIngredient9() + imgURLEnd);
        }


        if (meal.getStrIngredient10() != null && !meal.getStrIngredient10().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(9).setTitle(meal.getStrIngredient10());
            ingredientArrayList.get(9).setPhoto(imgURLBase + meal.getStrIngredient10() + imgURLEnd);
        }


        if (meal.getStrIngredient11() != null && !meal.getStrIngredient11().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(10).setTitle(meal.getStrIngredient11());
            ingredientArrayList.get(10).setPhoto(imgURLBase + meal.getStrIngredient11() + imgURLEnd);
        }


        if (meal.getStrIngredient12() != null && !meal.getStrIngredient12().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(11).setTitle(meal.getStrIngredient12());
            ingredientArrayList.get(11).setPhoto(imgURLBase + meal.getStrIngredient12() + imgURLEnd);
        }


        if (meal.getStrIngredient13() != null && !meal.getStrIngredient13().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(12).setTitle(meal.getStrIngredient13());
            ingredientArrayList.get(12).setPhoto(imgURLBase + meal.getStrIngredient13() + imgURLEnd);
        }


        if (meal.getStrIngredient14() != null && !meal.getStrIngredient14().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(13).setTitle(meal.getStrIngredient14());
            ingredientArrayList.get(13).setPhoto(imgURLBase + meal.getStrIngredient14() + imgURLEnd);
        }


        if (meal.getStrIngredient15() != null && !meal.getStrIngredient15().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(14).setTitle(meal.getStrIngredient15());
            ingredientArrayList.get(14).setPhoto(imgURLBase + meal.getStrIngredient15() + imgURLEnd);
        }


        if (meal.getStrIngredient16() != null && !meal.getStrIngredient16().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(15).setTitle(meal.getStrIngredient16());
            ingredientArrayList.get(15).setPhoto(imgURLBase + meal.getStrIngredient16() + imgURLEnd);
        }


        if (meal.getStrIngredient17() != null && !meal.getStrIngredient17().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(16).setTitle(meal.getStrIngredient17());
            ingredientArrayList.get(16).setPhoto(imgURLBase + meal.getStrIngredient17() + imgURLEnd);
        }


        if (meal.getStrIngredient18() != null && !meal.getStrIngredient18().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(17).setTitle(meal.getStrIngredient18());
            ingredientArrayList.get(17).setPhoto(imgURLBase + meal.getStrIngredient18() + imgURLEnd);
        }


        if (meal.getStrIngredient19() != null && !meal.getStrIngredient19().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(18).setTitle(meal.getStrIngredient19());
            ingredientArrayList.get(18).setPhoto(imgURLBase + meal.getStrIngredient19() + imgURLEnd);
        }


        if (meal.getStrIngredient20() != null && !meal.getStrIngredient20().isEmpty()) {
             ingredientArrayList.add(new Ingredient());
            ingredientArrayList.get(19).setTitle(meal.getStrIngredient20());
            ingredientArrayList.get(19).setPhoto(imgURLBase + meal.getStrIngredient20() + imgURLEnd);
        }



        if (meal.getStrMeasure1() != null && !meal.getStrMeasure1().isEmpty() && !Character.isWhitespace(meal.getStrMeasure1().charAt(0))) {
             ingredientArrayList.get(0).setSubtitle(meal.getStrMeasure1());
        }
        if (meal.getStrMeasure2() != null && !meal.getStrMeasure2().isEmpty() && !Character.isWhitespace(meal.getStrMeasure2().charAt(0))) {
             ingredientArrayList.get(1).setSubtitle(meal.getStrMeasure2());
        }
        if (meal.getStrMeasure3() != null && !meal.getStrMeasure3().isEmpty() && !Character.isWhitespace(meal.getStrMeasure3().charAt(0))) {
             ingredientArrayList.get(2).setSubtitle(meal.getStrMeasure3());
        }
        if (meal.getStrMeasure4() != null && !meal.getStrMeasure4().isEmpty() && !Character.isWhitespace(meal.getStrMeasure4().charAt(0))) {
             ingredientArrayList.get(3).setSubtitle(meal.getStrMeasure4());
        }
        if (meal.getStrMeasure5() != null && !meal.getStrMeasure5().isEmpty() && !Character.isWhitespace(meal.getStrMeasure5().charAt(0))) {
             ingredientArrayList.get(4).setSubtitle(meal.getStrMeasure5());
        }
        if (meal.getStrMeasure6() != null && !meal.getStrMeasure6().isEmpty() && !Character.isWhitespace(meal.getStrMeasure6().charAt(0))) {
             ingredientArrayList.get(5).setSubtitle(meal.getStrMeasure6());
        }

        if (meal.getStrMeasure7() != null && !meal.getStrMeasure7().isEmpty() && !Character.isWhitespace(meal.getStrMeasure7().charAt(0))) {
             ingredientArrayList.get(6).setSubtitle(meal.getStrMeasure7());
        }
        if (meal.getStrMeasure8() != null && !meal.getStrMeasure8().isEmpty() && !Character.isWhitespace(meal.getStrMeasure8().charAt(0))) {
             ingredientArrayList.get(7).setSubtitle(meal.getStrMeasure8());
        }
        if (meal.getStrMeasure9() != null && !meal.getStrMeasure9().isEmpty() && !Character.isWhitespace(meal.getStrMeasure9().charAt(0))) {
             ingredientArrayList.get(8).setSubtitle(meal.getStrMeasure9());
        }
        if (meal.getStrMeasure10() != null && !meal.getStrMeasure10().isEmpty() && !Character.isWhitespace(meal.getStrMeasure10().charAt(0))) {
             ingredientArrayList.get(9).setSubtitle(meal.getStrMeasure10());
        }
        if (meal.getStrMeasure11() != null && !meal.getStrMeasure11().isEmpty() && !Character.isWhitespace(meal.getStrMeasure11().charAt(0))) {
             ingredientArrayList.get(10).setSubtitle(meal.getStrMeasure11());
        }
        if (meal.getStrMeasure12() != null && !meal.getStrMeasure12().isEmpty() && !Character.isWhitespace(meal.getStrMeasure12().charAt(0))) {
             ingredientArrayList.get(11).setSubtitle(meal.getStrMeasure12());
        }
        if (meal.getStrMeasure13() != null && !meal.getStrMeasure13().isEmpty() && !Character.isWhitespace(meal.getStrMeasure13().charAt(0))) {
             ingredientArrayList.get(12).setSubtitle(meal.getStrMeasure13());
        }
        if (meal.getStrMeasure14() != null && !meal.getStrMeasure14().isEmpty() && !Character.isWhitespace(meal.getStrMeasure14().charAt(0))) {
             ingredientArrayList.get(13).setSubtitle(meal.getStrMeasure14());
        }
        if (meal.getStrMeasure15() != null && !meal.getStrMeasure15().isEmpty() && !Character.isWhitespace(meal.getStrMeasure15().charAt(0))) {
             ingredientArrayList.get(14).setSubtitle(meal.getStrMeasure15());
        }
        if (meal.getStrMeasure16() != null && !meal.getStrMeasure16().isEmpty() && !Character.isWhitespace(meal.getStrMeasure16().charAt(0))) {
             ingredientArrayList.get(15).setSubtitle(meal.getStrMeasure16());
        }
        if (meal.getStrMeasure17() != null && !meal.getStrMeasure17().isEmpty() && !Character.isWhitespace(meal.getStrMeasure17().charAt(0))) {
             ingredientArrayList.get(16).setSubtitle(meal.getStrMeasure17());
        }
        if (meal.getStrMeasure18() != null && !meal.getStrMeasure18().isEmpty() && !Character.isWhitespace(meal.getStrMeasure18().charAt(0))) {
             ingredientArrayList.get(17).setSubtitle(meal.getStrMeasure18());
        }
        if (meal.getStrMeasure19() != null && !meal.getStrMeasure19().isEmpty() && !Character.isWhitespace(meal.getStrMeasure19().charAt(0))) {
             ingredientArrayList.get(18).setSubtitle(meal.getStrMeasure19());
        }
        if (meal.getStrMeasure20() != null && !meal.getStrMeasure20().isEmpty() && !Character.isWhitespace(meal.getStrMeasure20().charAt(0))) {
             ingredientArrayList.get(19).setSubtitle(meal.getStrMeasure20());
        }

        ingredientsAdapter.setList(ingredientArrayList);
        ingredientsAdapter.notifyDataSetChanged();



        youtubePlayerView.setVisibility(View.VISIBLE);
        Uri uri = Uri.parse(meal.getStrYoutube());
        videoId = uri.getQueryParameter("v");
        if (videoId != null) {

            getLifecycle().addObserver(youtubePlayerView);
            youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(YouTubePlayer youTubePlayer) {
                    youTubePlayer.cueVideo(videoId, 0);
                }
            });
        } else {
            youtubePlayerView.setVisibility(View.GONE);

        }


    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onAddedToFavSuccessfully() {
        Toast.makeText(this, meal.getStrMeal() + " Added To Fav! ", Toast.LENGTH_SHORT).show();
    }

}