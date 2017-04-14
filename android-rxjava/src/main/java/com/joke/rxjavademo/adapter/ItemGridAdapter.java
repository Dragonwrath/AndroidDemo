package com.joke.rxjavademo.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joke.rxjavademo.R;
import com.joke.rxjavademo.model.Item;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Item>  items;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DebounceViewHolder viewHolder = (DebounceViewHolder) holder;
        Item item = items.get(position);
        ImageView imageIv = viewHolder.imageIv;
        Glide.with(imageIv.getContext()).load(item.imageUrl).into(imageIv);
        viewHolder.descriptionTv.setText(item.description);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setItems(List<Item> images) {
        items = images;
        notifyDataSetChanged();
    }



    static class DebounceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageIv)
        ImageView imageIv;
        @BindView(R.id.descriptionTv)
        TextView descriptionTv;
        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
