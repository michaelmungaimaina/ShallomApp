/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 12
 */

/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 7
 */

package com.mich.gwan.shallom.activity.doctrine;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationBarView;
import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.activity.home.MainActivity;
import com.mich.gwan.shallom.activity.lesson.LessonActivity;
import com.mich.gwan.shallom.activity.lesson.LessonDaySummary;
import com.mich.gwan.shallom.activity.requests.RequestsActivity;
import com.mich.gwan.shallom.activity.songs.SongsActivity;
import com.mich.gwan.shallom.adapter.DoctrineAdapter;
import com.mich.gwan.shallom.dao.DatabaseHelper;
import com.mich.gwan.shallom.databinding.ActivityBibleLessonDaySummaryBinding;
import com.mich.gwan.shallom.databinding.ActivityDoctrineLayoutBinding;
import com.mich.gwan.shallom.helper.InputValidation;
import com.mich.gwan.shallom.model.Doctrine;
import com.mich.gwan.shallom.model.LessonWeek;
import com.mich.gwan.shallom.service.AsyncTaskExecutorService;

import java.util.ArrayList;
import java.util.List;

public class DoctrineActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityDoctrineLayoutBinding binding;

    private RecyclerView recyclerView;

    private ImageView searchIcon;
    private ImageView backIcon;

    private CardView filterCardView;
    private CardView searchCardView;

    private EditText filterEditText;

    private TextView middleText;

    private Toolbar toolbar;

    private BottomNavigationView bottomNavigationView;

    private DatabaseHelper databaseHelper;
    private InputValidation inputValidation;
    private DoctrineAdapter adapter;

    private List<Doctrine> list;

    private boolean isVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityDoctrineLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
        initObjects();
        initListeners();

        // Sets the status bar color to the specified color
        int statusBarColor = ContextCompat.getColor(this, R.color.layout_tint);
        Window window = this.getWindow();
        window.setStatusBarColor(statusBarColor);
    }

    private void initViews() {
        recyclerView = binding.recyclerViewDoctrine;
        searchIcon = binding.myToolbar.shapeableImageViewIcon;
        backIcon = binding.myToolbar.backIcon;
        filterCardView = binding.myToolbar.cardViewFilter;
        searchCardView = binding.myToolbar.cardViewSearch;
        filterEditText = binding.myToolbar.editTextFilter;
        toolbar = binding.myToolbar.toolbarPayment;
        middleText = binding.myToolbar.toolbarMiddleText;
        bottomNavigationView = binding.bottomNavigation;

        backIcon.setVisibility(View.GONE);

        middleText.setText(R.string.points_doctrines);
        middleText.setTextSize(27);
        //middleText.setTextSize(37);

        // Set the default selected item
        bottomNavigationView.setSelectedItemId(R.id.doctrine);
        // Handle bottom menu navigation
        menuClick();

    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(this);
        inputValidation = new InputValidation(this);
        list = new ArrayList<>();
        adapter = new DoctrineAdapter(list);

        LinearLayoutManager myLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        getDataFromSQLite();
    }

    private void initListeners() {
        searchCardView.setOnClickListener(this);
        backIcon.setOnClickListener(this);

        filterEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void filter(String text){
        List<Doctrine> temp = new ArrayList<>();
        for(Doctrine item: list){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(item.getDoctrineRef().toUpperCase().contains(text.toUpperCase()) || String.valueOf(item.getDoctrineId()).toUpperCase().contains(text.toUpperCase()) ||
                  item.getDoctrineDescription().toUpperCase().contains(text.toUpperCase()) || item.getDoctrineContent().toUpperCase().contains(text.toUpperCase())){
                temp.add(item);
            }
        }
        //update recyclerview
        adapter.updateList(temp);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cardViewSearch){
            if (!isVisible){
                isVisible = true;
                enableFilterMode();
                filterEditText.requestFocus();
                searchIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_close_green));
                WindowCompat.getInsetsController(getWindow(), filterEditText).show(WindowInsetsCompat.Type.ime());
                Toast.makeText(DoctrineActivity.this, "Filter mode enabled", Toast.LENGTH_LONG).show();
            } else {
                isVisible = false;
                enableFilterMode();
                searchIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_search_green));
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                adapter.updateList(list);
                Toast.makeText(DoctrineActivity.this, "Filter mode disabled", Toast.LENGTH_LONG).show();
            }

        } else if (v.getId() == R.id.backIcon) {
            finish();
        }
    }

    private void menuClick(){
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home)
                    // Start activity home
                    startActivity(new Intent(DoctrineActivity.this, MainActivity.class));
                else if (item.getItemId() == R.id.requests)
                    // Start activity requests
                    startActivity(new Intent(DoctrineActivity.this, RequestsActivity.class));
                else if (item.getItemId() == R.id.lesson)
                    // Start activity lesson
                    startActivity(new Intent(DoctrineActivity.this, LessonActivity.class));
                else if (item.getItemId() == R.id.doctrine)
                    Toast.makeText(DoctrineActivity.this, getString(R.string.already_at_doctrine), Toast.LENGTH_SHORT).show();
                else if (item.getItemId() == R.id.songs)
                    // Start activity songs
                    startActivity(new Intent(DoctrineActivity.this, SongsActivity.class));

                return false;
            }
        });
    }


    /**
     * Enable filter mode
     */
    public void enableFilterMode() {
        if (isVisible) {
            filterCardView.setVisibility(View.VISIBLE);
        } else {
            filterCardView.setVisibility(View.GONE);
            inputValidation.hideKeyboardFrom(filterEditText);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void getDataFromSQLite(){
// AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(@SuppressLint("StaticFieldLeak") Void... params) {
                list.clear();
                list.addAll(databaseHelper.getDoctrine());
                return null;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
