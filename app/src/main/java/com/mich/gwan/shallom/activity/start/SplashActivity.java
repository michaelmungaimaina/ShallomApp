/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 12
 */

/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 6
 */

package com.mich.gwan.shallom.activity.start;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.databinding.ActivitySplashscreenBinding;

public class SplashActivity extends AppCompatActivity {
    private TextView shallomText;
    private int index = 0;
    private ActivitySplashscreenBinding binding;

    private String shallom;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashscreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Objects.requireNonNull(getSupportActionBar()).hide();
        int statusBarColor = ContextCompat.getColor(this, R.color.layout_tint);
        Window window = this.getWindow();
        window.setStatusBarColor(statusBarColor);

        shallomText = binding.textViewShallom;
        shallom = getString(R.string.shallom_text);



        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }, 2500); // milliseconds delay

    }

    private void animateText() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (index <= shallom.length()) {
                    shallomText.setText(shallom.substring(0, index++));
                    handler.postDelayed(this, 100); // Adjust the delay as needed
                }
            }
        }, 100); // Adjust the initial delay as needed
    }
}
