package com.joke.widget.textview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

import com.joke.widget.R;

public class TextViewActivity extends AppCompatActivity {

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;


    private SpannableStringBuilder mBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        mBuilder = new SpannableStringBuilder();
        initView();
    }

    private void initView() {
        text1 = (TextView)findViewById(R.id.text1);
        initText1();
    }

    private void initText1(){
        mBuilder.clear();
        final RelativeSizeSpan span = new RelativeSizeSpan(2f);
        mBuilder.append("123.345678");
        mBuilder.setSpan(span, 0,4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        text1.setText(mBuilder);
    }




}
