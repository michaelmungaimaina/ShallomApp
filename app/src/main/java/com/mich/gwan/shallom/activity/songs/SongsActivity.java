/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 12
 */

/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 7
 */

package com.mich.gwan.shallom.activity.songs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.activity.lesson.LessonDaySummary;
import com.mich.gwan.shallom.databinding.ActivitySongHomeLayoutBinding;

import java.nio.Buffer;

public class SongsActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySongHomeLayoutBinding binding;

    private ImageView imageViewWorship;
    private ImageView imageViewPraise;

    private AppCompatButton buttonAllSongs;
    private AppCompatButton buttonGroupSongs;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivitySongHomeLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
        initListeners();

        setSupportActionBar(toolbar);
    }

    private void initViews() {
        imageViewWorship = binding.imageViewWorship;
        imageViewPraise = binding.imageViewPraise;
        buttonAllSongs = binding.buttonAllSongs;
        buttonGroupSongs = binding.buttonLogGroupSongs;
        toolbar = binding.myToolbar.toolbarPayment;
    }

    private void initListeners() {
        imageViewWorship.setOnClickListener(this);
        imageViewPraise.setOnClickListener(this);
        buttonAllSongs.setOnClickListener(this);
        buttonGroupSongs.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageViewWorship){
            Intent intent = new Intent(this, SongListActivity.class);
            intent.putExtra("CATEGORY", "WORSHIP");
            startActivity(intent);
        } else if (v.getId() ==  R.id.imageViewPraise) {
            Intent intent = new Intent(this, SongListActivity.class);
            intent.putExtra("CATEGORY", "PRAISE");
            startActivity(intent);
        } else if (v.getId() == R.id.buttonAllSongs) {
            Intent intent = new Intent(this, SongListActivity.class);
            intent.putExtra("CATEGORY", "NULL");
            startActivity(intent);
        } else if (v.getId() == R.id.buttonLogGroupSongs) {
            Intent intent = new Intent(this, SongGroupsActivity.class);
            startActivity(intent);
        }
    }
}
