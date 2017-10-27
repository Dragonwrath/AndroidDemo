package com.joke.widget.textview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
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
//        initView();
    }

//    private void initView() {
//        spannableTest();
//    }
//
//    private void spannableTest() {
//        text1 = (TextView)findViewById(R.id.text1);
//        text2 = (TextView)findViewById(R.id.text2);
//        text3 = (TextView)findViewById(R.id.text3);
//        text4 = (TextView)findViewById(R.id.text4);
//        initText1();
//        initText2();
//        initText3();
//        initText4();
//    }
//
//    private void initText1(){
//        mBuilder.clear();
//        final RelativeSizeSpan span = new RelativeSizeSpan(2f);
//        mBuilder.append("12345678");
//        mBuilder.setSpan(span, 1,4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        mBuilder.insert(4,"hahah");
//        mBuilder.insert(1,"hahah");
//        text1.setText(mBuilder);
//    }
//
//    private void initText2(){
//        mBuilder.clear();
//        final RelativeSizeSpan span = new RelativeSizeSpan(2f);
//        mBuilder.append("12345678");
//        mBuilder.setSpan(span, 1,4, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
//        mBuilder.insert(4,"hahah");
//        mBuilder.insert(1,"hahah");
//        text2.setText(mBuilder);
//    }
//
//    private void initText3(){
//        mBuilder.clear();
//        final RelativeSizeSpan span = new RelativeSizeSpan(2f);
//        mBuilder.append("12345678");
//        mBuilder.setSpan(span, 1,4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        mBuilder.insert(4,"hahah");
//        mBuilder.insert(1,"hahah");
//        text3.setText(mBuilder);
//    }
//
//    private void initText4(){
//        mBuilder.clear();
//        final RelativeSizeSpan span = new RelativeSizeSpan(2f);
//        mBuilder.append("12345678");
//        mBuilder.setSpan(span, 1,4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        mBuilder.insert(4,"hahah");
//        mBuilder.insert(1,"hahah");
//        text4.setText(mBuilder);
//    }


}
