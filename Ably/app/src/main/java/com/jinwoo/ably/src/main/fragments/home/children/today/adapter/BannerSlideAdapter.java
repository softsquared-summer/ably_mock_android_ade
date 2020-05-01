package com.jinwoo.ably.src.main.fragments.home.children.today.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.jinwoo.ably.R;
import java.util.ArrayList;

public class BannerSlideAdapter extends RecyclerView.Adapter<BannerSlideAdapter.SliderViewHolder> {

    private Context context;
    private ArrayList<String> itemList;

    public BannerSlideAdapter(Context context, ArrayList<String> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;

        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.slider_iv_image);
        }

        private void setImage(String url){
            Glide.with(context).load(url).into(image);
        }

    }
}