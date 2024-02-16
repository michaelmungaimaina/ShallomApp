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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
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
import com.mich.gwan.shallom.adapter.LessonSermonAdapter;
import com.mich.gwan.shallom.dao.DatabaseHelper;
import com.mich.gwan.shallom.databinding.ActivityBibleLessonDaySummaryBinding;
import com.mich.gwan.shallom.helper.InputValidation;
import com.mich.gwan.shallom.helper.RecyclerTouchListener;
import com.mich.gwan.shallom.model.LessonWeek;
import com.mich.gwan.shallom.service.AsyncTaskExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LessonDaySummary extends AppCompatActivity implements View.OnClickListener {

    private ActivityBibleLessonDaySummaryBinding binding;

    private RecyclerView recyclerView;

    private ImageView searchIcon;
    private ImageView backIcon;

    private CardView filterCardView;
    private CardView searchCardView;

    private EditText filterEditText;

    private Toolbar toolbar;

    private TextView english, swahili, middleText;

    private AppCompatButton lastQuarter, firstQuarter, secondQuarter, thirdQuarter;

    private DatabaseHelper databaseHelper;
    private InputValidation inputValidation;

    private LessonSermonAdapter adapter;

    private List<LessonWeek> list, mainList;
    private Intent intent;
    private boolean isVisible = false;
    private String year, quarter, quarterId;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityBibleLessonDaySummaryBinding.inflate(getLayoutInflater());
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
        recyclerView = binding.recyclerViewLesson;
        searchIcon = binding.myToolbar.shapeableImageViewIcon;
        backIcon = binding.myToolbar.backIcon;
        filterCardView = binding.myToolbar.cardViewFilter;
        searchCardView = binding.myToolbar.cardViewSearch;
        filterEditText = binding.myToolbar.editTextFilter;
        middleText = binding.myToolbar.toolbarMiddleText;
        toolbar = binding.myToolbar.toolbarPayment;
        english = binding.textViewEnglish;
        swahili = binding.textViewKiswahili;
        lastQuarter = binding.buttonLastQuarter;
        firstQuarter = binding.buttonFirstQuarter;
        secondQuarter = binding.buttonSecondQuarter;
        thirdQuarter = binding.buttonThirdQuarter;
    }

    private void initObjects() {
        intent = getIntent();
        year = intent.getStringExtra("YEAR");
        quarter = intent.getStringExtra("QUARTER");
        quarterId = intent.getStringExtra("QUARTER_ID");
        databaseHelper = new DatabaseHelper(this);
        inputValidation = new InputValidation(this);
        list = new ArrayList<>();
        middleText.setText(year);

        if (!quarter.equals("NULL")) {
            updateView(firstQuarter, quarter);
            updateView(secondQuarter, quarter);
            updateView(thirdQuarter, quarter);
            updateView(lastQuarter, quarter);
        }

        getDataFromSQLite();
        mainList = getList("ENGLISH");
        adapter = new LessonSermonAdapter(mainList);

        LinearLayoutManager myLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void initListeners() {
        searchCardView.setOnClickListener(this);
        backIcon.setOnClickListener(this);
        english.setOnClickListener(this);
        swahili.setOnClickListener(this);
        lastQuarter.setOnClickListener(this);
        firstQuarter.setOnClickListener(this);
        secondQuarter.setOnClickListener(this);
        thirdQuarter.setOnClickListener(this);

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

                        if (mainList.get(position).getLessonLanguage().name().equals("KISWAHILI")){
                            int weekId = 0;
                            String lessonTitle = null;

                            // Get the week id
                            for (LessonWeek lessonWeek : list){
                                if (Objects.equals(lessonWeek.getLessonDate(), mainList.get(position).getLessonDate()) && lessonWeek.getLessonLanguage().name().equals("ENGLISH")) {
                                    weekId = lessonWeek.getLessonWeekId();
                                    lessonTitle = lessonWeek.getLessonTitle();
                                }
                            }

                            Intent intent = new Intent(view.getContext(), LessonQuestionsActivity.class);
                            intent.putExtra("WEEK_ID", weekId);
                            intent.putExtra("LESSON_TITLE", lessonTitle);
                            intent.putExtra("LESSON_DATE", mainList.get(position).getLessonDate());
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(view.getContext(), LessonQuestionsActivity.class);
                            intent.putExtra("WEEK_ID", mainList.get(position).getLessonWeekId());
                            intent.putExtra("LESSON_TITLE", mainList.get(position).getLessonTitle());
                            intent.putExtra("LESSON_DATE", mainList.get(position).getLessonDate());
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
    }

    private void filter(String text){
        List<LessonWeek> temp = new ArrayList<>();
        for(LessonWeek item: mainList){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(item.getLessonTitle().contains(text.toUpperCase())){
                temp.add(item);
            }
        }
        //update recyclerview
        adapter.updateList(temp);
    }


    private List<LessonWeek> getList(String language){
        List<LessonWeek> temp = new ArrayList<>();
        for (LessonWeek item : list){
            if (item.getLessonLanguage().name().equals(language)){
                temp.add(item);
            }
        }
        return temp;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cardViewSearch){
            if (!isVisible){
                isVisible = true;
                enableFilterMode();
                filterEditText.requestFocus();
                searchIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_close_green));
                WindowCompat.getInsetsController(getWindow(), filterEditText).show(WindowInsetsCompat.Type.ime());
                Toast.makeText(LessonDaySummary.this, "Filter mode enabled", Toast.LENGTH_LONG).show();
            } else {
                isVisible = false;
                enableFilterMode();
                searchIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_search_green));
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                adapter.updateList(list);
                Toast.makeText(LessonDaySummary.this, "Filter mode disabled", Toast.LENGTH_LONG).show();
            }

        } else if (v.getId() == R.id.backIcon) {
            finishAffinity();
        } else if (v.getId() == R.id.textViewKiswahili) {
            getList("KISWAHILI");
            adapter.notifyDataSetChanged();

            // Update View
            updateView(swahili, true);
            updateView(english, false);
        } else if (v.getId() == R.id.textViewEnglish) {
            adapter.updateList(getList("ENGLISH"));

            //Update View
            updateView(english, true);
            updateView(swahili, false);
        } else if (v.getId() == R.id.buttonFirstQuarter) {
            updateView(firstQuarter, true);
            updateView(secondQuarter, false);
            updateView(thirdQuarter, false);
            updateView(lastQuarter, false);

            // Data retrieval and update
            buttonAction(firstQuarter);
        } else if (v.getId() == R.id.buttonSecondQuarter) {
            updateView(firstQuarter, false);
            updateView(secondQuarter, true);
            updateView(thirdQuarter, false);
            updateView(lastQuarter, false);

            // Data retrieval and update
            buttonAction(secondQuarter);
        } else if (v.getId() == R.id.buttonLastQuarter) {
            updateView(firstQuarter, false);
            updateView(secondQuarter, false);
            updateView(thirdQuarter, false);
            updateView(lastQuarter, true);

            // Data retrieval and update
            buttonAction(lastQuarter);
        } else if (v.getId() == R.id.buttonThirdQuarter) {
            updateView(firstQuarter, false);
            updateView(secondQuarter, false);
            updateView(thirdQuarter, true);
            updateView(lastQuarter, false);

            // Data retrieval and update
            buttonAction(thirdQuarter);
        }
    }

    private void buttonAction(AppCompatButton button){
        // Check if the pressed quarter id present
        if (databaseHelper.checkLessonQuarter(year,button.getText().toString())) {
            // Set the list
            list = databaseHelper.getLessonWeek(databaseHelper.getLessonQuarterId(year, button.getText().toString()));
            // Update adapter
            adapter.updateList(getList("ENGLISH"));
        } else
            Toast.makeText(this, "The quarter " + button.getText().toString() + " is not available", Toast.LENGTH_LONG).show();
    }

    private void updateView(AppCompatButton button, boolean isSelected) {
        if (isSelected) {
            button.setTextColor(ContextCompat.getColor(this, R.color.green_bright));
            button.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.ic_green_border_rectangle));
        } else {
            button.setTextColor(ContextCompat.getColor(this, R.color.grey));
            button.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.ic_grey_border_rectangle));
        }
    }

    private void updateView(TextView textView, boolean isSelected){
        if (isSelected){
            textView.setTextColor(ContextCompat.getColor(this, R.color.white));
            textView.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_tilted_rectangle_green_border));
        } else {
            textView.setTextColor(ContextCompat.getColor(this, R.color.grey));
            textView.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_tilted_grey_rectangle));
        }
    }

    private void updateView(AppCompatButton button, String quarter){
        updateView(button, button.getText().toString().equals(quarter));
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
                list = databaseHelper.getLessonWeek(Integer.parseInt(quarterId));
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
