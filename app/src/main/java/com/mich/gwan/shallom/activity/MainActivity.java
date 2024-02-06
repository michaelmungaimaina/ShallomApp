/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 1 / 22
 */

package com.mich.gwan.shallom.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;
import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.databinding.ActivityMainLayoutBinding;
import com.mich.gwan.shallom.fragment.TabFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainLayoutBinding binding;

    private Toolbar toolbar;

    private TextView textViewName;
    private TextView textViewGreeting;

    private CardView cardViewSearch;
    private CardView cardViewSettings;
    private CardView cardViewNotification;

    private ShapeableImageView profileImage;

    FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityMainLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textViewName = binding.myToolbar.textViewNameDisplay;
        textViewGreeting = binding.myToolbar.textViewGreetingText;
        profileImage = binding.myToolbar.shapeableImageViewUserAvatar;
        cardViewSearch = binding.myToolbar.cardViewSearch;
        cardViewSettings = binding.myToolbar.cardViewSettings;
        cardViewNotification = binding.myToolbar.cardViewNotification;
        toolbar = binding.myToolbar.toolbar;

        // since, NoActionBar was defined in theme, we set toolbar as our action bar.
        setSupportActionBar(toolbar);

        //this basically defines on click on each menu item.
        bottomNavigationView = binding.bottomNavigation;
        Menu menu = bottomNavigationView.getMenu();

        //Inflate the first fragment,this is like Home fragment before user selects anything.
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameContent,new TabFragment()).commit();

        // Sets the status bar color to the specified color
        int statusBarColor = ContextCompat.getColor(this, R.color.layout_tint);
        Window window = this.getWindow();
        window.setStatusBarColor(statusBarColor);

    }
}
