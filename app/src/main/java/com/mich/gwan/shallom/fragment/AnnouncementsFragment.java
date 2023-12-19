package com.mich.gwan.shallom.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mich.gwan.shallom.databinding.FragmentAnnouncementsBinding;

public class AnnouncementsFragment extends Fragment {

private FragmentAnnouncementsBinding binding;

    /** Accessing context **/
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentAnnouncementsBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }
}
