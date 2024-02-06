/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 1 / 22
 */

package com.mich.gwan.shallom.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.databinding.ActivityMainLayoutBinding;
import com.mich.gwan.shallom.fragment.TabFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainLayoutBinding binding;



    private Toolbar toolbar;

    FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityMainLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // since, NoActionBar was defined in theme, we set toolbar as our action bar.
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //this basically defines on click on each menu item.
        bottomNavigationView = findViewById(R.id.bottom_navigation);
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
