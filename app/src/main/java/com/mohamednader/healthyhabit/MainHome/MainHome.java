package com.mohamednader.healthyhabit.MainHome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.mohamednader.healthyhabit.Area.View.AreaActivity;
import com.mohamednader.healthyhabit.Area.View.AreaFragment;
import com.mohamednader.healthyhabit.Area.View.AreaHomeFragment;
import com.mohamednader.healthyhabit.Category.View.CategoryFragment;
import com.mohamednader.healthyhabit.Category.View.CategoryHomeFragment;
import com.mohamednader.healthyhabit.Database.ConcreteLocalSource;
import com.mohamednader.healthyhabit.Favorites.View.FavFragment;
import com.mohamednader.healthyhabit.Home.Presenter.HomePresenter;
import com.mohamednader.healthyhabit.Home.View.HomeFragment;
import com.mohamednader.healthyhabit.Home.View.HomeViewInterface;
import com.mohamednader.healthyhabit.Models.CategoriesModels.Category;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.Repository;
import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainHome extends AppCompatActivity implements HomeViewInterface {

    FloatingActionButton fab;

    DrawerLayout drawerLayout;

    BottomNavigationView bottomNavigationView;

    List<Category> categories;
    List<Meal> areas;
    private HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        categories = new ArrayList<>();
        areas = new ArrayList<>();
        homePresenter = new HomePresenter(this,
                Repository.getInstance(this, ApiClient.getInstance(), ConcreteLocalSource.getInstance(this)));

        homePresenter.getListCategoriesDetails();
        homePresenter.getListAreasNames();


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        replaceFragment(new HomeFragment());

        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.shorts:

                    CategoryHomeFragment categoryHomeFragment = new CategoryHomeFragment();
                    Bundle argsCategory = new Bundle();
                    argsCategory.putSerializable("category", (Serializable) categories);
                    categoryHomeFragment.setArguments(argsCategory);
                    replaceFragment(categoryHomeFragment);

                    break;
                case R.id.subscriptions:

                    AreaHomeFragment areaHomeFragment = new AreaHomeFragment();
                    Bundle argsArea = new Bundle();
                    argsArea.putSerializable("meal", (Serializable) areas);
                    areaHomeFragment.setArguments(argsArea);
                    replaceFragment(areaHomeFragment);

                    break;
                case R.id.library:

                    replaceFragment(new FavFragment());

                    break;
            }

            return true;
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new CategoryFragment());
            }
        });

    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void showMealsByLetterFilter(List<Meal> list) {

    }

    @Override
    public void showRandomMeal(List<Meal> list) {

    }

    @Override
    public void showListAreasNames(List<Meal> list) {
        areas = list;
    }

    @Override
    public void showListCategoriesDetails(List<Category> list) {
        categories = list;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onAddedToFavSuccessfully() {

    }
}