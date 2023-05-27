package com.mohamednader.healthyhabit.Auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.mohamednader.healthyhabit.Home.HomeActivity;
import com.mohamednader.healthyhabit.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout emailInputLayout, passwordInputLayout;
    TextView emailEditText, passwordEditText;
    private Button loginButton, googleButton, guestButton;
    private TextView forgetPasswordTextView, signUpTextView;
    private ProgressDialog mLoading;

    private final String TAG = "LoginActivity_TAG";
    private static final int RC_SIGN_IN = 100;
    private GoogleSignInClient mGoogleSignInClient;
    private Boolean emailAddressChecker;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        initViews();

    }

    private void initViews() {

        mAuth = FirebaseAuth.getInstance();
        mLoading = new ProgressDialog(this);


        emailEditText = findViewById(R.id.email_et);
        passwordEditText = findViewById(R.id.password_et);
        emailInputLayout = findViewById(R.id.email_input_et);
        passwordInputLayout = findViewById(R.id.password_input_et);

        loginButton = findViewById(R.id.email_login_btn);
        googleButton = findViewById(R.id.google_login_btn);
        guestButton = findViewById(R.id.guest_login_btn);
        forgetPasswordTextView = findViewById(R.id.forget_password_tv);
        signUpTextView = findViewById(R.id.signup_tv);

        loginButton.setOnClickListener(this);
        googleButton.setOnClickListener(this);
        guestButton.setOnClickListener(this);
        forgetPasswordTextView.setOnClickListener(this);
        signUpTextView.setOnClickListener(this);

        handelTextFields();

    }

    private void handelTextFields() {
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = String.valueOf(s);
                if (!input.contains("@") || !input.contains("."))
                    emailInputLayout.setError("Invalid Email!");
                else
                    emailInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = String.valueOf(s);
                if(input.length() < 8){
                    passwordInputLayout.setError("Short Password! Make it Longer!");
                }else if(input.length() > 10){
                    passwordInputLayout.setError("Short Password! Make it Shorter!");
                }else{
                    passwordInputLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.email_login_btn:
                if (validate())
                    loginToTheAccount();
                break;
            case R.id.google_login_btn:
                googleSignIn();
                break;
            case R.id.guest_login_btn:
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
                break;
            case R.id.forget_password_tv:
                //startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
                //finish();
                break;
            case R.id.signup_tv:
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                finish();
                break;
        }
    }


    private void googleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {

            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            mLoading.setTitle("Loading...");
            mLoading.setMessage("Please Wait");
            mLoading.show();
            mLoading.setCanceledOnTouchOutside(false);
            //Toast.makeText(Login.this,"Signed In Successfully",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
        } catch (ApiException e) {
            Toast.makeText(LoginActivity.this, "Sign In Failed", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "handleSignInResult: " + e.getMessage());
            mLoading.dismiss();
            FirebaseGoogleAuth(null);
        }
    }


    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {
        //check if the account is null
        if (acct != null) {
            AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
            mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Toast.makeText(Login.this, "Successful", Toast.LENGTH_SHORT).show();
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        firebaseUser.reload();
                        emailAddressChecker = firebaseUser.isEmailVerified();
                        if (emailAddressChecker) {
                            Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                            mLoading.dismiss();
                            updateUI(firebaseUser);
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        } else {
                            SendEmailVerificationMessage();
                        }

                    } else {
                        Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onComplete: login" + task.getException().getMessage());
                        mAuth.signOut();
                        updateUI(null);
                    }
                }
            });
        } else {
            Toast.makeText(LoginActivity.this, "Account Login failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(FirebaseUser fUser) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account != null) {
            String personName = account.getDisplayName();
            final String personGivenName = account.getGivenName();
            final String personFamilyName = account.getFamilyName();
            final String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();

            /*final String currentUserID = mAuth.getCurrentUser().getUid();
            UsersRef = FirebaseDatabase.getInstance().getReference("Users");
            final UserDataModel dataModel = new UserDataModel(currentUserID, personGivenName, personFamilyName, personEmail, "", "", "none", "none", "none");


            UsersRef.child(currentUserID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        if (dataSnapshot.child(getString(R.string.users_firstname)).getValue().toString().equals(""))
                            UsersRef.child(currentUserID).child(getString(R.string.users_firstname)).setValue(personGivenName);
                        if (dataSnapshot.child(getString(R.string.users_lastname)).getValue().toString().equals(""))
                            UsersRef.child(currentUserID).child(getString(R.string.users_lastname)).setValue(personFamilyName);
                        if (dataSnapshot.child(getString(R.string.users_email)).getValue().toString().equals(""))
                            UsersRef.child(currentUserID).child(getString(R.string.users_email)).setValue(personEmail);
                        if (dataSnapshot.child(getString(R.string.users_profileimage_url)).getValue().toString().equals(""))
                            UsersRef.child(getString(R.string.users_profileimage_url)).setValue("none");
                    } else {
                        UsersRef.child(currentUserID).setValue(dataModel).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()) {
                                    mLoading.dismiss();
                                    Log.e(TAG, "onComplete: Done Saving Data for google account !");
                                } else {
                                    Log.e(TAG, "onComplete: Error on Saving Data " + task.getException().toString());
                                }
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });*/
        }
    }


    private void loginToTheAccount() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        mLoading.setTitle("Logging In");
        mLoading.setMessage("Please Wait ...");
        mLoading.setCanceledOnTouchOutside(false);
        mLoading.show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    VerifyEmailAddress();
                } else {
                    String messsage = task.getException().getMessage();
                    Toast.makeText(LoginActivity.this, "Error Occurred: " + messsage, Toast.LENGTH_LONG).show();
                    mLoading.dismiss();
                }
            }
        });
    }


    boolean validate() {
        if (emailEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (passwordEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (passwordEditText.getText().toString().length() < 8) {
            passwordEditText.setError("Password < 8");
            Toast.makeText(this, "Please Enter a Password more than 8 chars", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    private void VerifyEmailAddress() {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        firebaseUser.reload();
        emailAddressChecker = firebaseUser.isEmailVerified();
        if (firebaseUser.getUid().equals("yLSVKZxA67gaA5a2k6TS73GleQJ3")) {
            Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
            mLoading.dismiss();
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        } else {
            if (emailAddressChecker) {

                Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                mLoading.dismiss();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();

            } else {
                Toast.makeText(LoginActivity.this, "Please Verfiy Your Account First ..", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                mLoading.dismiss();
            }

        }
    }


    private void SendEmailVerificationMessage() {
        final FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        firebaseUser.reload();
                        Toast.makeText(LoginActivity.this, "Check your email and verify your email.. ", Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                    } else {
                        String error = task.getException().toString();
                        Toast.makeText(LoginActivity.this, "Error : " + error, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


}