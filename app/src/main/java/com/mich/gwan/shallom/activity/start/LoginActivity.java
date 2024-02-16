/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 12
 */

/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 1 / 22
 */

package com.mich.gwan.shallom.activity.start;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.activity.home.MainActivity;
import com.mich.gwan.shallom.activity.signup.SignUpProfileActivity;
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
    private boolean exit;

    /**
     * Lifecycle callback method invoked when the activity is first created.
     * This method is part of the Android Activity Lifecycle.
     * <p>
     * In this implementation, it performs the following actions:<p>
     * - Inflates the layout using View Binding.<p>
     * - Sets the content view to the root view of the inflated layout.<p>
     * - The OnBackPressedCallback controls how back button events are dispatched.<p>
     * - Initializes views, listeners, and objects using helper methods.<p>
     * - Sets the status bar color to a specified color.
     *
     * @param savedInstanceState A Bundle containing the activity's previously saved state, if any.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityLoginLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initializes views, listeners, and objects using helper methods
        initViews();
        initListeners();
        initObjects();

        // Setup the OnBackPressedCallback
        setupOnBackPressedCallback();

        // Sets the status bar color to the specified color
        int statusBarColor = ContextCompat.getColor(this, R.color.layout_tint);
        Window window = this.getWindow();
        window.setStatusBarColor(statusBarColor);
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

        textViewCreateAccount.setOnClickListener(view -> {
            // Launch Create Account
            Intent intentRegister = new Intent(LoginActivity.this, SignUpProfileActivity.class);
            startActivity(intentRegister);
        });
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
        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        username = sharedPreferences.getString(USERNAME_KEY, null);
        password = sharedPreferences.getString(PASSWORD_KEY, null);
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
        } else if (v.getId() == R.id.textViewCreateAccount){
            // Launch Create Account
            Intent intentRegister = new Intent(LoginActivity.this, SignUpProfileActivity.class);
            startActivity(intentRegister);
        }
    }

    /**
     * Validates input text fields and verifies login credentials from SQLite database.
     * <p>
     * This method performs input validation on the username, and password fields. It then checks the provided
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

            //Put values
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(USERNAME_KEY, textInputEditTextUsername.getText().toString().toUpperCase());
            editor.putString(PASSWORD_KEY, textInputEditTextPassword.getText().toString().toUpperCase());
            editor.apply();

            // Clear inputs
            clearInputFields();

            // Start main activity
            Intent intent;
            intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(this, getString(R.string.invalid_password), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Lifecycle callback method invoked when the activity is about to become visible to the user.
     * This method is part of the Android Activity Lifecycle.
     * <p>
     * In this implementation, it checks if user credentials (username, and password) are already
     * stored in SharedPreferences. If the credentials are present, it starts the MainActivity,
     * effectively skipping the login screen.
     */
    @Override
    protected void onStart(){
        super.onStart();
        if (username != null && password != null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    /**
     * Clears the input fields by setting the text of associated TextInputEditTexts to an empty string.
     * This method is useful for resetting or clearing user input in the login form.
     */
    private void clearInputFields() {
        textInputEditTextUsername.setText("");
        textInputEditTextPassword.setText("");
    }

    /**
     * Method to set up an OnBackPressedCallback for handling back button presses.
     * The OnBackPressedCallback controls how back button events are dispatched.
     * Handles the back button press logic, including a double-press mechanism for exit.
     * Uses Toast messages to notify the user about the exit confirmation.
     */
    private void setupOnBackPressedCallback() {
        // Initialize OnBackPressedCallback
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (exit) {
                    finishAffinity(); // Finish all activities in the task, effectively exiting the application
                } else {
                    // Display a Toast message indicating the need to press back again to exit
                    Toast.makeText(getApplicationContext(), "Press Back again to Exit.", Toast.LENGTH_SHORT).show();

                    // Set exit flag to true and reset it after a specified time frame
                    exit = true;
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            exit = false; // Reset exit confirmation
                        }
                    }, 3 * 1000); // 3 seconds time frame
                }
            }
        };

        // Add OnBackPressedCallback to OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }
}
