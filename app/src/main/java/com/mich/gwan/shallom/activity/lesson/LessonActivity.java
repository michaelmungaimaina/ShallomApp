/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 12
 */

/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 7
 */

package com.mich.gwan.shallom.activity.lesson;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.google.android.material.imageview.ShapeableImageView;
import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.databinding.ActivityBibleLessonLandingPageBinding;

public class LessonActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityBibleLessonLandingPageBinding binding;

    private LinearLayout linearLayoutWebsite;
    private LinearLayout linearLayoutFaqs;

    private AppCompatButton subscribeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityBibleLessonLandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
        initListeners();
        // Setup the OnBackPressedCallback
        setupOnBackPressedCallback();

        // Sets the status bar color to the specified color
        int statusBarColor = ContextCompat.getColor(this, R.color.layout_tint);
        Window window = this.getWindow();
        window.setStatusBarColor(statusBarColor);
    }

    private void initViews() {
        linearLayoutWebsite = binding.layoutVisitWebsite;
        linearLayoutFaqs = binding.layoutFaqs;
        subscribeButton = binding.buttonSubscribe;
    }

    private void initListeners(){
        linearLayoutFaqs.setOnClickListener(this);
        linearLayoutWebsite.setOnClickListener(this);
        subscribeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.layoutFaqs){

        } else if (v.getId() == R.id.layoutVisitWebsite) {

        } else if (v.getId() == R.id.buttonSubscribe) {
            startActivity(new Intent(LessonActivity.this, LessonPaymentActivity.class));
        }
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
                finish();
            }
        };

        // Add OnBackPressedCallback to OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }
}
