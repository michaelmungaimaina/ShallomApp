package com.mich.gwan.shallom.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.mich.gwan.shallom.R;
import com.mich.gwan.shallom.adapter.GroupAdapter;
import com.mich.gwan.shallom.databinding.FragmentGroupsBinding;
import com.mich.gwan.shallom.helper.RecyclerTouchListener;
import com.mich.gwan.shallom.model.Group;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GroupsFragment extends Fragment {
    private FragmentGroupsBinding binding;
    private LinearLayoutCompat linearLayoutCompat;
    private TextView groupIntroduction;
    private RecyclerView recyclerViewGroups;
    private List<Group> list;
    private GroupAdapter adapter;
    private Group group;
    private Context context;
    private LinearLayoutManager linearLayoutManager;

    /** Accessing context **/
    @Override
    public void onAttach(@NonNull Context mContext){
        super.onAttach(mContext);
        context = mContext;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentGroupsBinding.inflate(inflater, container, false);

        group = new Group();
        list = new ArrayList<>();
        group.setGroupAcronym("COGYOK");
        group.setGroupFullName("C. O. G. Youth Organisation of Kenya");
        group.setGroupDescription("This is the a group of youth unmarried members");
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_ministry_green);
        assert drawable != null;
        group.setGroupImage(((BitmapDrawable) drawable).getBitmap());

        //list.set(0, group);
        list.add(group);
        initViews();
        initObjects();
        initListeners();
        return binding.getRoot();
    }

    private void initViews() {
        groupIntroduction = binding.textViewIntroduction;
        recyclerViewGroups = binding.recyclerViewGroups;
        linearLayoutCompat = binding.linearLayoutCompatGroupFrag;
    }

    private void initObjects() {
        adapter = new GroupAdapter(list);
        linearLayoutManager = new LinearLayoutManager(context);

        recyclerViewGroups.setLayoutManager(linearLayoutManager);
        recyclerViewGroups.setItemAnimator(new DefaultItemAnimator());
        recyclerViewGroups.setHasFixedSize(true);
        recyclerViewGroups.setAdapter(adapter);

        //list.set(0, group);
        list.add(group);
        adapter.updateList(list);
    }

    private void initListeners() {
        recyclerViewGroups.addOnItemTouchListener(new RecyclerTouchListener(context, recyclerViewGroups,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        Snackbar snackbar = Snackbar.make(linearLayoutCompat, list.get(position).getGroupAcronym() + " Selected", Snackbar.LENGTH_LONG);
                                snackbar.setAction("CLOSE", v -> snackbar.dismiss())
                                .setActionTextColor(ContextCompat.getColor(context, R.color.red_400))
                                .show();
                    }
                }));
    }
}
