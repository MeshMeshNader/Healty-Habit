package com.mohamednader.healthyhabit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mohamednader.healthyhabit.Auth.LoginActivity;
import com.mohamednader.healthyhabit.MainHome.MainHome;
import com.mohamednader.healthyhabit.Utils.CheckInternetConnection;
import com.mohamednader.healthyhabit.Utils.Utils;

public class Splash extends AppCompatActivity {

    CheckInternetConnection cd;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();
        cd = new CheckInternetConnection(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        splashTimer();
    }

    private void splashTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!cd.isConnected()) {
                    Toast.makeText(Splash.this, "No Internet Connection !", Toast.LENGTH_SHORT).show();
                    sendUserToHome();
                } else {
                    checkUser();
                }
            }
        }, 2000);
    }


    void checkUser() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            sendUserToLogin();
        } else {
            sendUserToHome();
        }
    }

    void sendUserToLogin() {
        startActivity(new Intent(Splash.this, LoginActivity.class));
        finish();
    }

    void sendUserToHome() {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        Utils.getSpEditor(this).putString(Utils.UserID, firebaseUser.getUid().toString());
        Utils.getSpEditor(this).putBoolean(Utils.IsLoggedOn, true);
        Utils.getSpEditor(this).commit();

        Intent intent = new Intent(Splash.this, MainHome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }


}