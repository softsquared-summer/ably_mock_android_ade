package com.jinwoo.ably.src.product.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.main.data.SlideImage;
import java.util.ArrayList;

public class ImageSlideAdapter extends RecyclerView.Adapter<ImageSlideAdapter.SliderViewHolder> {

    private ArrayList<SlideImage> itemList;

    public ImageSlideAdapter(ArrayList<SlideImage> itemList) {
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

        private void setImage(SlideImage slideImage){
            // TODO: Bring url information from the slideBanner item and load the corresponding image from the network
            image.setImageResource(slideImage.getImage());
        }

    }
}