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
import com.mich.gwan.shallom.databinding.RecyclerEventsBinding;
import com.mich.gwan.shallom.fragment.AnnouncementsFragment;
import com.mich.gwan.shallom.model.Event;

import java.text.MessageFormat;
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
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating recycler item view
        RecyclerEventsBinding binding = RecyclerEventsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.textViewEventStartDay.setText(list.get(position).getEventStartDate().format(DateTimeFormatter.ofPattern("dd")));
            holder.textViewEventStartMonth.setText(list.get(position).getEventStartDate().format(DateTimeFormatter.ofPattern("MMM")));
        }
        holder.textViewLocation.setText(list.get(position).getEventLocation());
        holder.textViewEventName.setText(list.get(position).getEventTitle());
        holder.textViewEventDescription.setText(list.get(position).getEventDescription());

        System.currentTimeMillis();
        //long time = list.get(position).getEventStartDate();
        holder.textViewEventDuration.setText(MessageFormat.format("Starts on {0} up to {1}", list.get(position).getEventStartDate(), list.get(position).getEventEndDate()));

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
                long weeks = remainingSeconds / (7 * 24 * 60 * 60);
                long days = (remainingSeconds % (7 * 24 * 60 * 60)) / (24 * 60 * 60);
                long hours = ((remainingSeconds % (7 * 24 * 60 * 60)) % (24 * 60 * 60)) / (60 * 60);
                long l = ((remainingSeconds % (7 * 24 * 60 * 60)) % (24 * 60 * 60)) % (60 * 60);
                long minutes = l / 60;
                long seconds = l % 60;

                // Update TextViews
                holder.textViewEventWeeks.setText(String.valueOf(days));
                holder.textViewEventDays.setText(String.valueOf(hours));
                holder.textViewEventHours.setText(String.valueOf(minutes));
                holder.textViewEventMinutes.setText(String.valueOf(seconds));
                holder.textViewEventSeconds.setText(String.valueOf(weeks));
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                // Handle countdown completion
                holder.textViewEventWeeks.setText("00");
                holder.textViewEventDays.setText("00");
                holder.textViewEventHours.setText("00");
                holder.textViewEventMinutes.setText("00");
                holder.textViewEventSeconds.setText("00");
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
        Log.v(EventAdapter.class.getSimpleName()," "+list.size());
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
        public TextView textViewEventSeconds;
        public RelativeLayout parentLayout;
        private final AnnouncementsFragment fragment;

        public ViewHolder(RecyclerEventsBinding binding){
            super(binding.getRoot());
            textViewEventStartDay = binding.textViewEventStartDay;
            textViewEventStartMonth = binding.textViewEventStartMonth;
            textViewLocation = binding.textViewEventLocation;
            textViewEventName = binding.textViewEventName;
            textViewEventDescription = binding.textViewEventDescription;
            textViewEventDuration = binding.textViewEventDuration;
            textViewEventWeeks = binding.textViewWeeks;
            textViewEventDays = binding.textViewDays;
            textViewEventHours = binding.textViewHours;
            textViewEventMinutes = binding.textViewMinutes;
            parentLayout = binding.relativeLayout;
            textViewEventSeconds = binding.textViewSeconds;
            fragment = new AnnouncementsFragment();
        }

    }
}
