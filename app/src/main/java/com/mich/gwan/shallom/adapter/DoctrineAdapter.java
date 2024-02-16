/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 14
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
import androidx.recyclerview.widget.RecyclerView;

import com.mich.gwan.shallom.databinding.RecyclerDoctrineBinding;
import com.mich.gwan.shallom.model.Doctrine;
import com.mich.gwan.shallom.model.LessonQuestion;

import java.util.List;

public class DoctrineAdapter extends RecyclerView.Adapter<DoctrineAdapter.ViewHolder> {
    private List<Doctrine> list;
    Context context;


    public DoctrineAdapter(List<Doctrine> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public DoctrineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        RecyclerDoctrineBinding binding = RecyclerDoctrineBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        context = parent.getContext();

        return new DoctrineAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textViewDoctrineNumber.setText(list.get(position).getDoctrineId());
        holder.textViewDoctrine.setText(list.get(position).getDoctrineContent());
        holder.textViewDoctrineDescription.setText(list.get(position).getDoctrineDescription());

        String[] books = list.get(position).getDoctrineRef().split(",");

        if (books.length > 0){
            switch (books.length){
                case 1:
                    holder.textViewBook1.setVisibility(View.VISIBLE);
                    holder.textViewBook1.setText(books[0]);
                    break;
                case 2:
                    holder.textViewBook1.setVisibility(View.VISIBLE);
                    holder.textViewBook1.setText(books[0]);
                    holder.textViewBook2.setVisibility(View.VISIBLE);
                    holder.textViewBook2.setText(books[1]);
                    break;
                case 3:
                    holder.textViewBook1.setVisibility(View.VISIBLE);
                    holder.textViewBook1.setText(books[0]);
                    holder.textViewBook2.setVisibility(View.VISIBLE);
                    holder.textViewBook2.setText(books[1]);
                    holder.textViewBook3.setVisibility(View.VISIBLE);
                    holder.textViewBook3.setText(books[2]);
                    break;
                case 4:
                    holder.textViewBook1.setVisibility(View.VISIBLE);
                    holder.textViewBook1.setText(books[0]);
                    holder.textViewBook2.setVisibility(View.VISIBLE);
                    holder.textViewBook2.setText(books[1]);
                    holder.textViewBook3.setVisibility(View.VISIBLE);
                    holder.textViewBook3.setText(books[2]);
                    holder.textViewBook4.setVisibility(View.VISIBLE);
                    holder.textViewBook4.setText(books[3]);
                    break;
                case 5:
                    holder.textViewBook1.setVisibility(View.VISIBLE);
                    holder.textViewBook1.setText(books[0]);
                    holder.textViewBook2.setVisibility(View.VISIBLE);
                    holder.textViewBook2.setText(books[1]);
                    holder.textViewBook3.setVisibility(View.VISIBLE);
                    holder.textViewBook3.setText(books[2]);
                    holder.textViewBook4.setVisibility(View.VISIBLE);
                    holder.textViewBook4.setText(books[3]);
                    holder.textViewBook5.setVisibility(View.VISIBLE);
                    holder.textViewBook5.setText(books[4]);
                    break;
                case 6:
                    holder.textViewBook1.setVisibility(View.VISIBLE);
                    holder.textViewBook1.setText(books[0]);
                    holder.textViewBook2.setVisibility(View.VISIBLE);
                    holder.textViewBook2.setText(books[1]);
                    holder.textViewBook3.setVisibility(View.VISIBLE);
                    holder.textViewBook3.setText(books[2]);
                    holder.textViewBook4.setVisibility(View.VISIBLE);
                    holder.textViewBook4.setText(books[3]);
                    holder.textViewBook5.setVisibility(View.VISIBLE);
                    holder.textViewBook5.setText(books[4]);
                    holder.textViewBook6.setVisibility(View.VISIBLE);
                    holder.textViewBook6.setText(books[5]);
                    break;
                case 7:
                    holder.textViewBook1.setVisibility(View.VISIBLE);
                    holder.textViewBook1.setText(books[0]);
                    holder.textViewBook2.setVisibility(View.VISIBLE);
                    holder.textViewBook2.setText(books[1]);
                    holder.textViewBook3.setVisibility(View.VISIBLE);
                    holder.textViewBook3.setText(books[2]);
                    holder.textViewBook4.setVisibility(View.VISIBLE);
                    holder.textViewBook4.setText(books[3]);
                    holder.textViewBook5.setVisibility(View.VISIBLE);
                    holder.textViewBook5.setText(books[4]);
                    holder.textViewBook6.setVisibility(View.VISIBLE);
                    holder.textViewBook6.setText(books[5]);
                    holder.textViewBook7.setVisibility(View.VISIBLE);
                    holder.textViewBook7.setText(books[6]);
                    break;
                case 8:
                    holder.textViewBook1.setVisibility(View.VISIBLE);
                    holder.textViewBook1.setText(books[0]);
                    holder.textViewBook2.setVisibility(View.VISIBLE);
                    holder.textViewBook2.setText(books[1]);
                    holder.textViewBook3.setVisibility(View.VISIBLE);
                    holder.textViewBook3.setText(books[2]);
                    holder.textViewBook4.setVisibility(View.VISIBLE);
                    holder.textViewBook4.setText(books[3]);
                    holder.textViewBook5.setVisibility(View.VISIBLE);
                    holder.textViewBook5.setText(books[4]);
                    holder.textViewBook6.setVisibility(View.VISIBLE);
                    holder.textViewBook6.setText(books[5]);
                    holder.textViewBook7.setVisibility(View.VISIBLE);
                    holder.textViewBook7.setText(books[6]);
                    holder.textViewBook8.setVisibility(View.VISIBLE);
                    holder.textViewBook8.setText(books[7]);
                    break;
                case 9:
                    holder.textViewBook1.setVisibility(View.VISIBLE);
                    holder.textViewBook1.setText(books[0]);
                    holder.textViewBook2.setVisibility(View.VISIBLE);
                    holder.textViewBook2.setText(books[1]);
                    holder.textViewBook3.setVisibility(View.VISIBLE);
                    holder.textViewBook3.setText(books[2]);
                    holder.textViewBook4.setVisibility(View.VISIBLE);
                    holder.textViewBook4.setText(books[3]);
                    holder.textViewBook5.setVisibility(View.VISIBLE);
                    holder.textViewBook5.setText(books[4]);
                    holder.textViewBook6.setVisibility(View.VISIBLE);
                    holder.textViewBook6.setText(books[5]);
                    holder.textViewBook7.setVisibility(View.VISIBLE);
                    holder.textViewBook7.setText(books[6]);
                    holder.textViewBook8.setVisibility(View.VISIBLE);
                    holder.textViewBook8.setText(books[7]);
                    holder.textViewBook9.setVisibility(View.VISIBLE);
                    holder.textViewBook9.setText(books[8]);
            }
        }
    }
    @Override
    public int getItemCount() {
        Log.v(DoctrineAdapter.class.getSimpleName(),""+list.size());
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<Doctrine> listItem){
        list = listItem;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewDoctrineNumber;
        public TextView textViewDoctrine;
        public TextView textViewDoctrineDescription;
        public TextView textViewBook1;
        public TextView textViewBook2;
        public TextView textViewBook3;
        public TextView textViewBook4;
        public TextView textViewBook5;
        public TextView textViewBook6;
        public TextView textViewBook7;
        public TextView textViewBook8;
        public TextView textViewBook9;

        public ViewHolder(RecyclerDoctrineBinding binding){
            super(binding.getRoot());
            textViewDoctrineNumber = binding.textViewDoctrineNumber;
            textViewDoctrine = binding.textViewDoctrine;
            textViewDoctrineDescription = binding.textViewDoctrineDescription;
            textViewBook1 = binding.textViewBookOne;
            textViewBook2 = binding.textViewBookTwo;
            textViewBook3 = binding.textViewBookThree;
            textViewBook4 = binding.textViewBookFour;
            textViewBook5 = binding.textViewBookFive;
            textViewBook6 = binding.textViewBookSix;
            textViewBook7 = binding.textViewBookSeven;
            textViewBook8 = binding.textViewBookEight;
            textViewBook9 = binding.textViewBookNine;
        }

    }
}
