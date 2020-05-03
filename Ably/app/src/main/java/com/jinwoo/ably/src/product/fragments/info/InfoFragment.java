package com.jinwoo.ably.src.product.fragments.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.jinwoo.ably.R;
import java.util.ArrayList;

public class InfoFragment extends Fragment {

    private ArrayList<String> images;

    public InfoFragment(ArrayList<String> images) { this.images = images; }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_info , container, false);

        ViewGroup imageContainer = view.findViewById(R.id.product_info_image_container);
        for(int i = 0; i < images.size(); i++) {
            ImageView image = new ImageView(getContext());
            Glide.with(getContext()).load(images.get(i)).into(image);
            imageContainer.addView(image);
        }

        return view;
    }
}
