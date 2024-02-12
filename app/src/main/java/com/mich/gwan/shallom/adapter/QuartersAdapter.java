/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 12
 */

package com.mich.gwan.shallom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.activity.lesson.LessonHomeActivity;
import com.mich.gwan.shallom.dao.DatabaseHelper;
import com.mich.gwan.shallom.model.LessonQuarter;

import java.util.List;

public class QuartersAdapter extends RecyclerView.Adapter<QuartersAdapter.ViewHolder> {
    private final List<String> list;
    Context context;


    public QuartersAdapter(List<String> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public QuartersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_quarters, parent, false);
        context = parent.getContext();

        return new QuartersAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textViewQuarter.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        Log.v(QuartersAdapter.class.getSimpleName(),""+list.size());
        return list.size();
    }


    /**
     * ViewHolder class
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewQuarter;

        public ViewHolder(View view){
            super(view);
            textViewQuarter = view.findViewById(R.id.textViewQuarter);
        }

    }
}