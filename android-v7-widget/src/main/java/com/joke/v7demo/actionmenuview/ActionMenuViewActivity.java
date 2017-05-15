package com.joke.v7demo.actionmenuview;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.joke.v7demo.R;

public class ActionMenuViewActivity extends AppCompatActivity implements ActionMenuView.OnMenuItemClickListener {

    private ActionMenuView mLine;
    private Toolbar mToolbar;

    @SuppressWarnings("ResourceType")
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_menu_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null){
            bar.setHomeButtonEnabled(true);
            bar.setDisplayShowTitleEnabled(false);
            bar.setDisplayHomeAsUpEnabled(true);
        }
        mLine = (ActionMenuView) mToolbar.findViewById(R.id.line);
        mLine.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu_view,mLine.getMenu());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.titile1:
            case R.id.titile2:
            case R.id.titile3:
                Toast.makeText(this, item.getItemId(),Toast.LENGTH_LONG).show();
                item.getIcon().setColorFilter(ContextCompat.getColor(this,android.R.color.darker_gray),
                        PorterDuff.Mode.DST_OVER);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
