/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 1 / 23
 */

package com.mich.gwan.shallom.activity.signup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.activity.LoginActivity;
import com.mich.gwan.shallom.dao.DatabaseHelper;
import com.mich.gwan.shallom.databinding.ActivitySignupProfileBinding;
import com.mich.gwan.shallom.helper.InputValidation;
import com.mich.gwan.shallom.model.User;
import com.mich.gwan.shallom.utils.ImageUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SignUpProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private TextInputEditText textInputEditTextFirstName;
    private TextInputEditText textInputEditTextSurname;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPhoneNumber;
    private TextInputEditText textInputEditTextUsername;

    private TextInputLayout textInputLayoutFirstName;
    private TextInputLayout textInputLayoutGender;
    private TextInputLayout textInputLayoutSurname;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPhoneNumber;
    private TextInputLayout textInputLayoutUsername;
    private TextInputLayout textInputLayoutUserAvatar;

    private Spinner spinnerGender;

    private AppCompatButton buttonNext;

    private ShapeableImageView shapeableImageViewUserAvatar;

    private TextView textViewBackToLogin;

    private ActivitySignupProfileBinding binding;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    public User user;
    private ImageUtils imageUtils;
    private EditText firstDigitOtpEdt, secondDigitOtpEdt, thirdDigitOtpEdt, fourthDigitOtpEdt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupProfileBinding.inflate(getLayoutInflater());
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
     * Initialize objects used in the activity.
     * This method initializes instances of DatabaseHelper, InputValidation, and User classes.
     */
    private void initObjects() {
        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Initialize InputValidation
        inputValidation = new InputValidation(this);

        // Initialize User object
        user = new User();
    }

    /**
     * Initialize listeners for various UI elements.
     * This method sets click listeners for the user avatar image view, back to login text view,
     * and next button.
     */
    private void initListeners() {
        // Set click listener for user avatar image view
        shapeableImageViewUserAvatar.setOnClickListener(this);

        // Set click listener for back to login text view
        textViewBackToLogin.setOnClickListener(this);

        // Set click listener for next button
        buttonNext.setOnClickListener(this);
    }


    /**
     * Initialize views from the layout file.
     * This method sets references to various views such as EditTexts, ImageViews, Spinners, etc.
     * It also populates the gender spinner with values.
     */
    private void initViews() {
        // Initialize EditTexts
        textInputEditTextFirstName = binding.textInputEditTextFirstName;
        textInputEditTextSurname = binding.textInputEditTextSurname;
        textInputEditTextEmail = binding.textInputEditTextUEmail;
        textInputEditTextPhoneNumber = binding.textInputEditTextPhoneNumber;
        textInputEditTextUsername = binding.textInputEditTextUsername;

        // Initialize TextInputLayouts
        textInputLayoutFirstName = binding.textInputLayoutFirstName;
        textInputLayoutSurname = binding.textInputLayoutSurname;
        textInputLayoutEmail = binding.textInputLayoutEmail;
        textInputLayoutPhoneNumber = binding.textInputLayoutPhoneNumber;
        textInputLayoutUsername = binding.textInputLayoutUsername;
        textInputLayoutUserAvatar = binding.textInputLayoutUserAvatar;
        textInputLayoutGender = binding.textInputLayoutGender;

        // Initialize Button
        buttonNext = binding.buttonNext;

        // Initialize ImageView
        shapeableImageViewUserAvatar = binding.shapeableImageViewUserAvatar;

        // Initialize TextView
        textViewBackToLogin = binding.textViewBackToLogin;

        // Initialize Spinner
        spinnerGender = binding.spinnerGender;

        // Create a list of gender options
        List<String> gender = new ArrayList<>();
        gender.add("Select Gender");
        gender.add("MALE");
        gender.add("FEMALE");
        gender.add("OTHER");

        // Set up ArrayAdapter for gender spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);
    }

    private void setupOnBackPressedCallback() {
    }

    @Override
    public void onClick(View v) {
        // Check which view was clicked
        if (v.getId() == R.id.buttonNext){
            // Button to proceed to the next step of registration
            // Launch Locality setting Activity
            verifyFromSQLite(); // Validate user input data
            Intent intentRegister = new Intent(getApplicationContext(), SignUpLocalityActivity.class);
            startActivity(intentRegister); // Start the next activity
        } else if (v.getId() == R.id.textViewBackToLogin) {
            // Text view to navigate back to the Login Activity
            // Move back to Login Activity
            Intent intentRegister = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intentRegister); // Start the LoginActivity
        } else if (v.getId() == R.id.shapeableImageViewUserAvatar) {
            // ImageView to select user avatar image
            // Call image chooser
            getContentLauncher.launch("image/*"); // Launch image chooser
        }
    }

    /**
     * ActivityResultLauncher for handling image selection from the device's storage.
     * This launcher is responsible for launching the file picker intent, retrieving the selected image URI,
     * and setting the selected image to the ImageView once the image is retrieved.
     */
    private final ActivityResultLauncher<String> getContentLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null) {
                        try {
                            // Get the bitmap from the URI and set it to the ImageView
                            InputStream inputStream = getContentResolver().openInputStream(result);
                            // Decode the input stream into a bitmap
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                            // Set the decoded bitmap to the ImageView
                            shapeableImageViewUserAvatar.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

    /**
     * Verifies the user input data against SQLite database constraints and validation rules.
     * This method checks various user input fields such as name, email, phone number, username, gender, and avatar image,
     * ensuring they are filled and valid before proceeding with user registration.
     * If any validation fails, appropriate error messages are displayed to guide the user.
     */
    private void verifyFromSQLite() {
        // Check if the first name field is filled
        if (inputValidation.isInputEditTextFilled(textInputEditTextFirstName, textInputLayoutFirstName, getString(R.string.input_this_field)))
            return;
        // Check if the surname field is filled
        if (inputValidation.isInputEditTextFilled(textInputEditTextSurname, textInputLayoutSurname, getString(R.string.input_this_field)))
            return;
        // Check if the email field is filled
        if (inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.input_this_field)))
            return;
        // Check if the email format is valid
        if (inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.not_valid_email)))
            return;
        // Check if the phone number field is filled
        if (inputValidation.isInputEditTextFilled(textInputEditTextPhoneNumber, textInputLayoutPhoneNumber, getString(R.string.input_this_field)))
            return;
        // Check if the phone number format is valid
        if (inputValidation.isValidPhoneNumber(textInputEditTextPhoneNumber, textInputLayoutPhoneNumber, getString(R.string.invalid_phone)))
            return;
        // Check if the username field is filled
        if (inputValidation.isInputEditTextFilled(textInputEditTextUsername, textInputLayoutUsername, getString(R.string.input_this_field)))
            return;
        // Check if the gender is selected from the spinner
        if (inputValidation.isSpinnerEmpty(spinnerGender, textInputLayoutGender, getString(R.string.select_gender)))
            return;
        // Check if an image is selected for the user avatar
        if (inputValidation.isShapeableImageViewHasImage(shapeableImageViewUserAvatar, textInputLayoutUserAvatar, getString(R.string.select_image)))
            return;
        // Check if the username already exists in the database
        if (databaseHelper.checkUsernameIfExists(Objects.requireNonNull(textInputEditTextUsername.getText()).toString().toUpperCase())) {
            textInputEditTextUsername.requestFocus();
            return;
        }
        // Check if the email already exists in the database
        if (databaseHelper.checkEmailIfExists(Objects.requireNonNull(textInputEditTextEmail.getText()).toString())){
            textInputEditTextEmail.requestFocus();
            return;
        }

        // Retrieve the drawable image from the avatar ImageView
        Drawable drawable = shapeableImageViewUserAvatar.getDrawable();
        // Set user data based on input fields
        user.setEmail(textInputEditTextEmail.getText().toString());
        user.setPhone(textInputEditTextPhoneNumber.getText().toString());
        user.setFirstName(textInputEditTextFirstName.getText().toString().toUpperCase());
        user.setLastName(textInputEditTextSurname.getText().toString().toUpperCase());
        user.setUsername(textInputEditTextUsername.getText().toString().toUpperCase());
        user.setGender(spinnerGender.getSelectedItem().toString().toUpperCase());
        user.setUserProf(((BitmapDrawable) drawable).getBitmap());
    }
}
