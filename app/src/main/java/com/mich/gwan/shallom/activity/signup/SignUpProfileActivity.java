/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 1 / 23
 */

package com.mich.gwan.shallom.activity.signup;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

        firstDigitOtpEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (firstDigitOtpEdt.getText().toString().length() == 1) {
                    secondDigitOtpEdt.requestFocus();
                }
            }
        });

        secondDigitOtpEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (secondDigitOtpEdt.getText().toString().length() == 0) {
                    firstDigitOtpEdt.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (secondDigitOtpEdt.getText().toString().length() == 1) {
                    thirdDigitOtpEdt.requestFocus();
                }
            }
        });

        thirdDigitOtpEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (thirdDigitOtpEdt.getText().toString().length() == 0) {
                    secondDigitOtpEdt.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (thirdDigitOtpEdt.getText().toString().length() == 1) {
                    fourthDigitOtpEdt.requestFocus();
                }
            }
        });

        fourthDigitOtpEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (fourthDigitOtpEdt.getText().toString().length() == 0) {
                    thirdDigitOtpEdt.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // We can call api to verify the OTP here or on an explicit button click
            }
        });
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(this);
        inputValidation = new InputValidation(this);
        user = new User();
    }

    private void initListeners() {
        shapeableImageViewUserAvatar.setOnClickListener(this);
        textViewBackToLogin.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
    }

    private void initViews() {
        textInputEditTextFirstName = binding.textInputEditTextFirstName;
        textInputEditTextSurname = binding.textInputEditTextSurname;
        textInputEditTextEmail = binding.textInputEditTextUEmail;
        textInputEditTextPhoneNumber = binding.textInputEditTextPhoneNumber;
        textInputEditTextUsername = binding.textInputEditTextUsername;
        textInputLayoutFirstName = binding.textInputLayoutFirstName;
        textInputLayoutSurname = binding.textInputLayoutSurname;
        textInputLayoutEmail = binding.textInputLayoutEmail;
        textInputLayoutPhoneNumber = binding.textInputLayoutPhoneNumber;
        textInputLayoutUsername = binding.textInputLayoutUsername;
        textInputLayoutUserAvatar = binding.textInputLayoutUserAvatar;
        buttonNext = binding.buttonNext;
        shapeableImageViewUserAvatar = binding.shapeableImageViewUserAvatar;
        textViewBackToLogin = binding.textViewBackToLogin;
        spinnerGender = binding.spinnerGender;
        textInputLayoutGender = binding.textInputLayoutGender;

        List<String> gender = new ArrayList<>();
        gender.add("Select Gender");
        gender.add("MALE");
        gender.add("FEMALE");
        gender.add("OTHER");

        // Set up ArrayAdapter for gender
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);
    }

    private void setupOnBackPressedCallback() {
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonNext){
            // Launch Locality setting Activity
            verifyFromSQLite();
            Intent intentRegister = new Intent(getApplicationContext(), SignUpLocalityActivity.class);
            startActivity(intentRegister);
        } else if (v.getId() == R.id.textViewBackToLogin) {
            // Move back to Login Activity
            Intent intentRegister = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intentRegister);
        } else if (v.getId() == R.id.shapeableImageViewUserAvatar) {
            // Call image chooser
        }
    }

    private void verifyFromSQLite() {
        if (inputValidation.isInputEditTextFilled(textInputEditTextFirstName, textInputLayoutFirstName, getString(R.string.input_this_field)))
            return;
        if (inputValidation.isInputEditTextFilled(textInputEditTextSurname, textInputLayoutSurname, getString(R.string.input_this_field)))
            return;
        if (inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.input_this_field)))
            return;
        if (inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.not_valid_email)))
            return;
        if (inputValidation.isInputEditTextFilled(textInputEditTextPhoneNumber, textInputLayoutPhoneNumber, getString(R.string.input_this_field)))
            return;
        if (inputValidation.isValidPhoneNumber(textInputEditTextPhoneNumber, textInputLayoutPhoneNumber, getString(R.string.invalid_phone)))
            return;
        if (inputValidation.isInputEditTextFilled(textInputEditTextUsername, textInputLayoutUsername, getString(R.string.input_this_field)))
            return;
        if (inputValidation.isSpinnerEmpty(spinnerGender, textInputLayoutGender, getString(R.string.select_gender)))
            return;
        if (inputValidation.isShapeableImageViewHasImage(shapeableImageViewUserAvatar, textInputLayoutUserAvatar, getString(R.string.select_image)))
            return;
        if (databaseHelper.checkUsernameIfExists(Objects.requireNonNull(textInputEditTextUsername.getText()).toString().toUpperCase())) {
            textInputEditTextUsername.requestFocus();
            return;
        }
        if (databaseHelper.checkEmailIfExists(Objects.requireNonNull(textInputEditTextEmail.getText()).toString())){
            textInputEditTextEmail.requestFocus();
            return;
        }

        Drawable drawable = shapeableImageViewUserAvatar.getDrawable();
        user.setEmail(textInputEditTextEmail.getText().toString());
        user.setPhone(textInputEditTextPhoneNumber.getText().toString());
        user.setFirstName(textInputEditTextFirstName.getText().toString().toUpperCase());
        user.setLastName(textInputEditTextSurname.getText().toString().toUpperCase());
        user.setUsername(textInputEditTextUsername.getText().toString().toUpperCase());
        user.setGender(spinnerGender.getSelectedItem().toString().toUpperCase());
        user.setUserProf(((BitmapDrawable) drawable).getBitmap());
    }
}
