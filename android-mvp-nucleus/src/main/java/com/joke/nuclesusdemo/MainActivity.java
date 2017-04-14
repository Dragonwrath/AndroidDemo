package com.joke.nuclesusdemo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joke.nuclesusdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends BaseActivity<MainPresenter> implements ResponseCallBack {

    @BindView(R.id.text)
    TextView mText1;
    @BindView(R.id.activity_main)
    RelativeLayout mActivityMain;
    private MainPresenter mPresenter;

    @Override
    protected void initPresenter() {
        final PresenterFactory<MainPresenter> factory = getPresenterFactory();
        setPresenterFactory(new PresenterFactory<MainPresenter>() {
            @Override
            public MainPresenter createPresenter() {
                MainPresenter presenter = factory.createPresenter();
                presenter.takeView(MainActivity.this);
                return presenter;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected BaseActivity getActivity() {
        return this;
    }

    @Override
    protected void initData() {
        mPresenter = getPresenter();
    }

    @Override
    public void onDataSuccess(int action, int from, Bundle bundle) {
        String string = bundle.getString("textView");
        mText1.setText(string);
    }

    @Override
    public void onDataFailure(int action, String error) {
    }

    @OnClick(R.id.activity_main)
    public void onClick(View view) {
        mPresenter.changeText(null);
    }
}
