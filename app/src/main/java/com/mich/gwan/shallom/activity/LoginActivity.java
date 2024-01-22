/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 1 / 22
 */

package com.mich.gwan.shallom.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.dao.DatabaseHelper;
import com.mich.gwan.shallom.databinding.ActivityLoginLayoutBinding;
import com.mich.gwan.shallom.helper.InputValidation;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityLoginLayoutBinding binding;

    private DatabaseHelper db;
    private InputValidation inputValidation;

    private ShapeableImageView shapeableImageViewUserAvatar;

    private TextInputEditText textInputEditTextUsername;
    private TextInputEditText textInputEditTextPassword;

    private TextInputLayout textInputLayoutUsername;
    private TextInputLayout textInputLayoutPassword;

    private TextView textViewForgotPassword;
    private TextView textViewCreateAccount;

    private AppCompatButton buttonLogin;

    private CardView cardViewGoogleSignIn;

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String USERNAME_KEY = "username_key";
    public static final String PASSWORD_KEY = "password_key";
    private SharedPreferences sharedPreferences;
    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityLoginLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();

        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.layout_tint));
    }

    /**
     * Initializes the views for this activity.
     * <p>
     * This method retrieves references to various views from the layout binding for
     * further use within the activity.
     * </p>
     */
    private void initViews(){
        shapeableImageViewUserAvatar = binding.shapeableImageViewUserAvatar;
        textInputEditTextUsername = binding.textInputEditTextUsername;
        textInputEditTextPassword = binding.textInputEditTextPassword;
        textInputLayoutUsername = binding.textInputLayoutUsername;
        textInputLayoutPassword = binding.textInputLayoutPassword;
        textViewForgotPassword = binding.textViewForgotPassword;
        textViewCreateAccount = binding.textViewCreateAccount;
        buttonLogin = binding.buttonLogIn;
        cardViewGoogleSignIn = binding.cardViewGoogleSignIn;
    }

    /**
     * Initializes event listeners for UI elements.
     * <p>
     * This method assigns event handlers to relevant UI elements
     * </p>
     */
    private void initListeners(){
        buttonLogin.setOnClickListener(this);
        cardViewGoogleSignIn.setOnClickListener(this);
        textViewForgotPassword.setOnClickListener(this);
    }

    /**
     * Initializes objects used within the current class.
     * <p>
     * This method initializes instances of various class instances.
     */
    private void initObjects(){
        db = new DatabaseHelper(this);
        inputValidation = new InputValidation(this);
    }

    /**
     * Implemented method to handle click events on the view.
     * <p>
     * This method is called when a view is clicked. It serves as a click event listener for
     * various UI elements.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonLogIn) {
            verifyFromSQLite();
        } else if (v.getId() == R.id.textViewForgotPassword || v.getId() == R.id.cardViewGoogleSignIn) {
            Toast.makeText(this, R.string.coming_soon, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Validates input text fields and verifies login credentials from SQLite database.
     * <p>
     * This method performs input validation on the uesrname, and password fields. It then checks the provided
     * credentials against the SQLite database. If the credentials are valid, it opens a new session, displays a success
     * message, and saves user information in SharedPreferences. Otherwise, it shows an error message.
     * </p>
     */
    private void verifyFromSQLite() {
        if (inputValidation.isInputEditTextFilled(textInputEditTextUsername, textInputLayoutUsername, getString(R.string.missing_username)))
            return;
        if (inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.empty_password)))
            return;
        if (inputValidation.isValidPassword(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.not_valid_password)))
            return;
        if (db.checkUser(Objects.requireNonNull(textInputEditTextUsername.getText()).toString().toUpperCase(),
                Objects.requireNonNull(textInputEditTextPassword.getText()).toString().toUpperCase())){

            
            clearInputFields();
        } else {
            Toast.makeText(this, getString(R.string.invalid_password), Toast.LENGTH_LONG).show();
        }
    }

    private void clearInputFields() {
        textInputEditTextUsername.setText("");
        textInputEditTextPassword.setText("");
    }
}
