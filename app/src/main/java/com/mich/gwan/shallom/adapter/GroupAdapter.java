/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 9
 */

package com.mich.gwan.shallom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.fragment.GroupsFragment;
import com.mich.gwan.shallom.model.Group;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {
    private final List<Group> list;
    private int index = RecyclerView.NO_POSITION;
    private Context context;
    private boolean status = true;

    public GroupAdapter(List<Group> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_events, parent, false);
        context = parent.getContext();

        return new GroupAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textViewAcronym.setText(list.get(position).getGroupAcronym());
        holder.textViewDescription.setText(list.get(position).getGroupDescription());
        holder.textViewTopText.setText(list.get(position).getGroupFullName());
        holder.imageView.setImageBitmap(list.get(position).getGroupImage());

        holder.cardViewDescription.setOnClickListener(v -> {
            if (status)
                holder.textViewDescription.setVisibility(View.VISIBLE);
            else
                holder.textViewDescription.setVisibility(View.GONE);
            holder.imageView.setRotation(180);
        });


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
        Log.v(GroupAdapter.class.getSimpleName(),""+list.size());
        return list.size();
    }


    /**
     * ViewHolder class
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewAcronym;
        public TextView textViewDescription;
        public TextView textViewTopText;
        public ImageView imageView;
        public CardView cardViewDescription;
        public LinearLayoutCompat parentLayout;
        private final GroupsFragment fragment;

        public ViewHolder(View view){
            super(view);
            textViewAcronym = view.findViewById(R.id.textViewGroupAcronym);
            textViewDescription = view.findViewById(R.id.textViewGroupDescription);
            textViewTopText = view.findViewById(R.id.textViewGroupAcronymTopText);
            imageView = view.findViewById(R.id.imageViewAcronymImage);
            cardViewDescription = view.findViewById(R.id.cardViewDescription);
            parentLayout = view.findViewById(R.id.linearLayoutCompatGroup);
            fragment = new GroupsFragment();
        }

    }
}

