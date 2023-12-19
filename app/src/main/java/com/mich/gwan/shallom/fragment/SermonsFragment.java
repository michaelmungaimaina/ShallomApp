package com.mich.gwan.shallom.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mich.gwan.shallom.databinding.FragmentSermonsBinding;

public class SermonsFragment extends Fragment {
    private FragmentSermonsBinding binding;

    public void onAttach(Context context){
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentSermonsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}
