package com.joke.mvpdemo.lesson1.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.joke.mvpdemo.R;
import com.joke.mvpdemo.lesson1.presenter.MainPresenter;

public class Lession1Activity extends AppCompatActivity implements MainView {

    private TextView text;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lession1);
        text = (TextView)findViewById(R.id.text);
        mPresenter = new MainPresenter();
        mPresenter.addTaskListener(this);
    }



    @Override
    public void onShowString(String str) {
        text.setText(str);
    }

    @Override
    public void onFailureString(String str) {
        text.setText(str);
    }


    public void load(View view) {
        mPresenter.getString();
    }
}
