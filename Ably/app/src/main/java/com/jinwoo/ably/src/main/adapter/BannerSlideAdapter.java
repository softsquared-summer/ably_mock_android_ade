package com.jinwoo.ably.src.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jinwoo.ably.R;
import com.jinwoo.ably.src.main.data.SlideBanner;
import java.util.ArrayList;

public class BannerSlideAdapter extends RecyclerView.Adapter<BannerSlideAdapter.SliderViewHolder> {

    private ArrayList<SlideBanner> itemList;

    public BannerSlideAdapter(ArrayList<SlideBanner> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider_banner, parent, false));
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
            image = (ImageView) itemView.findViewById(R.id.banner_iv_image);
        }

        private void setImage(SlideBanner slideBanner){
            // TODO: Bring url information from the slideBanner item and load the corresponding image from the network
            image.setImageResource(slideBanner.getImage());
        }

    }
}