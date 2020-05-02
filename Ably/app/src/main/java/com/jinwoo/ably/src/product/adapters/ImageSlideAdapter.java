package com.jinwoo.ably.src.product.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jinwoo.ably.R;
import java.util.ArrayList;

public class ImageSlideAdapter extends RecyclerView.Adapter<ImageSlideAdapter.SliderViewHolder> {

    private ArrayList<Integer> images;

    public ImageSlideAdapter(ArrayList<Integer> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;

        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.slider_iv_image);
        }

        private void setImage(int image){
            // TODO: Bring url information from the slideBanner item and load the corresponding image from the network
            this.image.setImageResource(image);
        }
    }
}