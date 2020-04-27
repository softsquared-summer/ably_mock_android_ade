package com.jinwoo.ably.src.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.jinwoo.ably.R;

public class SlideAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    public int[] images = {
        R.drawable.img_banner1,
        R.drawable.img_banner2,
        R.drawable.img_banner3,
        R.drawable.img_banner4,
        R.drawable.img_banner5
    };

    public SlideAdapter (Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.item_banner_slider, container, false);
        ImageView image = (ImageView) view.findViewById(R.id.banner_iv_image);
        image.setImageResource(images[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}