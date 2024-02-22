/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 13
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
import androidx.recyclerview.widget.RecyclerView;

import com.mich.gwan.shallom.databinding.RecyclerLessonQuarterBinding;
import com.mich.gwan.shallom.databinding.RecyclerLessonQuestionsBinding;
import com.mich.gwan.shallom.model.LessonQuestion;
import com.mich.gwan.shallom.model.LessonWeek;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<LessonQuestion> list;
    Context context;


    public QuestionAdapter(List<LessonQuestion> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating recycler item view
        RecyclerLessonQuestionsBinding binding = RecyclerLessonQuestionsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        context = parent.getContext();

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textViewQuestionNumber.setText(String.valueOf(position + 1));
        //!(position + 1 < 11) ? String.valueOf(position + 1) : "0" + position + 1
        holder.textViewQuestion.setText(list.get(position).getQuestion());
        holder.textViewRefScripture.setText(list.get(position).getQuestionRefBooks());

        if (list.get(position).getExplanation().equals("NULL")){
            holder.textViewExplanationTitle.setVisibility(View.GONE);
            holder.textViewExplanation.setVisibility(View.GONE);
        } else
            holder.textViewExplanation.setText(list.get(position).getExplanation());
    }

    @Override
    public int getItemCount() {
        Log.v(QuestionAdapter.class.getSimpleName(),""+list.size());
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<LessonQuestion> listItem){
        list = listItem;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewQuestionNumber;
        public TextView textViewQuestion;
        public TextView textViewRefScripture;
        public TextView textViewExplanationTitle;
        public TextView textViewExplanation;

        public ViewHolder(RecyclerLessonQuestionsBinding binding){
            super(binding.getRoot());
            textViewQuestionNumber = binding.textViewQuestionNumber;
            textViewQuestion = binding.textViewQuestion;
            textViewRefScripture = binding.textViewRefScripture;
            textViewExplanationTitle = binding.textViewExplanationTitle;
            textViewExplanation = binding.textViewExplanation;
        }

    }
}