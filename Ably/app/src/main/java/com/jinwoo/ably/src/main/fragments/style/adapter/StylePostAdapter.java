package com.jinwoo.ably.src.main.fragments.style.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.main.fragments.style.data.StylePost;
import java.util.ArrayList;

public class StylePostAdapter extends RecyclerView.Adapter<StylePostAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<StylePost> items;
    private LayoutInflater inflater;

    public StylePostAdapter(Context context, ArrayList<StylePost> items) {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_style, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        StylePost item = items.get(position);

        Glide.with(context).load(item.getImage()).into(holder.image);
        holder.uploader.setText(item.getUploader());
        holder.title.setText(item.getTitle());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() { return (items != null ? items.size() : 0); }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView uploader, title;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image      = itemView.findViewById(R.id.item_style_iv_image);
            this.uploader   = itemView.findViewById(R.id.item_style_tv_uploader);
            this.title      = itemView.findViewById(R.id.item_style_tv_title);
        }
    }
}
