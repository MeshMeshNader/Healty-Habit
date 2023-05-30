package com.mohamednader.healthyhabit.Models;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mohamednader.healthyhabit.Network.NetworkDelegateAPI;
import com.mohamednader.healthyhabit.Network.RemoteSource;
import com.mohamednader.healthyhabit.R;

public class Repository implements RepositoryInterface{

    Context context;
    RemoteSource remoteSource;
    private static Repository repo = null;

    //For Google Login and SignUp
    private static final int RC_SIGN_IN = 100;
    private GoogleSignInClient mGoogleSignInClient;
    private Boolean emailAddressChecker;
    private FirebaseAuth mAuth;

    private Repository(Context context, RemoteSource remoteSource) {
        this.context = context;
        this.remoteSource = remoteSource;

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(context.getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(context, gso);


    }

    public static Repository getInstance(Context context, RemoteSource remoteSource) {
        if (repo == null) {
            repo = new Repository(context, remoteSource);
        }
        return repo;
    }


    @Override
    public void startCallToGetMealsByFirstLetter(NetworkDelegateAPI networkDelegate, Character character) {
        remoteSource.startCallToGetMealsByFirstLetter(networkDelegate, character);
    }

    @Override
    public void startCallToGetRandomMeal(NetworkDelegateAPI networkDelegate) {
        remoteSource.startCallToGetRandomMeal(networkDelegate);
    }

    @Override
    public void startCallToGetMealDetailsByID(NetworkDelegateAPI networkDelegate, int id) {
        remoteSource.startCallToGetMealDetailsByID(networkDelegate, id);
    }

    @Override
    public void startCallToGetListCategoriesNames(NetworkDelegateAPI networkDelegate) {
        remoteSource.startCallToGetListCategoriesNames(networkDelegate);
    }

    @Override
    public void startCallToGetListAreasNames(NetworkDelegateAPI networkDelegate) {
        remoteSource.startCallToGetListAreasNames(networkDelegate);
    }

    @Override
    public void startCallToGetListIngredientsNames(NetworkDelegateAPI networkDelegate) {
        remoteSource.startCallToGetListIngredientsNames(networkDelegate);
    }

    @Override
    public void startCallToGetMealsByCategory(NetworkDelegateAPI networkDelegate, String category) {
        remoteSource.startCallToGetMealsByCategory(networkDelegate, category);
    }

    @Override
    public void startCallToGetMealsByArea(NetworkDelegateAPI networkDelegate, String area) {
        remoteSource.startCallToGetMealsByArea(networkDelegate,area);
    }

    @Override
    public void startCallToGetMealsByIngredient(NetworkDelegateAPI networkDelegate, String ingredient) {
        remoteSource.startCallToGetMealsByIngredient(networkDelegate, ingredient);
    }

    @Override
    public void startCallToGetListCategoriesDetails(NetworkDelegateAPI networkDelegate) {
        remoteSource.startCallToGetListCategoriesDetails(networkDelegate);
    }

//    @Override
//    public void startCallFirebaseLogin(NetworkDelegateAPI networkDelegate, String email, String password) {
////        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
////            @Override
////            public void onComplete(@NonNull Task<AuthResult> task) {
////                if (task.isSuccessful()) {
////                   // VerifyEmailAddress(networkDelegate);
////                } else {
//////                    String messsage = task.getException().getMessage();
//////                    Toast.makeText(LoginActivity.this, "Error Occurred: " + messsage, Toast.LENGTH_LONG).show();
//////                    mLoading.dismiss();
////                 }
////            }
////        });
//    }
//
//    private void VerifyEmailAddress(NetworkDelegateAPI networkDelegate) {
//        FirebaseUser firebaseUser = mAuth.getCurrentUser();
//        firebaseUser.reload();
//        emailAddressChecker = firebaseUser.isEmailVerified();
//        if (emailAddressChecker) {
////            Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
////            mLoading.dismiss();
////            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
////            finish();
////            networkDelegate.
//        } else {
////            Toast.makeText(LoginActivity.this, "Please Verfiy Your Account First ..", Toast.LENGTH_SHORT).show();
////            mAuth.signOut();
////            mLoading.dismiss();
//        }
//    }




}
