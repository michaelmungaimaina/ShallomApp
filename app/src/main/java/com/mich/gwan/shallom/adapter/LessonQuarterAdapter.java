/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 12
 */

package com.mich.gwan.shallom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.activity.lesson.LessonDaySummary;
import com.mich.gwan.shallom.activity.lesson.LessonHomeActivity;
import com.mich.gwan.shallom.dao.DatabaseHelper;
import com.mich.gwan.shallom.fragment.AnnouncementsFragment;
import com.mich.gwan.shallom.helper.RecyclerTouchListener;
import com.mich.gwan.shallom.model.Event;
import com.mich.gwan.shallom.model.LessonQuarter;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LessonQuarterAdapter extends RecyclerView.Adapter<LessonQuarterAdapter.ViewHolder> {

    private List<LessonQuarter> list;
    private int index = RecyclerView.NO_POSITION;

    private DatabaseHelper databaseHelper;
    private LinearLayoutManager linearLayoutManager;
    private QuartersAdapter adapter;

    private List<String> quarterList;
    Context context;


    public LessonQuarterAdapter(List<LessonQuarter> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public LessonQuarterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_bible_lesson, parent, false);
        context = parent.getContext();

        return new LessonQuarterAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textViewYear.setText(list.get(position).getQuarterYear());

        databaseHelper = new DatabaseHelper(context);
        linearLayoutManager = new LinearLayoutManager(context);
        quarterList = databaseHelper.getLessonQuarters(list.get(position).getQuarterYear());
        adapter = new QuartersAdapter(quarterList);

        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.recyclerView.setItemAnimator(new DefaultItemAnimator());
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setAdapter(adapter);

       holder.recyclerView.addOnItemTouchListener(new RecyclerTouchListener(context, holder.recyclerView,
               new RecyclerTouchListener.ClickListener() {
                   @Override
                   public void onClick(View view, int pos) {
                       Context context = view.getContext();
                       Intent intent = new Intent(context, LessonDaySummary.class);
                       intent.putExtra("YEAR", list.get(position).getQuarterYear());
                       intent.putExtra("QUARTER_ID", list.get(position).getQuarterId());
                       intent.putExtra("QUARTER", quarterList.get(pos));
                       context.startActivity(intent);
                   }

                   @Override
                   public void onLongClick(View view, int position) {

                   }
               }));


        holder.itemView.setSelected(index == position);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean onLongClick(View view) {
                index = holder.getLayoutPosition();
                notifyDataSetChanged();
                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                index = holder.getLayoutPosition();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.v(EventAdapter.class.getSimpleName(),""+list.size());
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<LessonQuarter> listItem){
        list = listItem;
        notifyDataSetChanged();
    }


    /**
     * ViewHolder class
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewYear;
        public RecyclerView recyclerView;

        public ConstraintLayout parentLayout;
        private final LessonHomeActivity activity;

        public ViewHolder(View view){
            super(view);
            textViewYear = view.findViewById(R.id.textViewYear);
            recyclerView = view.findViewById(R.id.recyclerQuarters);

            parentLayout = view.findViewById(R.id.constraintLayoutMain);
            activity = new LessonHomeActivity();
        }

    }
}

