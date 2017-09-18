package android.graphics.colormatrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.marshon.approotdemo.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;


public class ColorMatrixActivity extends AppCompatActivity {

    public ColorMatrix colorMatrix;
    ImageView mImageView;
    GridView mGroup;

    private int mEtWidth,mEtHeight;
    private EditText[] mETs = new EditText[20];
    private float[] mColorMatrix = new float[20];
    public Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);

        mImageView = (ImageView)findViewById(R.id.imageView);
        mGroup = (GridView) findViewById(R.id.icon_group);
        colorMatrix = new ColorMatrix();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.duel);
        mImageView.setImageResource(R.drawable.duel);
        mGroup.post(new Runnable() {
            @Override
            public void run() {
                mEtWidth = mGroup.getWidth() / 5;
                mEtHeight = mGroup.getHeight() / 4;
                addEts();
                initMatrix();
            }
        });
    }

    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                mETs[i].setText(String.valueOf(1));
            } else {
                mETs[i].setText(String.valueOf(0));
            }
        }
    }

    private void addEts() {
        for (int i = 0; i < 20; i++) {
            EditText editText = new EditText(this);
            mETs[i] = editText;
            editText.setWidth(mEtHeight);
            editText.setHeight(mEtHeight);
            mGroup.addView(editText);
        }
    }

    private void getMatrix() {
        for (int i = 0; i < 20; i++) {
            mColorMatrix[i] = Float.valueOf(mETs[i].getText().toString());
        }
    }

    private void setImageMatrix() {
        final Bitmap bmp = Bitmap.createBitmap(this.bitmap.getWidth(), this.bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(mColorMatrix);
        final Canvas canvas = new Canvas(bmp);
        final Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0 , 0 , paint);
        mImageView.setImageBitmap(bmp);
    }
    private void method1() {
        //色调
        colorMatrix.setRotate(0, 0);
    }

    private void method2() {
        //饱和度
        colorMatrix.setSaturation(1f);
    }

    private void method3() {
        //亮度
        colorMatrix.setScale(1, 1, 1, 1);
    }

    private void method4() {
//        ColorMatrix imageMatrix =new ColorMatrix();
//        imageMatrix.postConcat(hubMatTix);
//        imageMatrix.postConcat(saturationMarrix);
//        imageMatrix.postConcat(lumMatrix);

    }

    private Bitmap method5() {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int color;
        int r,g,b,a;
        final Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] oldPixels = new int[width * height];
        int[] newPixels = new int[width * height];
        bitmap.getPixels(oldPixels, 0, width, 0, 0 ,width, height);
        for (int i = 0; i < width * height; i++) {
            color = oldPixels[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

            if (r > 255) {
                r = 255;
            } else if (r < 0) {
                r = 0;
            }

            if (g > 255) {
                g = 255;
            } else if (g < 0) {
                g = 0;
            }

            if (b > 255) {
                b = 255;
            } else if (b < 0) {
                b = 0;
            }

            newPixels[i] = Color.argb(a, r, g, b);
        }
        bmp.setPixels(newPixels, 0 , width, 0 , 0, width, height);
        return bmp;
    }

    public void btnChange(View view) {
        getMatrix();
        setImageMatrix();
    }

    public void btnReset(View view) {
        initMatrix();
        getMatrix();
        setImageMatrix();
    }
}

class ColorMatrixUtils {

    //灰色效果
    public final static  float[] GRAY = {
            0.33f, 0.59f, 0.11F, 0, 0,
            0.33f, 0.59f, 0.11f, 0, 0,
            0.33F, 0.59f, 0.1f,  0, 0,
            0,     0,     0,     1, 0};

    //反转效果
    public final static  float[] RORATE = {
            -1f, 0f, 0F, 1, 1,
            0f, -1f, 0F, 1, 1,
            0f, 0f, -1F, 1, 1,
            0f,  0f, 0F, 1, 0
    };

}
