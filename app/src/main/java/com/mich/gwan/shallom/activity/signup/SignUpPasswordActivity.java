/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 1 / 23
 */

package com.mich.gwan.shallom.activity.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.dao.DatabaseHelper;
import com.mich.gwan.shallom.databinding.ActivitySignupPasswordBinding;
import com.mich.gwan.shallom.helper.InputValidation;
import com.mich.gwan.shallom.model.User;

public class SignUpPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private LinearLayout passwordStrengthControllerLayout;
    private LinearLayout passwordWeakLayout;
    private LinearLayout passwordGoodLayout;
    private LinearLayout passwordStrongLayout;

    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton buttonPrevious;
    private AppCompatButton buttonRegister;

    private SignUpLocalityActivity localityActivity;
    private User user;
    private DatabaseHelper databaseHelper;
    private InputValidation inputValidation;

    private ActivitySignupPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivitySignupPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
        initListeners();
        initObjects();

        // Sets the status bar color to the specified color
        int statusBarColor = ContextCompat.getColor(this, R.color.layout_tint);
        Window window = this.getWindow();
        window.setStatusBarColor(statusBarColor);
    }

    private void initViews() {
        textInputLayoutPassword = binding.textInputLayoutPassword;
        textInputLayoutConfirmPassword = binding.textInputLayoutConfirmPassword;
        textInputEditTextPassword = binding.textInputEditTextPassword;
        textInputEditTextConfirmPassword = binding.textInputEditTextConfirmPassword;
        buttonPrevious = binding.buttonPrevious;
        buttonRegister = binding.buttonNext;
        passwordStrengthControllerLayout = binding.passwordStrengthControllerLayout;
        passwordWeakLayout = binding.weakLayout;
        passwordGoodLayout = binding.goodLayout;
        passwordStrongLayout = binding.strongLayout;
    }

    private void initListeners() {
        buttonPrevious.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
    }

    private void initObjects() {
        localityActivity = new SignUpLocalityActivity();
        user = localityActivity.user;
        databaseHelper = new DatabaseHelper(this);
        inputValidation = new InputValidation(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonNext){
            verifyFromSQLite();
            Intent phoneVerificationIntent = new Intent(getApplicationContext(), SignUpPhoneActivity.class);
            startActivity(phoneVerificationIntent);
        } else if (v.getId() == R.id.buttonPrevious)
            finish();
    }

    private void attachTextWatcher() {
        textInputEditTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordStrengthControllerLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkPassword(editable.toString());
            }
        });
    }


    private void checkPassword(String password){
        if (password.length() < 6 && !inputValidation.isValidPassword(password, "$(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+,.\\\\/;':\"-]).{6,15}")){
            passwordStrengthControllerLayout.setVisibility(View.VISIBLE);
            passwordWeakLayout.setVisibility(View.VISIBLE);
            passwordGoodLayout.setVisibility(View.GONE);
            passwordStrongLayout.setVisibility(View.GONE);
        } else if (inputValidation.isValidPassword(password, "$(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+,.\\\\/;':\"-]).{6,7}")) {
            passwordStrengthControllerLayout.setVisibility(View.VISIBLE);
            passwordWeakLayout.setVisibility(View.GONE);
            passwordGoodLayout.setVisibility(View.VISIBLE);
            passwordStrongLayout.setVisibility(View.GONE);
        } else if (inputValidation.isValidPassword(password, "$(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+,.\\\\/;':\"-]).{8,15}")){
            passwordStrengthControllerLayout.setVisibility(View.VISIBLE);
            passwordWeakLayout.setVisibility(View.GONE);
            passwordGoodLayout.setVisibility(View.GONE);
            passwordStrongLayout.setVisibility(View.VISIBLE);
        }
    }

    private void verifyFromSQLite() {
        if (inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.empty_password)))
            return;
        if (inputValidation.isValidPassword(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.not_valid_password)))
            return;
        if (inputValidation.isInputEditTextFilled(textInputEditTextConfirmPassword, textInputLayoutConfirmPassword, getString(R.string.confirm_password)))
            return;
        if (inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword, textInputLayoutConfirmPassword, getString(R.string.password_mismatch)))
            return;

        user.setPassword(textInputEditTextPassword.getText().toString());

        databaseHelper.addUser(user);
    }
}
