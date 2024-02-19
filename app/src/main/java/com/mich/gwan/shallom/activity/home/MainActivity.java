/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 12
 */

/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 1 / 22
 */

package com.mich.gwan.shallom.activity.home;

import static com.google.android.material.navigation.NavigationBarView.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.activity.doctrine.DoctrineActivity;
import com.mich.gwan.shallom.activity.lesson.LessonActivity;
import com.mich.gwan.shallom.activity.lesson.LessonHomeActivity;
import com.mich.gwan.shallom.activity.requests.RequestsActivity;
import com.mich.gwan.shallom.activity.songs.SongsActivity;
import com.mich.gwan.shallom.databinding.ActivityMainLayoutBinding;
import com.mich.gwan.shallom.fragment.TabFragment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity{

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

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String USERNAME_KEY = "username_key";
    public static final String PASSWORD_KEY = "password_key";
    private SharedPreferences sharedPreferences;
    private String username, password;
    private boolean exit;

    @RequiresApi(api = Build.VERSION_CODES.O)
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

        // initialize sharedprefs
        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        username = sharedPreferences.getString(USERNAME_KEY,null);
        password = sharedPreferences.getString(PASSWORD_KEY, null);

        //this basically defines on click on each menu item.
        bottomNavigationView = binding.bottomNavigation;
        // Set the default selected item
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Inflate the first fragment,this is like Home fragment before user selects anything.
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameContent,new TabFragment()).commit();

        // Setup the OnBackPressedCallback
        setupOnBackPressedCallback();

        // Show greeting text
        displayGreeting();

        // Handle menu navigation
        menuClick();

        // Sets the status bar color to the specified color
        int statusBarColor = ContextCompat.getColor(this, R.color.layout_tint);
        Window window = this.getWindow();
        window.setStatusBarColor(statusBarColor);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void displayGreeting(){
        int time = Integer.parseInt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH")));
        if (time >= 0 && time < 4)
            textViewGreeting.setText(getString(R.string.good_night));
        else if (time >= 4 && time < 12)
            textViewGreeting.setText(getString(R.string.good_morning));
        else if (time >= 12 && time < 16)
            textViewGreeting.setText(getString(R.string.good_afternoon));
        else if (time >= 16 && time < 22)
            textViewGreeting.setText(getString(R.string.good_evening));
        else if (time >= 22 && time <= 23)
            textViewGreeting.setText(getString(R.string.good_night));
    }

    private void menuClick(){
        bottomNavigationView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home)
                    Toast.makeText(MainActivity.this, getString(R.string.already_at_home), Toast.LENGTH_SHORT).show();
                else if (item.getItemId() == R.id.requests)
                    // Start activity requests
                    startActivity(new Intent(MainActivity.this, RequestsActivity.class));
                else if (item.getItemId() == R.id.lesson)
                    // Start activity lesson
                    startActivity(new Intent(MainActivity.this, LessonActivity.class));
                else if (item.getItemId() == R.id.doctrine)
                    // Start activity doctrine
                    startActivity(new Intent(MainActivity.this, DoctrineActivity.class));
                else if (item.getItemId() == R.id.songs)
                    // Start activity songs
                    startActivity(new Intent(MainActivity.this, SongsActivity.class));

                    return false;
            }
        });
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
                if (exit) {
                    finishAffinity(); // Finish all activities in the task, effectively exiting the application
                } else {
                    // Display a Toast message indicating the need to press back again to exit
                    Toast.makeText(getApplicationContext(), "Press Back again to Exit.", Toast.LENGTH_SHORT).show();

                    // Set exit flag to true and reset it after a specified time frame
                    exit = true;
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            exit = false; // Reset exit confirmation
                        }
                    }, 3 * 1000); // 3 seconds time frame
                }
            }
        };

        // Add OnBackPressedCallback to OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }
}
