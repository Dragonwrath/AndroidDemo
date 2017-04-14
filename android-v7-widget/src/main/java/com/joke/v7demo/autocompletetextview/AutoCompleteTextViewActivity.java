package com.joke.v7demo.autocompletetextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.widget.ArrayAdapter;

import com.joke.v7demo.R;

public class AutoCompleteTextViewActivity extends AppCompatActivity {


    private static final String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_view);
        AppCompatAutoCompleteTextView textView = (AppCompatAutoCompleteTextView) findViewById(R.id.auto_complete_text_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        textView.setAdapter(adapter);
    }
}
