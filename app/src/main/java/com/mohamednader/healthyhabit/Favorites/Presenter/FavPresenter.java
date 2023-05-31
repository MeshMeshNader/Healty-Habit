package com.mohamednader.healthyhabit.Favorites.Presenter;

import com.mohamednader.healthyhabit.Favorites.View.FavViewInterface;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.RepositoryInterface;

public class FavPresenter implements FavPresenterInterface {


    FavViewInterface viewInterface;
    RepositoryInterface repositoryInterface;

    public FavPresenter(FavViewInterface viewInterface, RepositoryInterface repositoryInterface) {
        this.viewInterface = viewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void getStoredMeals() {
        viewInterface.showAllStoredMeals( repositoryInterface.getStoredMeals() );
    }

    @Override
    public void deleteMealFromFav(Meal meal) {

        repositoryInterface.deleteMeal(meal);
        viewInterface.onDeletedFromFavSuccessfully();
    }

}
