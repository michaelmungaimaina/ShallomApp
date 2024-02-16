/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 7
 */

package com.mich.gwan.shallom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.fragment.AnnouncementsFragment;
import com.mich.gwan.shallom.model.Event;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private final List<Event> list;
    private int index = RecyclerView.NO_POSITION;
    Context context;


    public EventAdapter(List<Event> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_events, parent, false);
        context = parent.getContext();

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.textViewEventStartDay.setText(list.get(position).getEventStartDate().format(DateTimeFormatter.ofPattern("DD")));
            holder.textViewEventStartMonth.setText(list.get(position).getEventStartDate().format(DateTimeFormatter.ofPattern("MMM")));
        }
        holder.textViewLocation.setText(list.get(position).getEventLocation());
        holder.textViewEventName.setText(list.get(position).getEventTitle());
        holder.textViewEventDescription.setText(list.get(position).getEventDescription());

        System.currentTimeMillis();
        //long time = list.get(position).getEventStartDate();
        holder.textViewEventDuration.setText(list.get(position).getEventLocation());

        long targetMillisec = 0;
        // Set target date and time
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            targetMillisec = list.get(position).getEventEndDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }

        // Start countdown timer
        new CountDownTimer(targetMillisec - System.currentTimeMillis(), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long remainingSeconds = millisUntilFinished / 1000;

                // Calculate remaining days, hours, minutes, and seconds
                long days = remainingSeconds / (24 * 60 * 60);
                long hours = (remainingSeconds % (24 * 60 * 60)) / (60 * 60);
                long minutes = ((remainingSeconds % (24 * 60 * 60)) % (60 * 60)) / 60;
                long seconds = ((remainingSeconds % (24 * 60 * 60)) % (60 * 60)) % 60;

                // Update TextViews
                holder.textViewEventWeeks.setText(String.valueOf(days));
                holder.textViewEventDays.setText(String.valueOf(hours));
                holder.textViewEventHours.setText(String.valueOf(minutes));
                holder.textViewEventMinutes.setText(String.valueOf(seconds));
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                // Handle countdown completion
                holder.textViewEventWeeks.setText("00");
                holder.textViewEventDays.setText("00");
                holder.textViewEventHours.setText("00");
                holder.textViewEventMinutes.setText("00");
            }
        }.start();


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


    /**
     * ViewHolder class
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewEventStartDay;
        public TextView textViewEventStartMonth;
        public TextView textViewLocation;
        public TextView textViewEventName;
        public TextView textViewEventDescription;
        public TextView textViewEventDuration;
        public TextView textViewEventWeeks;
        public TextView textViewEventDays;
        public TextView textViewEventHours;
        public TextView textViewEventMinutes;
        public RelativeLayout parentLayout;
        private final AnnouncementsFragment fragment;

        public ViewHolder(View view){
            super(view);
            textViewEventStartDay = view.findViewById(R.id.textViewEventStartDay);
            textViewEventStartMonth = view.findViewById(R.id.textViewEventStartMonth);
            textViewLocation = view.findViewById(R.id.textViewLocation);
            textViewEventName = view.findViewById(R.id.textViewEventName);
            textViewEventDescription = view.findViewById(R.id.textViewEventDescription);
            textViewEventDuration = view.findViewById(R.id.textViewEventDuration);
            textViewEventWeeks = view.findViewById(R.id.textViewWeeks);
            textViewEventDays = view.findViewById(R.id.textViewDays);
            textViewEventHours = view.findViewById(R.id.textViewHours);
            textViewEventMinutes = view.findViewById(R.id.textViewMinutes);
            parentLayout = view.findViewById(R.id.relativeLayout);
            fragment = new AnnouncementsFragment();
        }

    }
}
