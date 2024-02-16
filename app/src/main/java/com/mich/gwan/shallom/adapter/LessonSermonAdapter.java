/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 12
 */

package com.mich.gwan.shallom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.databinding.RecyclerLessonQuarterBinding;
import com.mich.gwan.shallom.model.LessonQuarter;
import com.mich.gwan.shallom.model.LessonWeek;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class LessonSermonAdapter extends RecyclerView.Adapter<LessonSermonAdapter.ViewHolder> {
    private List<LessonWeek> list;
    Context context;


    public LessonSermonAdapter(List<LessonWeek> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public LessonSermonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        RecyclerLessonQuarterBinding binding = RecyclerLessonQuarterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        context = parent.getContext();

        return new LessonSermonAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.textViewSermonDay.setText((CharSequence) DateTimeFormatter.ofPattern("DD").parse(list.get(position).getLessonDate()));
            holder.textViewSermonMonth.setText((CharSequence) DateTimeFormatter.ofPattern("MMM").parse(list.get(position).getLessonDate()));
        }
        holder.textViewSermonTitle.setText(list.get(position).getLessonTitle());
        holder.textViewMainScripture.setText(list.get(position).getLessonReading());
        holder.textViewMemoryVerse.setText(list.get(position).getMemoryVerse());
    }

    @Override
    public int getItemCount() {
        Log.v(LessonSermonAdapter.class.getSimpleName(),""+list.size());
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<LessonWeek> listItem){
        list = listItem;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewSermonDay;
        public TextView textViewSermonMonth;
        public TextView textViewSermonTitle;
        public TextView textViewMainScripture;
        public TextView textViewMemoryVerse;

        public ViewHolder(RecyclerLessonQuarterBinding binding){
            super(binding.getRoot());
            textViewSermonDay = binding.textViewSermonDay;
            textViewSermonMonth = binding.textViewSermonMonth;
            textViewSermonTitle = binding.textViewSermonTitle;
            textViewMainScripture = binding.textViewMainScripture;
            textViewMemoryVerse = binding.textViewMemoryVerse;
        }

    }
}
