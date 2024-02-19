/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 12
 */

package com.mich.gwan.shallom.activity.lesson;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.imageview.ShapeableImageView;
import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.dao.DatabaseHelper;
import com.mich.gwan.shallom.databinding.ActivityBibleLessonPaymentPageBinding;
import com.mich.gwan.shallom.databinding.BottomSheetPaymentNotificationBinding;
import com.mich.gwan.shallom.model.User;

public class LessonPaymentActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityBibleLessonPaymentPageBinding binding;

    private TextView textViewOne;
    private TextView textViewTwo;
    private TextView textViewThree;
    private TextView textViewFour;
    private TextView textViewFive;
    private TextView textViewSix;
    private TextView textViewSeven;
    private TextView textViewEight;
    private TextView textViewNine;
    private TextView textViewThreeMonths;
    private TextView textViewSixMonths;
    private TextView textViewNineMonths;
    private TextView textViewOneYear;
    private TextView textViewTen;

    private Toolbar toolbar;

    private ImageView iconBack;
    private ShapeableImageView userProfile;

    private LinearLayout linearLayoutThreeMonths;
    private LinearLayout linearLayoutSixMonths;
    private LinearLayout linearLayoutNineMonths;
    private LinearLayout linearLayoutOneYear;

    private AppCompatButton buttonMakePayment;

    private User user;
    private DatabaseHelper databaseHelper;

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String USERNAME_KEY = "username_key";
    public static final String PASSWORD_KEY = "password_key";
    private SharedPreferences sharedPreferences;
    private String username, password;

    private int subscriptionPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityBibleLessonPaymentPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // initialize sharedprefs
        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        username = sharedPreferences.getString(USERNAME_KEY,null);
        password = sharedPreferences.getString(PASSWORD_KEY, null);

        initViews();

        // set toolbar as the action bar.
        setSupportActionBar(toolbar);

        //initObjects();
        initListeners();

    }

    private void initViews() {
        textViewOne = binding.textInputEditTextOne;
        textViewTwo = binding.textInputEditTextTwo;
        textViewThree = binding.textInputEditTextThree;
        textViewFour = binding.textInputEditTextFour;
        textViewFive = binding.textInputEditTextFive;
        textViewSix = binding.textInputEditTextSix;
        textViewSeven = binding.textInputEditTextSeven;
        textViewEight = binding.textInputEditTextEight;
        textViewNine = binding.textInputEditTextNine;
        textViewThreeMonths = binding.textViewThreeMonths;
        textViewSixMonths = binding.textViewSixMonths;
        textViewNineMonths = binding.textViewNineMonths;
        textViewOneYear = binding.textViewOneYear;
        textViewTen = binding.textInputEditTextTen;
        toolbar = binding.myToolbar.toolbarPayment;
        iconBack = binding.myToolbar.backIcon;
        userProfile = binding.myToolbar.shapeableImageViewUserAvatar;
        linearLayoutThreeMonths = binding.linearLayoutThreeMonths;
        linearLayoutSixMonths = binding.linearLayoutSixMonths;
        linearLayoutNineMonths = binding.linearLayoutNineMonths;
        linearLayoutOneYear = binding.linearLayoutOneYear;
        buttonMakePayment = binding.buttonMakePayment;
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(this);
        user = databaseHelper.getUser(username, password).get(0);

        userProfile.setImageBitmap(user.getUserProf());

        textViewOne.setText(user.getPhone().substring(0,2));
        textViewTwo.setText(user.getPhone().substring(1,3));
        textViewThree.setText(user.getPhone().substring(2,4));
        textViewFour.setText(user.getPhone().substring(3,5));
        textViewFive.setText(user.getPhone().substring(4,6));
        textViewSix.setText(user.getPhone().substring(5,7));
        textViewSeven.setText(user.getPhone().substring(6,8));
        textViewEight.setText(user.getPhone().substring(7,9));
        textViewNine.setText(user.getPhone().substring(8,10));
        textViewTen.setText(user.getPhone().substring(10));
    }

    private void initListeners() {
        iconBack.setOnClickListener(this);
        userProfile.setOnClickListener(this);
        linearLayoutThreeMonths.setOnClickListener(this);
        linearLayoutSixMonths.setOnClickListener(this);
        linearLayoutNineMonths.setOnClickListener(this);
        linearLayoutOneYear.setOnClickListener(this);
        buttonMakePayment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backIcon){
            finishAffinity();
        } else if (v.getId() == R.id.shapeableImageViewUserAvatar) {
            // Open User profile settings
        } else if (v.getId() == R.id.linearLayoutThreeMonths) {
            linearLayoutThreeMonths.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.green_700)));
            linearLayoutSixMonths.setBackgroundTintList(null);
            linearLayoutNineMonths.setBackgroundTintList(null);
            linearLayoutOneYear.setBackgroundTintList(null);

            subscriptionPrice = Integer.parseInt(textViewThreeMonths.getText().toString().substring(5));
        } else if (v.getId() == R.id.linearLayoutSixMonths) {
            linearLayoutThreeMonths.setBackgroundTintList(null);
            linearLayoutSixMonths.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.green_blue)));
            linearLayoutNineMonths.setBackgroundTintList(null);
            linearLayoutOneYear.setBackgroundTintList(null);

            subscriptionPrice = Integer.parseInt(textViewSixMonths.getText().toString().substring(5));
        } else if (v.getId() == R.id.linearLayoutNineMonths) {
            linearLayoutThreeMonths.setBackgroundTintList(null);
            linearLayoutSixMonths.setBackgroundTintList(null);
            linearLayoutNineMonths.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.orange_green)));
            linearLayoutOneYear.setBackgroundTintList(null);

            subscriptionPrice = Integer.parseInt(textViewNineMonths.getText().toString().substring(5));
        } else if (v.getId() == R.id.linearLayoutOneYear) {
            linearLayoutThreeMonths.setBackgroundTintList(null);
            linearLayoutSixMonths.setBackgroundTintList(null);
            linearLayoutNineMonths.setBackgroundTintList(null);
            linearLayoutOneYear.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.orange_bright)));

            subscriptionPrice = Integer.parseInt(textViewOneYear.getText().toString().substring(5));
        } else if (v.getId() == R.id.buttonMakePayment) {
            // Show bottom sheet
            showBottomSheet();
            // Start activity lesson view
            startActivity(new Intent(LessonPaymentActivity.this, LessonHomeActivity.class));
        }
    }

    private void showBottomSheet() {
        @SuppressLint("PrivateResource") final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this,
                com.google.android.material.R.style.Base_V14_ThemeOverlay_MaterialComponents_BottomSheetDialog);
        BottomSheetPaymentNotificationBinding binding1 = BottomSheetPaymentNotificationBinding.inflate(getLayoutInflater());

        final AppCompatButton buttonOk = binding1.buttonOk;

        buttonOk.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
        });
        bottomSheetDialog.show();
    }
}
