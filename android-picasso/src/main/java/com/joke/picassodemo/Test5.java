package com.joke.picassodemo;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.IOException;

public class Test5 extends BaseClass {

    public Test5(Context context) {
        super(context);
        if (context instanceof Activity) {
            Activity act = (Activity) context;
            ImageView imageView = (ImageView) act.findViewById(R.id.target);
            Picasso
                    .with(context)
                    .load(Test2.eatFoodyImages[0])
                    .transform(new BlurTransformation(context))
                    .into(imageView);
        }
    }

    public Test5(Context context ,Picasso picasso) {
        super(context);
        picasso.load(Test2.eatFoodyImages[0])
                .transform(new GrayscaleTransformation(context,picasso))
                .into((ImageView) ((Activity)context).findViewById(R.id.target));
    }

}
class BlurTransformation implements Transformation{
    private Context mContext;
    public BlurTransformation(Context context) {
        mContext = context;
    }

    @Override
    public Bitmap transform(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            RenderScript rs = RenderScript.create(mContext);
            // Create another bitmap that will hold the results of the filter.
            Bitmap blurredBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

            // Allocate memory for Renderscript to work with
            Allocation input = Allocation.createFromBitmap(rs,
                    blurredBitmap,
                    Allocation.MipmapControl.MIPMAP_FULL,
                    Allocation.USAGE_SHARED);
            Allocation output = Allocation.createTyped(rs,
                    input.getType());

            // Load up an instance of the specific script that we want to use.
            ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setInput(input);

            // Set the blur radius
            script.setRadius(10);

            // Start the ScriptIntrinisicBlur
            script.forEach(output);

            // Copy the output to the blurred bitmap
            output.copyTo(blurredBitmap);

            bitmap.recycle();

            return blurredBitmap;
        }
        return null;
    }

    @Override
    public String key() {
        return "blur";
    }
}
class GrayscaleTransformation implements Transformation {

    private final Picasso picasso;
    private final Context context;

    public GrayscaleTransformation(Context context,Picasso picasso) {
        this.context = context;
        this.picasso = picasso;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap result  = Bitmap.createBitmap(source.getWidth(), source.getHeight(), source.getConfig());
        Bitmap noise;
        try {
            noise = picasso.load(Test2.eatFoodyImages[0]).get();
        } catch (IOException e) {
            throw new RuntimeException("Failed to apply transformation! Missing resource.");
        }

        BitmapShader shader = new BitmapShader(noise, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColorFilter(filter);

        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(source, 0, 0, paint);

        paint.setColorFilter(null);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));

        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);

        source.recycle();
        noise.recycle();

        return result;
    }

    @Override
    public String key() {
        return "grayscaleTransformation()";
    }
}