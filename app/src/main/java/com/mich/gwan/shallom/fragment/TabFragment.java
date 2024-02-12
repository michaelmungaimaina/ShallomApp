package com.mich.gwan.shallom.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mich.gwan.shallom.R;

import java.util.Objects;

public class TabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager2 viewPager;
    public static int int_items = 4;
    private final int[] tabIcons = {
            R.drawable.ic_announcement_green,
            R.drawable.ic_group_green,
            R.drawable.ic_sermon_green,
            R.drawable.ic_ministry_green
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //this inflates out tab layout file.
        @SuppressLint("InflateParams") View x =  inflater.inflate(R.layout.tab_fragment_layout,null);
        // set up stuff.
        tabLayout = x.findViewById(R.id.tabs);
        viewPager = x.findViewById(R.id.viewpager);



        // create a new adapter for our pageViewer. This adapters returns child fragments as per the positon of the page Viewer.
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager(), getLifecycle()));

        //provide the viewPager to TabLayout.
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(getPageTitle(position))
        ).attach();
        setupTabIcons();
        //to preload the adjacent tabs. This makes transition smooth.
        viewPager.setOffscreenPageLimit(2);

        return x;
    }

    private String getPageTitle(int position) {
        switch (position){
            case 0 : return getString(R.string.announcement_text);
            case 1 : return getString(R.string.group_text);
            case 2 : return getString(R.string.sermon_text);
            case 3 : return getString(R.string.ministry_text);
            default: return null;
        }
    }
    private void setupTabIcons() {
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(tabIcons[0]);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(tabIcons[1]);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(tabIcons[2]);
        Objects.requireNonNull(tabLayout.getTabAt(3)).setIcon(tabIcons[3]);
    }
    static class MyAdapter extends FragmentStateAdapter {

        /**
         * Manage the lifecycle of each Fragment
         * @param fm fragment manager
         * @param lifecycle lifecycle
         */
        public MyAdapter(FragmentManager fm, Lifecycle lifecycle) {
            super(fm,lifecycle);
        }

        //return the fragment with respect to page position.
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0 : return new AnnouncementsFragment();
                case 1 : return new GroupsFragment();
                case 2 : return new SermonsFragment();
                case 3 : return new MinistriesFragment();
            }
            return new Fragment();
        }

        /**
         * Return number of fragments available
         * @return number of items
         */
        @Override
        public int getItemCount() {
            return int_items;
        }
    }
}
