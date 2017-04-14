package com.joke.rxjavademo.module.elementary_1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.joke.rxjavademo.BaseFragment;
import com.joke.rxjavademo.R;
import com.joke.rxjavademo.adapter.ZhuangbiListAdapter;
import com.joke.rxjavademo.model.ZhuangbiImage;
import com.joke.rxjavademo.network.Network;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ElementaryFragment extends BaseFragment {

    @BindView(R.id.gridRv) RecyclerView mGridRv;
    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;

    private ZhuangbiListAdapter mAdapter = new ZhuangbiListAdapter();

    private Observer<List<ZhuangbiImage>> observer = new Observer<List<ZhuangbiImage>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<ZhuangbiImage> images) {
            mSwipeRefreshLayout.setRefreshing(false);
            mAdapter.setImages(images);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_elementary, container,false);
        ButterKnife.bind(this, view);

        mGridRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mGridRv.setAdapter(mAdapter);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setEnabled(false);
        return view;
    }

    @OnCheckedChanged({R.id.searchRb1, R.id.searchRb2, R.id.searchRb3, R.id.searchRb4})
    void TabChange(RadioButton button, boolean checked) {
        if (checked) {
            unSubscribe();
            mAdapter.setImages(null);
            mSwipeRefreshLayout.setRefreshing(true);
            Network.getZhuangbiApi()
                    .search(button.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    @Override
    protected int getDialogRes() {
        return R.layout.tip_bt;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_elementary;
    }
}
