package com.joke.widget.numberpicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.NumberPicker;

import com.joke.widget.R;


public class NumberPickerActivity extends AppCompatActivity {

    NumberPicker picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_picker);
        picker = (NumberPicker)findViewById(R.id.picker1);
        picker.setMaxValue(7);
        picker.setMinValue(1);
        picker.setDisplayedValues(new String[]{"Mon","Fer","Wen","Thu","Fri","Sat","Sun"});
        picker.isInEditMode();
    }
}
