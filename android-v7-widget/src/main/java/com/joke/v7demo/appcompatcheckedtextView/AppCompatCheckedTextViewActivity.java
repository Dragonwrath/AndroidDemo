package com.joke.v7demo.appcompatcheckedtextView;

import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.view.View;

import com.joke.v7demo.R;

public class AppCompatCheckedTextViewActivity extends AppCompatActivity {

    AppCompatCheckedTextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_compat_checked_text_view);
        textView = (AppCompatCheckedTextView)findViewById(R.id.checked_text_view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView.isChecked()) {
                    textView.setChecked(false);
                } else {
                    textView.setChecked(true);
                }
            }
        });
    }
}
