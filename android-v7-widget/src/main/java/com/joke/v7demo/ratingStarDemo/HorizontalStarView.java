package com.joke.v7demo.ratingStarDemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;


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
