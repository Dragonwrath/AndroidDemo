package com.joke.widget.android.widget.image;

import android.content.Context;
import android.graphics.Matrix;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.RemoteViews.RemoteView;

@RemoteView
public class CustomImageView extends View {

    // settable by the client
    private Uri mUri;
    private int mResource = 0;
    private Matrix mMatrix;
    private ScaleType mScaleType;
    private boolean mHaveFrame = false;
    private boolean mAdjustViewBounds = false;
    private int mMaxWidth = Integer.MAX_VALUE;
    private int mMaxHeight = Integer.MAX_VALUE;


    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context,AttributeSet attrs,int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
