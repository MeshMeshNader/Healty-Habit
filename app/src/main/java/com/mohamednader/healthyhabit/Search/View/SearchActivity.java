package com.mohamednader.healthyhabit.Search.View;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mohamednader.healthyhabit.Database.ConcreteLocalSource;
import com.mohamednader.healthyhabit.Models.Repository;
import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.R;
import com.mohamednader.healthyhabit.Search.Presenter.SearchPresenter;
import com.mohamednader.healthyhabit.Search.View.SearchRequest.SearchFragmentRequest;
import com.mohamednader.healthyhabit.Search.View.SearchResponse.SearchFragmentResponse;

public class SearchActivity extends AppCompatActivity implements SearchViewInterface {


    Toolbar toolbar;
    SearchPresenter searchPresenter;
    SearchFragmentRequest searchFragmentRequest;
    SearchFragmentResponse searchFragmentResponse;
    private boolean isSearchFragmentDisplayed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        intiViews();

        if (savedInstanceState == null) {
            replaceFragment(searchFragmentRequest, "searchRequest");
            isSearchFragmentDisplayed = true;
        }


    }

    private void intiViews() {
        searchPresenter = new SearchPresenter(this,
                Repository.getInstance(this, ApiClient.getInstance(), ConcreteLocalSource.getInstance(this)));

        searchFragmentRequest = new SearchFragmentRequest(searchPresenter);

        searchPresenter.setSearchFragmentRequest(searchFragmentRequest);

        searchFragmentResponse = new SearchFragmentResponse();
        toolbar = findViewById(R.id.toolbar_search);
        setupActionBar();


    }

    private void replaceFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.search_fragment_container, fragment, tag);
        fragmentTransaction.commit();
    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Search...");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search_bar, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Here");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchPresenter.upDateList(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchPresenter.upDateList(newText);
                return true;
            }
        });

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && !isSearchFragmentDisplayed) {
                    replaceFragment(searchFragmentRequest, "searchRequest");
                    isSearchFragmentDisplayed = true;
                }
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.search:
                if (!isSearchFragmentDisplayed) {
                    replaceFragment(searchFragmentRequest, "searchRequest");
                    isSearchFragmentDisplayed = true;
                }

                break;
        }
        return true;
    }


    @Override
    public void goToSearchResult(String keyToSearch) {


        MenuItem searchMenuItem = toolbar.getMenu().findItem(R.id.search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.clearFocus();

        // Create a new instance of SearchFragmentResponse
        searchFragmentResponse = new SearchFragmentResponse();

        Bundle args = new Bundle();
        args.putString("EXTRA_SEARCH", keyToSearch);
        searchFragmentResponse.setArguments(args);

        replaceFragment(searchFragmentResponse, "searchResponse");
        isSearchFragmentDisplayed = false;


    }


}