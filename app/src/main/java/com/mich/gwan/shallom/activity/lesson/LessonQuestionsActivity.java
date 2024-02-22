/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 13
 */

package com.mich.gwan.shallom.activity.lesson;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
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

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import com.mich.gwan.shallom.adapter.QuestionAdapter;
import com.mich.gwan.shallom.dao.DatabaseHelper;
import com.mich.gwan.shallom.databinding.ActivityBibleLessonQuestionLayoutBinding;
import com.mich.gwan.shallom.enums.Language;
import com.mich.gwan.shallom.helper.InputValidation;
import com.mich.gwan.shallom.helper.RecyclerTouchListener;
import com.mich.gwan.shallom.model.LessonQuestion;
import com.mich.gwan.shallom.model.LessonWeek;
import com.mich.gwan.shallom.service.AsyncTaskExecutorService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LessonQuestionsActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityBibleLessonQuestionLayoutBinding binding;

    private RecyclerView recyclerView;

    private ImageView searchIcon;
    private ImageView backIcon;

    private CardView filterCardView;
    private CardView searchCardView;

    private EditText filterEditText;

    private Toolbar toolbar;

    private TextView english;
    private TextView swahili;
    private TextView middleText;
    private TextView lessonTitle;
    private TextView scriptureReading;
    private TextView memoryVerse;

    private DatabaseHelper databaseHelper;
    private QuestionAdapter adapter;
    private InputValidation inputValidation;

    private List<LessonQuestion> list;
    private List<LessonQuestion> mainList;

    private Intent intent;
    private boolean isVisible = false;
    private boolean isEnglish = true;
    private String weekId;
    private String lessonTitleText;
    private String lessonDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBibleLessonQuestionLayoutBinding.inflate(getLayoutInflater());
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
        recyclerView = binding.recyclerViewQuestions;
        searchIcon = binding.myToolbar.shapeableImageViewIcon;
        backIcon = binding.myToolbar.backIcon;
        filterCardView = binding.myToolbar.cardViewFilter;
        searchCardView = binding.myToolbar.cardViewSearch;
        filterEditText = binding.myToolbar.editTextFilter;
        toolbar = binding.myToolbar.toolbarPayment;
        english = binding.textViewEnglish;
        swahili = binding.textViewKiswahili;
        middleText = binding.myToolbar.toolbarMiddleText;
        lessonTitle = binding.textViewLessonTitle;
        scriptureReading = binding.textViewScriptureReading;
        memoryVerse = binding.textViewMemoryVerse;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initObjects() {
        databaseHelper = new DatabaseHelper(this);
        inputValidation = new InputValidation(this);
        intent = getIntent();
        weekId = String.valueOf(intent.getStringExtra("WEEK_ID"));
        lessonTitleText = intent.getStringExtra("LESSON_TITLE");
        lessonDate = intent.getStringExtra("LESSON_DATE");
        list = new ArrayList<>();

        middleText.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("MMM, dd yyy")));
        System.out.println(weekId);
        System.out.println(lessonDate);
        System.out.println(lessonTitleText);
        list = databaseHelper.getLessonQuestion(Integer.parseInt(weekId));
        getDataFromSQLite();
        mainList = getList("ENGLISH");
        adapter = new QuestionAdapter(mainList);

        LinearLayoutManager myLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        // Default values for the text views - English is the default lang
        lessonTitle.setText(lessonTitleText);
        scriptureReading.setText(databaseHelper.getLessonReading(lessonDate, "ENGLISH"));
        memoryVerse.setText(databaseHelper.getLessonMemVerse(lessonDate, "ENGLISH"));
    }

    private void initListeners() {
        searchCardView.setOnClickListener(this);
        backIcon.setOnClickListener(this);
        english.setOnClickListener(this);
        swahili.setOnClickListener(this);

        // Handle the filtering action on the edit text
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

    /**
     * Updates the values that the user types on the filter edit text to the
     * adapter if the values match in the active list
     * @param text The text input from user
     */
    private void filter(String text){
        List<LessonQuestion> temp = new ArrayList<>();
        if (isEnglish)
            for (LessonQuestion item : getList(Language.ENGLISH.name())) {
                //or use .equal(text) with you want equal match
                //use .toLowerCase() for better matches
                if (item.getQuestion().toUpperCase().contains(text.toUpperCase()) || item.getQuestionRefBooks().toUpperCase().contains(text.toUpperCase()) ||
                        String.valueOf(item.getQuestionId()).toUpperCase().contains(text.toUpperCase()) || item.getExplanation().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(item);
                }
            }
        else
            for (LessonQuestion item : getList(Language.KISWAHILI.name())) {
                //or use .equal(text) with you want equal match
                //use .toLowerCase() for better matches
                if (item.getQuestion().toUpperCase().contains(text.toUpperCase()) || item.getQuestionRefBooks().toUpperCase().contains(text.toUpperCase()) ||
                        String.valueOf(item.getQuestionId()).toUpperCase().contains(text.toUpperCase()) || item.getExplanation().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(item);
                }
            }

        //update recyclerview
        adapter.updateList(temp);
    }

    /**
     * The method that switches the display between languages
     * @param language the language to be switched to
     * @return a list of the selected language
     */
    private List<LessonQuestion> getList(String language){
        List<LessonQuestion> temp = new ArrayList<>();
        for (LessonQuestion item : list){
            if (item.getLessonLanguage().name().equals(language)){
                temp.add(item);
            }
        }
        return temp;
    }

    /**
     * Handles onClick events for various views
     * @param v the clicked view
     */
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cardViewSearch){
            if (!isVisible){
                isVisible = true;
                enableFilterMode();

                updateView(searchIcon, getString(R.string.enable_filter_mode), false);
            } else {
                isVisible = false;
                enableFilterMode();

                updateView(searchIcon, getString(R.string.disable_filter_mode), true);
            }

        } else if (v.getId() == R.id.backIcon) {
            finish();

        } else if (v.getId() == R.id.textViewKiswahili) {
            // Update list with swahili data
            adapter.updateList(getList(Language.KISWAHILI.name()));
            adapter.notifyDataSetChanged();

            // Update language status
            isEnglish = false;

            // Set the values of the TextViews
            lessonTitle.setText(databaseHelper.getLessonTitle(lessonDate, Language.KISWAHILI.name()));
            scriptureReading.setText(databaseHelper.getLessonReading(lessonDate, Language.KISWAHILI.name()));
            memoryVerse.setText(databaseHelper.getLessonMemVerse(lessonDate, Language.KISWAHILI.name()));

            // Update View
            updateView(swahili, true);
            updateView(english, false);
        } else if (v.getId() == R.id.textViewEnglish) {
            // Update list with english data
            adapter.updateList(getList(Language.ENGLISH.name()));
            adapter.notifyDataSetChanged();

            // Update language status
            isEnglish = false;

            // Set the values of the text views
            lessonTitle.setText(lessonTitleText);
            scriptureReading.setText(databaseHelper.getLessonReading(lessonDate, Language.ENGLISH.name()));
            memoryVerse.setText(databaseHelper.getLessonMemVerse(lessonDate, Language.ENGLISH.name()));

            //Update View
            updateView(english, true);
            updateView(swahili, false);
        }
    }

    /**
     * Sets active textview with a green border and a grey background
     * while the inactive view with a grey border and a hollow background
     * @param textView Text view that is selected
     * @param isSelected status of view
     */
    private void updateView(TextView textView, boolean isSelected){
        if (isSelected){
            textView.setTextColor(ContextCompat.getColor(this, R.color.white));
            textView.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_tilted_rectangle_green_border));
        } else {
            textView.setTextColor(ContextCompat.getColor(this, R.color.grey));
            textView.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_tilted_grey_rectangle));
        }
    }

    /**
     * Alternates the icons of an imageview depending on the one that is active
     * @param imageView The image view whose value is altered
     * @param message The message to be displayed
     * @param isVisible visibility of the icon;
     */
    private void updateView(ImageView imageView, String message, boolean isVisible){
        if (isVisible){
            imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_search_green));
            InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

            // Update the right list of language
            if (!isEnglish) adapter.updateList(getList(Language.KISWAHILI.name()));
            else adapter.updateList(getList(Language.ENGLISH.name()));

            Toast.makeText(LessonQuestionsActivity.this, message, Toast.LENGTH_LONG).show();
        } else{
            filterEditText.requestFocus();
            imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_close_green));
            WindowCompat.getInsetsController(getWindow(), filterEditText).show(WindowInsetsCompat.Type.ime());
            Toast.makeText(LessonQuestionsActivity.this, message, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Enable filter mode
     * Makes the cardview holding the edit text visible
     */
    public void enableFilterMode() {
        if (isVisible) {
            filterCardView.setVisibility(View.VISIBLE);
        } else {
            filterCardView.setVisibility(View.GONE);
            inputValidation.hideKeyboardFrom(filterEditText);
        }
    }

    /**
     * Gets data from the database
     */
    private void getDataFromSQLite(){
        new AsyncTaskExecutorService<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void unused) {
                list.clear();
                list = databaseHelper.getLessonQuestion(Integer.parseInt(weekId));
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
