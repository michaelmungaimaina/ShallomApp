/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 15
 */

package com.mich.gwan.shallom.activity.songs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.dao.DatabaseHelper;
import com.mich.gwan.shallom.databinding.ActivitySongViewLayoutBinding;
import com.mich.gwan.shallom.enums.Preference;
import com.mich.gwan.shallom.helper.InputValidation;
import com.mich.gwan.shallom.model.SongChorus;
import com.mich.gwan.shallom.model.SongStanza;
import com.mich.gwan.shallom.service.AsyncTaskExecutorService;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class SongViewActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySongViewLayoutBinding binding;

    private ImageView backIcon;
    private ImageView shareIcon;
    private ImageView playIcon;
    private ImageView favouriteIcon;

    private RecyclerView recyclerView;

    private TextView chorus;
    private TextView stanza;

    private Toolbar toolbar;

    private DatabaseHelper databaseHelper;
    private InputValidation inputValidation;

    private List<SongChorus> listChorus;
    private List<SongStanza> listStanza;

    private String songId;
    private String songAuthor;

    private Intent intent;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

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
        shareIcon = binding.myToolbar.imageViewShare;
        playIcon = binding.myToolbar.imageViewPlay;
        favouriteIcon = binding.myToolbar.imageViewFovourite;
        recyclerView = binding.recyclerViewStanzas;
        chorus = binding.textViewChorus;
        stanza = binding.textViewStanzas;
        toolbar = binding.myToolbar.toolbarPayment;
    }

    private void initObjects() {
        intent = getIntent();
        songAuthor = intent.getStringExtra("SONG_AUTHOR");
        songId = intent.getStringExtra("SONG_ID");
        databaseHelper = new DatabaseHelper(this);
        inputValidation = new InputValidation(this);
        listChorus = new ArrayList<>();
        listStanza = new ArrayList<>();

        // Update View
        updateView();

        // Get Stanzas
        getDataFromSQLite(songId);

        // Get Chorus(es)
        listChorus = databaseHelper.getChoruses(Integer.parseInt(songId));

        // Display Stanzas
        displayStanza();

        // Display Chorus(es)
        displayChorus();
    }

    private void displayChorus() {
        int count = 0;
        if (listChorus.size() > 1){
            for (SongChorus item : listChorus){
                chorus.setText(MessageFormat.format("Chorus 0{0}\n{1}\n", count, item.getChorusContent()));
                count++;
            }
        } else
            chorus.setText(listChorus.get(0).getChorusContent());
    }

    private void displayStanza() {
        int count = 0;
        for (SongStanza item : listStanza){
            stanza.setText(MessageFormat.format("Stanza 0{0}\n{1}\n", count, item.getStanzaContent()));
            count++;
        }
    }

    private void initListeners() {
        backIcon.setOnClickListener(this);
        shareIcon.setOnClickListener(this);
        playIcon.setOnClickListener(this);
        favouriteIcon.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backIcon){
            finishAffinity();
        } else if (v.getId() == R.id.imageViewShare) {
            // Share data
            Toast.makeText(this, R.string.coming_soon, Toast.LENGTH_LONG).show();
        } else if (v.getId() == R.id.imageViewPlay) {
            // Play audio
            Toast.makeText(this, R.string.coming_soon, Toast.LENGTH_LONG).show();
        } else if (v.getId() == R.id.imageViewFovourite) {
            if (!databaseHelper.checkSongPref(songId).equals(String.valueOf(Preference.FAVOURITE)))
                databaseHelper.updateSong(songId, Preference.FAVOURITE);
            else
                Toast.makeText(this, R.string.already_favourite, Toast.LENGTH_LONG).show();

            updateView();
        }
    }

    /**
     * Update the icons
     */
    private void updateView(){
        if (databaseHelper.checkSongPref(songId).equals(String.valueOf(Preference.FAVOURITE)))
            favouriteIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favourite_selected_green));
        else
            favouriteIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favourite_uselected_green));
    }

    private void getDataFromSQLite(String songId){
        new AsyncTaskExecutorService<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void unused) {
                listStanza.clear();
                listStanza = databaseHelper.getStanza(Integer.parseInt(songId));
                return null;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void onPostExecute(Void unused) {
            }
        };
    }

}
