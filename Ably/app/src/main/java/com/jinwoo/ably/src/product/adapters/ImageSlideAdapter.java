package com.jinwoo.ably.src.product.adapters;

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

public class ImageSlideAdapter extends RecyclerView.Adapter<ImageSlideAdapter.SliderViewHolder> {

    private Context context;
    private ArrayList<String> imageUrls;

    public ImageSlideAdapter(Context context, ArrayList<String> imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(imageUrls.get(position));
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;

        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.slider_iv_image);
        }

        private void setImage(String image){
            Glide.with(context).load(image).into(this.image);
        }
    }
}