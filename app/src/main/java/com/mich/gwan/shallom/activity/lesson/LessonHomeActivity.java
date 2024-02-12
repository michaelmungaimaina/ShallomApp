/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 12
 */

package com.mich.gwan.shallom.activity.lesson;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.adapter.LessonQuarterAdapter;
import com.mich.gwan.shallom.dao.DatabaseHelper;
import com.mich.gwan.shallom.databinding.ActivityBibleLessonHomeBinding;
import com.mich.gwan.shallom.helper.InputValidation;
import com.mich.gwan.shallom.helper.RecyclerTouchListener;
import com.mich.gwan.shallom.model.LessonQuarter;
import com.mich.gwan.shallom.service.AsyncTaskExecutorService;

import java.util.ArrayList;
import java.util.List;

public class LessonHomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityBibleLessonHomeBinding binding;
    private RecyclerView recyclerView;

    private ShapeableImageView searchIcon;
    private ImageView backIcon;

    private CardView filterCardView;
    private CardView searchCardView;

    private EditText filterEditText;

    private Toolbar toolbar;

    private DatabaseHelper databaseHelper;
    private InputValidation inputValidation;
    private List<LessonQuarter> list;

    private LessonQuarterAdapter adapter;

    private boolean isVisible = false;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityBibleLessonHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();

        // set toolbar as the action bar.
        setSupportActionBar(toolbar);

        initObjects();
        initListeners();

        // Sets the status bar color to the specified color
        int statusBarColor = ContextCompat.getColor(this, R.color.layout_tint);
        Window window = this.getWindow();
        window.setStatusBarColor(statusBarColor);

    }

    private void initViews() {
        recyclerView = binding.recyclerViewBibleLesson;
        searchIcon = binding.myToolbar.shapeableImageViewIcon;
        backIcon = binding.myToolbar.backIcon;
        filterCardView = binding.myToolbar.cardViewFilter;
        searchCardView = binding.myToolbar.cardViewSearch;
        filterEditText = binding.myToolbar.editTextFilter;
        toolbar = binding.myToolbar.toolbarPayment;
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(this);
        list = new ArrayList<>();
        adapter = new LessonQuarterAdapter(list);
        inputValidation = new InputValidation(this);

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

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent intent = new Intent(view.getContext(), LessonDaySummary.class);
                        intent.putExtra("YEAR", list.get(position).getQuarterYear());
                        intent.putExtra("QUARTER_ID", list.get(position).getQuarterId());
                        intent.putExtra("QUARTER", "NULL");
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
    }

    void filter(String text){
        List<LessonQuarter> temp = new ArrayList<>();
        for(LessonQuarter item: list){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(item.getQuarterYear().contains(text.toUpperCase())){
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
                Toast.makeText(LessonHomeActivity.this, "Filter mode enabled", Toast.LENGTH_LONG).show();
            } else {
                isVisible = false;
                enableFilterMode();
                searchIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_search_green));
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                adapter.updateList(list);
            }

        } else if (v.getId() == R.id.backIcon) {

        }
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

    private void getDataFromSQLite(){
        new AsyncTaskExecutorService<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void unused) {
                list.clear();
                list = databaseHelper.getLessonQuarter();
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
