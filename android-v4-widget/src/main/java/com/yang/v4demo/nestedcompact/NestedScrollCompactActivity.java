package com.yang.v4demo.nestedcompact;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.yang.v4demo.R;

import java.util.ArrayList;
import java.util.List;


public class NestedScrollCompactActivity extends AppCompatActivity implements Palette.PaletteAsyncListener {

    private static final String TAG = "NestedScrollCompactActivity";
    private ImageView mFirst;

    private SwipeRefreshLayout mRefreshLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll_compact);

        NestedScrollLayout main = (NestedScrollLayout) findViewById(R.id.main);
        mFirst = (ImageView)main.findViewById(R.id.image_first);
        BitmapDrawable drawable = (BitmapDrawable) mFirst.getDrawable();

        Log.d(TAG, mFirst.getWidth()+"------"+mFirst.getHeight());
        Palette.from(drawable.getBitmap()).generate(this);


//        first.setBackgroundColor(population);
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            strings.add(TAG+"-->"+i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings);
        ListView list = (ListView) findViewById(R.id.list);

        list.setAdapter(adapter);
    }

    @Override
    public void onGenerated(Palette palette) {
        Palette.Swatch swatch = palette.getLightMutedSwatch();
        if (swatch == null)
            swatch = palette.getLightVibrantSwatch();
        List<Palette.Swatch> swatches = palette.getSwatches();
        for (Palette.Swatch swa : swatches) {
            if (swa != null) {
                int rgb = swa.getRgb();
                Log.e(TAG, "Swatch List: ----->" + rgb);
            }
        }

        if (swatch != null) {
            int rgb = swatch.getRgb();
            mFirst.setBackgroundColor(rgb);
            Log.e(TAG, "onCreate: ----->" + rgb);
        } else {
            Log.e(TAG, "onCreate: ----->null");
        }
    }
}
