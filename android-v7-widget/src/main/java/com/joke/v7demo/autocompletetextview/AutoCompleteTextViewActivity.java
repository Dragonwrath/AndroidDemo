package com.joke.v7demo.autocompletetextview;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.joke.v7demo.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AutoCompleteTextViewActivity extends AppCompatActivity implements View.OnTouchListener, TextView.OnEditorActionListener {


    private static final String[] COUNTRIES = new String[] {
            "belgium", "france", "italy", "germany", "spain"
    };
    private AppCompatAutoCompleteTextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_view);
        mTextView = (AppCompatAutoCompleteTextView) findViewById(R.id.auto_complete_text_view);
        AutoCompleteAdapter<String> adapter = new AutoCompleteAdapter<>(this);

        mTextView.setAdapter(adapter);
        mTextView.setOnTouchListener(this);
        mTextView.setOnEditorActionListener(this);
        mTextView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        String digits = "abcdef";
        mTextView.setKeyListener(DigitsKeyListener.getInstance(digits));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v instanceof AppCompatAutoCompleteTextView) {
            Drawable drawable = mTextView.getCompoundDrawables()[2];
            if (drawable == null)
                return false;
            int intrinsicHeight = drawable.getIntrinsicHeight();
            float width = v.getWidth() - event.getX();
            if (width < intrinsicHeight) {
                mTextView.setText("");
                return true;
            }
        }
        return false;
    }

    /**
     *
     * 需要给相应的TextView指定类型
     *         android:inputType="text"
     *         android:imeOptions="actionDone"
     * 如果不指定无法在软键盘上显示相应的内容
     * @param v 相应的View
     * @param actionId 相应的action
     * @param event 相应的event事件
     * @return 是否消费相应的事件，返回true代表消费掉，类似于TouchEvent
     *
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        switch (actionId) {
            case EditorInfo.IME_ACTION_DONE: {
                Toast.makeText(this, "DONE", Toast.LENGTH_SHORT).show();
            }
            return true;
            case EditorInfo.IME_ACTION_SEARCH: {
                Toast.makeText(this, "SEARCH", Toast.LENGTH_SHORT).show();
            }
            return true;
            case EditorInfo.IME_ACTION_NONE:
                Toast.makeText(this, "NONE", Toast.LENGTH_SHORT).show();
                mTextView.setText("");
                return true;
        }
        return false;
    }
}
