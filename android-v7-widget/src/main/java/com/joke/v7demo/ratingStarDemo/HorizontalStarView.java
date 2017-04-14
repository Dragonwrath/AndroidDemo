package com.joke.v7demo.ratingStarDemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class HorizontalStarView extends Button {

    public HorizontalStarView(Context context) {
        this(context,null);
    }

    public HorizontalStarView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HorizontalStarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
