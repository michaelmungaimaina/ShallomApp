/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 14
 */

package com.mich.gwan.shallom.activity.songs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.activity.lesson.LessonDaySummary;
import com.mich.gwan.shallom.activity.lesson.LessonHomeActivity;
import com.mich.gwan.shallom.adapter.AdapterSong;
import com.mich.gwan.shallom.dao.DatabaseHelper;
import com.mich.gwan.shallom.databinding.ActivitySongListLayoutBinding;
import com.mich.gwan.shallom.helper.InputValidation;
import com.mich.gwan.shallom.helper.RecyclerTouchListener;
import com.mich.gwan.shallom.model.LessonQuarter;
import com.mich.gwan.shallom.model.Song;
import com.mich.gwan.shallom.service.AsyncTaskExecutorService;

import java.util.ArrayList;
import java.util.List;

public class SongListActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySongListLayoutBinding binding;

    private ImageView backIcon;
    private ImageView searchIcon;

    private CardView cardViewSearch;
    private CardView cardViewFilter;

    private EditText editTextFilter;

    private TextView middleText;

    private Toolbar toolbar;

    private RecyclerView recyclerView;

    private DatabaseHelper databaseHelper;

    private AdapterSong adapter;

    private InputValidation inputValidation;

    private List<Song> list;

    private String category;
    private boolean isVisible = false;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivitySongListLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
        initObjects();
        initListeners();

        setSupportActionBar(toolbar);

        // Sets the status bar color to the specified color
        int statusBarColor = ContextCompat.getColor(this, R.color.layout_tint);
        Window window = this.getWindow();
        window.setStatusBarColor(statusBarColor);
    }

    private void initViews() {
        backIcon = binding.myToolbar.backIcon;
        searchIcon = binding.myToolbar.shapeableImageViewIcon;
        cardViewSearch = binding.myToolbar.cardViewSearch;
        cardViewFilter = binding.myToolbar.cardViewFilter;
        editTextFilter = binding.myToolbar.editTextFilter;
        middleText = binding.myToolbar.toolbarMiddleText;
        toolbar = binding.myToolbar.toolbarPayment;
        recyclerView = binding.recyclerViewSongs;
    }

    private void initObjects() {
        intent = getIntent();
        category = intent.getStringExtra("CATEGORY");
        databaseHelper = new DatabaseHelper(this);
        list = new ArrayList<>();
        inputValidation = new InputValidation(this);

        middleText.setText(category);

        // Retrieve data from database
        if (category.equals("NULL"))
            getDataFromSQLite();
        else
            getDataFromSQLite(category);

        adapter = new AdapterSong(list);

        LinearLayoutManager myLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void initListeners() {
        cardViewSearch.setOnClickListener(this);
        backIcon.setOnClickListener(this);

        // Action filter
        editTextFilter.addTextChangedListener(new TextWatcher() {
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

        // Clicking the recyclerview
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent intent = new Intent(view.getContext(), SongViewActivity.class);
                        intent.putExtra("SONG_ID", list.get(position).getSongId());
                        intent.putExtra("SONG_AUTHOR", list.get(position).getSongAuthor());
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));


    }

    void filter(String text){
        List<Song> temp = new ArrayList<>();
        for(Song item: list){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(item.getSongTitle().contains(text.toUpperCase())){
                temp.add(item);
            }
        }
        //update recyclerview
        adapter.updateList(temp);
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cardViewSearch) {
            if (!isVisible) {
                isVisible = true;
                enableFilterMode();
                editTextFilter.requestFocus();
                searchIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_close_green));
                WindowCompat.getInsetsController(getWindow(), editTextFilter).show(WindowInsetsCompat.Type.ime());
                Toast.makeText(SongListActivity.this, "Filter mode enabled", Toast.LENGTH_LONG).show();
            } else {
                isVisible = false;
                enableFilterMode();
                searchIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_search_green));
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                adapter.updateList(list);
            }
        }
    }

    /**
     * Enable filter mode
     */
    public void enableFilterMode() {
        if (isVisible) {
            cardViewFilter.setVisibility(View.VISIBLE);
        } else {
            cardViewFilter.setVisibility(View.GONE);
            inputValidation.hideKeyboardFrom(editTextFilter);
        }
    }

    private void getDataFromSQLite(){
        new AsyncTaskExecutorService<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void unused) {
                list.clear();
                list = databaseHelper.getSong();
                return null;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void onPostExecute(Void unused) {
                adapter.notifyDataSetChanged();
            }
        };
    }


    private void getDataFromSQLite(String category){
        new AsyncTaskExecutorService<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void unused) {
                list.clear();
                list = databaseHelper.getSong(category);
                return null;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void onPostExecute(Void unused) {
                adapter.notifyDataSetChanged();
            }
        };
    }


}
