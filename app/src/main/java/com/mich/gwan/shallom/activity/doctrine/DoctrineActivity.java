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
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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

import com.google.android.material.imageview.ShapeableImageView;
import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.activity.lesson.LessonDaySummary;
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

    }

    private void initViews() {
        recyclerView = binding.recyclerViewGroups;
        searchIcon = binding.myToolbar.shapeableImageViewIcon;
        backIcon = binding.myToolbar.backIcon;
        filterCardView = binding.myToolbar.cardViewFilter;
        searchCardView = binding.myToolbar.cardViewSearch;
        filterEditText = binding.myToolbar.editTextFilter;
        toolbar = binding.myToolbar.toolbarPayment;
        middleText = binding.myToolbar.toolbarMiddleText;
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
            if(item.getDoctrineRef().contains(text.toUpperCase()) || String.valueOf(item.getDoctrineId()).contains(text.toUpperCase()) ||
                  item.getDoctrineDescription().contains(text.toUpperCase()) || item.getDoctrineContent().contains(text.toUpperCase())){
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
            finishAffinity();
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
                list = databaseHelper.getDoctrine();
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
