package com.mich.gwan.shallom;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    private EditText firstDigitOtpEdt, secondDigitOtpEdt, thirdDigitOtpEdt, fourthDigitOtpEdt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
