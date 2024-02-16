/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 15
 */

package com.mich.gwan.shallom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mich.gwan.shallom.dao.DatabaseHelper;
import com.mich.gwan.shallom.databinding.RecyclerSongBinding;
import com.mich.gwan.shallom.model.Song;

import java.util.List;

public class AdapterSong extends RecyclerView.Adapter<AdapterSong.ViewHolder> {
    private List<Song> list;
    Context context;

    private DatabaseHelper databaseHelper;

    public AdapterSong(List<Song> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public AdapterSong.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        RecyclerSongBinding binding = RecyclerSongBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        context = parent.getContext();

        return new AdapterSong.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textViewInitial.setText(list.get(position).getSongTitle().charAt(0));
        databaseHelper = new DatabaseHelper(context);
        holder.textViewTitle.setText(list.get(position).getSongTitle());
        holder.textViewChorus.setText(databaseHelper.getChorus(list.get(position).getSongId()));
    }

    @Override
    public int getItemCount() {
        Log.v(AdapterSong.class.getSimpleName(),""+list.size());
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<Song> listItem){
        list = listItem;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewInitial;
        public TextView textViewTitle;
        public TextView textViewChorus;

        public ViewHolder(RecyclerSongBinding binding){
            super(binding.getRoot());
            textViewInitial = binding.textViewInitial;
            textViewTitle = binding.textViewSongTitle;
            textViewChorus = binding.textViewSongChorus;
        }

    }
}