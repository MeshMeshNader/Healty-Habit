package com.mohamednader.healthyhabit.Home.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.mohamednader.healthyhabit.Adapters.AreasAdapter;
import com.mohamednader.healthyhabit.Adapters.CategoriesAdapter;
import com.mohamednader.healthyhabit.Adapters.OnAreaClickListener;
import com.mohamednader.healthyhabit.Adapters.OnCategoryClickListener;
import com.mohamednader.healthyhabit.Adapters.OnMealClickListener;
import com.mohamednader.healthyhabit.Adapters.SwipeMealAdapter;
import com.mohamednader.healthyhabit.Area.View.AreaActivity;
import com.mohamednader.healthyhabit.Auth.LoginActivity;
import com.mohamednader.healthyhabit.Category.View.CategoryActivity;
import com.mohamednader.healthyhabit.Database.ConcreteLocalSource;
import com.mohamednader.healthyhabit.Home.Presenter.HomePresenter;
import com.mohamednader.healthyhabit.MealDetails.View.MealDetailsActivity;
import com.mohamednader.healthyhabit.Models.CategoriesModels.Category;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.Repository;
import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.Planning.View.WeekActivity;
import com.mohamednader.healthyhabit.R;
import com.mohamednader.healthyhabit.Utils.CheckInternetConnection;
import com.mohamednader.healthyhabit.Utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;

public class HomeFragment extends Fragment implements HomeViewInterface, OnAreaClickListener, OnCategoryClickListener, OnMealClickListener, SwipeStack.SwipeStackListener {

    public static final String EXTRA_MEAL_ID = "mealID";
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_AREA = "area";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";
    private final String TAG = "HomeFragment_TAG";
    CheckInternetConnection cd;
    TextView noInternet, cat, ins, planning;
    private RecyclerView recyclerViewAreas;
    private RecyclerView recyclerViewCategories;
    private AreasAdapter areasAdapter;
    private CardView planningCard;
    private CategoriesAdapter categoriesAdapter;
    private SwipeMealAdapter swipeMealAdapter;
    private SwipeStack swipeStackView;
    private List<Meal> stackMeals;
    private int stackCounter = 0;
    private HomePresenter homePresenter;
    private Meal meal;
    private View view;
    private ImageView logoutImg;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        Log.i(TAG, "onCreateView: ");

        initViews();
        recyclerViewConfig();
        cd = new CheckInternetConnection(getActivity());

        homePresenter = new HomePresenter(this, Repository.getInstance(getActivity(), ApiClient.getInstance(), ConcreteLocalSource.getInstance(getActivity())));

        if (!cd.isConnected()) {
            hideViews();
        } else {
            getData();
            showViews();
        }

        if (!(Utils.getSp(getActivity()).getBoolean(Utils.IsLoggedOn, false))) {
            planning.setText("Sign in to Use Planner");
            planningCard.setClickable(false);
        } else {
            planning.setText("Plan Your Days!");
            planningCard.setClickable(true);
        }


        return view;
    }

    private void getData() {
        homePresenter.getListAreasNames();
        homePresenter.getListCategoriesDetails();
        for (int i = 0; i < 10; i++) {
            homePresenter.getRandomMeal();
            stackCounter++;
        }
    }

    private void showViews() {
        noInternet.setVisibility(View.GONE);
        view.findViewById(R.id.shimmerArea).setVisibility(View.VISIBLE);
        view.findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
        cat.setVisibility(View.VISIBLE);
        ins.setVisibility(View.VISIBLE);
        swipeStackView.setVisibility(View.VISIBLE);
        recyclerViewAreas.setVisibility(View.VISIBLE);
        recyclerViewCategories.setVisibility(View.VISIBLE);
    }

    private void hideViews() {
        noInternet.setVisibility(View.VISIBLE);
        view.findViewById(R.id.shimmerArea).setVisibility(View.GONE);
        view.findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
        cat.setVisibility(View.GONE);
        ins.setVisibility(View.GONE);
        swipeStackView.setVisibility(View.GONE);
        recyclerViewAreas.setVisibility(View.GONE);
        recyclerViewCategories.setVisibility(View.GONE);
    }


    private void initViews() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        mAuth = FirebaseAuth.getInstance();

        noInternet = view.findViewById(R.id.no_internet_text_view);
        cat = view.findViewById(R.id.titleCategory);
        ins = view.findViewById(R.id.meal_for_inspiration);
        planning = view.findViewById(R.id.planning_card_text);
        swipeStackView = view.findViewById(R.id.swipe_stack_view);
        swipeStackView.setListener(this);
        stackMeals = new ArrayList<>();
        recyclerViewAreas = view.findViewById(R.id.viewPagerHeader);
        recyclerViewCategories = view.findViewById(R.id.recyclerCategory);
        planningCard = view.findViewById(R.id.cardSearch);
        logoutImg = view.findViewById(R.id.img_log_out);
        planningCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WeekActivity.class);
                startActivity(intent);
            }
        });

        logoutImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(Utils.getSp(getActivity()).getBoolean(Utils.IsLoggedOn, false))) {
                    new AlertDialog.Builder(getActivity()).setMessage("Do You Want To SignUP ? ").setCancelable(false)
                            .setPositiveButton("Yes, Lets SignUp", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    logout();
                                }
                            }).setNegativeButton("No, Stay Here", null).show();
                } else {
                    new AlertDialog.Builder(getActivity()).setMessage("Do You Really Want To SignOut? ").setCancelable(false).setPositiveButton("Yes, SignOut", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            logout();
                        }
                    }).setNegativeButton("No, Stay Here", null).show();
                }


            }
        });
    }

    private void recyclerViewConfig() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewAreas.setHasFixedSize(true);
        recyclerViewAreas.setLayoutManager(linearLayoutManager);
        areasAdapter = new AreasAdapter(getActivity(), new ArrayList<>(), this);
        recyclerViewAreas.setAdapter(areasAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        recyclerViewCategories.setLayoutManager(layoutManager);
        recyclerViewCategories.setNestedScrollingEnabled(true);
        categoriesAdapter = new CategoriesAdapter(getActivity(), new ArrayList<>(), this);
        recyclerViewCategories.setAdapter(categoriesAdapter);

        swipeMealAdapter = new SwipeMealAdapter(getActivity(), new ArrayList<>(), this);
    }

    private void logout() {
        mGoogleSignInClient.signOut();
        mAuth.signOut();

        Utils.getSpEditor(getActivity()).putString(Utils.UserID, "");
        Utils.getSpEditor(getActivity()).putBoolean(Utils.IsLoggedOn, false);
        Utils.getSpEditor(getActivity()).commit();

        Intent userLogout = new Intent(getActivity(), LoginActivity.class);
        userLogout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        userLogout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(userLogout);
        getActivity().finish();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Add any further view setup or initialization logic here
    }

    @Override
    public void showMealsByLetterFilter(List<Meal> list) {
        for (int i = 0; i < list.size(); i++)
            Log.i(TAG, "showMealsByLetterFilter: " + list.get(i).getStrMeal());
    }

    @Override
    public void showRandomMeal(List<Meal> list) {
        stackMeals.add(list.get(0));
        Log.i(TAG, "showRandomMeal: " + stackCounter);
        if (stackCounter == 10) {
            swipeMealAdapter.setList(stackMeals);
            swipeStackView.setAdapter(swipeMealAdapter);
        }
    }

    @Override
    public void showListAreasNames(List<Meal> list) {
        for (int i = 0; i < list.size(); i++)
            Log.i(TAG, "showListAreasNames: " + list.get(i).getStrArea());
        areasAdapter.setList(list);
        areasAdapter.notifyDataSetChanged();
    }

    @Override
    public void showListCategoriesDetails(List<Category> list) {
        categoriesAdapter.setList(list);
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        view.findViewById(R.id.shimmerArea).setVisibility(View.VISIBLE);
        view.findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        view.findViewById(R.id.shimmerArea).setVisibility(View.GONE);
        view.findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
    }

    @Override
    public void onAreaClick(List<Meal> list, int pos) {
        Intent intent = new Intent(getActivity(), AreaActivity.class);
        intent.putExtra(EXTRA_AREA, (Serializable) list);
        intent.putExtra(EXTRA_POSITION, pos);
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(List<Category> list, int pos) {
        Intent intent = new Intent(getActivity(), CategoryActivity.class);
        intent.putExtra(EXTRA_CATEGORY, (Serializable) list);
        intent.putExtra(EXTRA_POSITION, pos);
        startActivity(intent);
    }

    @Override
    public void onMealClick(int mealID) {
        Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
        intent.putExtra(EXTRA_MEAL_ID, mealID);
        startActivity(intent);
    }

    @Override
    public void onDeleteMealClick(Meal meal) {
        // Handle delete meal click event here
    }

    @Override
    public void addToFavMeal(Meal meal) {
        this.meal = meal;
        meal.setStrIngredient20(Utils.getSp(getActivity())
                .getString(Utils.UserID, ""));
        homePresenter.addMealToFav(meal);
    }

    @Override
    public void onFavMealClick(Meal meal) {
        homePresenter.getMealDetailsByID(Integer.parseInt(meal.getIdMeal()));

    }

    @Override
    public void onAddedToFavSuccessfully() {
        Toast.makeText(getActivity(), meal.getStrMeal() + " Added To Fav! ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewSwipedToLeft(int position) {
        // Handle view swiped to left event here
    }

    @Override
    public void onViewSwipedToRight(int position) {
        // Handle view swiped to right event here
    }

    @Override
    public void onStackEmpty() {
        swipeStackView.resetStack();
    }
}
