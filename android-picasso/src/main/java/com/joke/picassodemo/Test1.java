package com.joke.picassodemo;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Test1 {
    private ImageView mImageView;

    private String internetUrl = "http://i.imgur.com/DvpvklR.png";

    public Test1(ImageView imageView) {
        mImageView = imageView;
    }

    public void load(Context context){
        Picasso.with(context)
            .load(internetUrl)
            .into(mImageView);
    }
}
