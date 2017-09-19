package android.graphics.colormatrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.marshon.approotdemo.R;
import android.util.AttributeSet;
import android.view.View;


public class BitmapMeshView extends View {
    public Bitmap bitmap;
    private int HEIGHT,WIDTH;
    private float[] orig,verts;
    private int A;

    public BitmapMeshView(Context context) {
        this(context, null, 0);
    }

    public BitmapMeshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapMeshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        HEIGHT = getMeasuredHeight();
        WIDTH = getMeasuredWidth();
        orig = new float[(HEIGHT + 1) * (WIDTH +1) * 2];
        verts = new float[(HEIGHT + 1) * (WIDTH +1) * 2];
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.duel);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void calculateBitmap() {
        float bitmapWidth = bitmap.getWidth();
        float bitmapHeight = bitmap.getHeight();
        int index = 0;
        for (int y = 0; y <= HEIGHT; y++) {
            float fy = bitmapHeight * y /HEIGHT;
            for (int x = 0; x <= WIDTH; x++) {
                float fx = bitmapHeight * y / HEIGHT;
                orig[index * 2] = verts[index * 2] = fx;
                orig[index * 2 + 1] = verts[index * 2 + 1] = fy + 100;
            }
        }
    }

    private void flagWave() {
        for (int j = 0; j <= HEIGHT; j++) {
            for (int i = 0; i <= WIDTH ; i++) {
                verts[(j * (WIDTH + 1) + i) * 2] += 0 ;
                float offsetY = (float) Math.sin((float)i / WIDTH * 2 * Math.PI);
                verts[(j * (WIDTH + 1) + i) * 2 + 1] = orig[(j * (WIDTH + 1) + i) * 2 + 1] + offsetY * A;

            }
        }
    }
}
