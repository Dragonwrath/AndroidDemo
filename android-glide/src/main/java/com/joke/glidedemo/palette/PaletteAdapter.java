package com.joke.glidedemo.palette;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.joke.glidedemo.R;

import java.util.List;

public class PaletteAdapter extends RecyclerView.Adapter<PaletteAdapter.ImageTextViewHolder> {

    private final BitmapRequestBuilder<String, PaletteBitmap> glideRequest;
    private final List<String> data;
    private final @ColorInt
    int defaultColor;
    public PaletteAdapter(Context context, RequestManager glide, List<String> data) {
        this.data = data;
        this.defaultColor = ContextCompat.getColor(context, R.color.default_background);
        this.glideRequest = glide
                .fromString()
                .asBitmap()
                .transcode(new PaletteBitmapTranscoder(context), PaletteBitmap.class)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        ;
    }
    @Override public int getItemCount() {
        return data.size();
    }
    @Override public ImageTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ImageTextViewHolder(view);
    }
    @Override public void onBindViewHolder(final ImageTextViewHolder holder, int position) {
        // reset color so it looks like the view was just inflated even if it was recycled
        // this is to prevent inheriting another position's colors
        holder.itemView.setBackgroundColor(defaultColor);
        String url = data.get(position);
        if (url != null) { // simulate an optional url from the data item
            holder.imageView.setVisibility(View.VISIBLE);
            glideRequest
                    .load(url)
                    .into(new ImageViewTarget<PaletteBitmap>(holder.imageView) {
                        @Override protected void setResource(PaletteBitmap resource) {
                            super.view.setImageBitmap(resource.bitmap);
                            int color = resource.palette.getVibrantColor(defaultColor);
                            holder.itemView.setBackgroundColor(color);
                        }
                    });
        } else {
            // clear when no image is shown, don't use holder.imageView.setImageDrawable(null) to do the same
            Glide.clear(holder.imageView);
            holder.imageView.setVisibility(View.GONE);
        }
    }
    @Override public void onViewRecycled(ImageTextViewHolder holder) {
        super.onViewRecycled(holder);
        // optional, but recommended way to clear up the resources used by Glide
        Glide.clear(holder.imageView);
    }
    static class ImageTextViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        public ImageTextViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.image_item);
        }
    }
}
