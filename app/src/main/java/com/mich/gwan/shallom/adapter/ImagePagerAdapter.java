/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 7
 */

package com.mich.gwan.shallom.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;

import com.mich.gwan.shallom.R;

import java.util.Objects;

public class ImagePagerAdapter extends PagerAdapter {

    private LayoutInflater mLayoutInflater;
    private Context context;
    private int[] images;
    private String[] urls;

    public ImagePagerAdapter(Context context, int[] images, String[] urls) {
        this.context = context;
        this.images = images;
        this.urls = urls;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull View container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_viewpager, (ViewGroup) container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewMain);
        imageView.setImageResource(images[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Set an OnClickListener to open the corresponding web page when clicked
        imageView.setOnClickListener(v -> {
            String url = urls[position]; // Retrieve URL corresponding to the clicked image
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(context, intent, null);
        });

        ((ViewPager) container).addView(itemView, 0);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull View container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
