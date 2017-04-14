package com.joke.nuclesusdemo.base;


import android.os.Bundle;
import android.os.PersistableBundle;

import butterknife.ButterKnife;
import nucleus.presenter.Presenter;
import nucleus.view.NucleusAppCompatActivity;

public abstract class BaseActivity<Type extends Presenter> extends NucleusAppCompatActivity<Type> {

    abstract protected void initPresenter();
    abstract protected int getLayoutResId();
    abstract protected BaseActivity getActivity();
    abstract protected void initData();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        if (getActivity() == null) {
            return;
        } else if (getLayoutResId() <= 0 ) {
            getActivity().finish();
            return;
        }
        setContentView(getLayoutResId());
        initial(getActivity());
    }

    protected void initial(BaseActivity activity) {
        ButterKnife.bind(activity);
        BaseApplication.getInstance().addActivity(activity);
        initData();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.getInstance().removeActivity(getActivity());
    }

}
