/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 1 / 23
 */

package com.mich.gwan.shallom.activity.signup;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.databinding.ActivitySignupPhoneVerificationBinding;
import com.mich.gwan.shallom.helper.InputValidation;

public class SignUpPhoneActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText textInputEditTextOne;
    private TextInputEditText textInputEditTextTwo;
    private TextInputEditText textInputEditTextThree;
    private TextInputEditText textInputEditTextFour;
    private TextInputEditText textInputEditTextFive;
    private TextInputEditText textInputEditTextSix;

    private TextView textViewRequestCode;

    private AppCompatButton buttonVerify;

    private InputValidation inputValidation;

    private ActivitySignupPhoneVerificationBinding binding;

    @Override
    protected void onCreate(Bundle davedInstanceState){
        super.onCreate(davedInstanceState);
        binding = ActivitySignupPhoneVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
        initListeners();
        controlSingleInput();
        initObjects();

        // Sets the status bar color to the specified color
        int statusBarColor = ContextCompat.getColor(this, R.color.layout_tint);
        Window window = this.getWindow();
        window.setStatusBarColor(statusBarColor);
    }

    /**
     * Controls the focus on single input fields.
     * This method adds text change listeners to the input fields and manages the focus transitions
     * between them based on the length of the input in each field.
     */
    private void controlSingleInput() {
        textInputEditTextOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (textInputEditTextOne.getText().toString().length() == 1)
                    textInputEditTextTwo.requestFocus();
            }
        });

        textInputEditTextTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textInputEditTextTwo.getText().toString().length() == 0)
                    textInputEditTextOne.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (textInputEditTextTwo.getText().toString().length() == 1)
                    textInputEditTextThree.requestFocus();
            }
        });

        textInputEditTextThree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textInputEditTextThree.getText().toString().length() == 0)
                    textInputEditTextTwo.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (textInputEditTextThree.getText().toString().length() == 1)
                    textInputEditTextFour.requestFocus();
            }
        });

        textInputEditTextFour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textInputEditTextFour.getText().toString().length() == 0)
                    textInputEditTextThree.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (textInputEditTextFour.getText().toString().length() == 1)
                    textInputEditTextFive.requestFocus();
            }
        });

        textInputEditTextFive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textInputEditTextFive.getText().toString().length() == 0)
                    textInputEditTextFour.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (textInputEditTextFive.getText().toString().length() == 1)
                    textInputEditTextSix.requestFocus();
            }
        });

        textInputEditTextSix.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textInputEditTextSix.getText().toString().length() == 0)
                    textInputEditTextFive.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // We can call api to verify the OTP here or on an explicit button click
            }
        });
    }

    private void initViews() {
        textInputEditTextOne = binding.textInputEditTextCodeOne;
        textInputEditTextTwo = binding.textInputEditTextCodeTwo;
        textInputEditTextThree = binding.textInputEditTextCodeThree;
        textInputEditTextFour = binding.textInputEditTextCodeFour;
        textInputEditTextFive = binding.textInputEditTextCodeFive;
        textInputEditTextSix = binding.textInputEditTextCodeSix;
        textViewRequestCode = binding.textViewRequestCode;
        buttonVerify = binding.buttonVerify;
    }

    private void initListeners() {
        textViewRequestCode.setOnClickListener(this);
        buttonVerify.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonVerify){
            // verify phone
            verifyCode();
            // Launch main activity

        } else if (v.getId() == R.id.textViewRequestCode) {
            // requestCode
            requestCode();
        }
    }

    private void requestCode() {
    }

    private void verifyCode() {
        if (textInputEditTextOne.getText().toString().isEmpty())
            return;
        if (textInputEditTextTwo.getText().toString().isEmpty())
            return;
        if (textInputEditTextThree.getText().toString().isEmpty())
            return;
        if (textInputEditTextFour.getText().toString().isEmpty())
            return;
        if (textInputEditTextFive.getText().toString().isEmpty())
            return;
        if (textInputEditTextSix.getText().toString().isEmpty())
            return;

        // verify code
    }
}
