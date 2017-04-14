package com.joke.rxjavademo.module.zip_3;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.joke.rxjavademo.BaseFragment;
import com.joke.rxjavademo.R;
import com.joke.rxjavademo.adapter.ItemGridAdapter;
import com.joke.rxjavademo.model.Item;
import com.joke.rxjavademo.model.ZhuangbiImage;
import com.joke.rxjavademo.network.Network;
import com.joke.rxjavademo.util.GankBeautyResultToItemsMapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZipFragment extends BaseFragment {

    @BindView(R.id.gridRv)
    RecyclerView mGridRv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    ItemGridAdapter adapter = new ItemGridAdapter();

    Observer<List<Item>> observer = new Observer<List<Item>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<Item> items) {
            mSwipeRefreshLayout.setRefreshing(false);
            adapter.setItems(items);
        }
    };
    @BindView(R.id.tipBt)
    Button mTipBt;
    private Subscription mSubscribe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zip, container, false);
        ButterKnife.bind(this, view);

        mGridRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mGridRv.setAdapter(adapter);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setEnabled(false);
        return view;
    }

    @Override
    protected int getDialogRes() {
        return R.layout.tip_bt;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_zip;
    }


    @OnClick({R.id.zipLoadBt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zipLoadBt:
                mSwipeRefreshLayout.setRefreshing(true);
                unSubscribe();
                Observable<List<Item>> o1 = Network.getGankApi()
                        .getBeauties(50, 1)
                        .map(GankBeautyResultToItemsMapper.getInstance());
                Observable<List<ZhuangbiImage>> o2 = Network.getZhuangbiApi()
                        .search("装逼");
                Func2<List<Item>, List<ZhuangbiImage>, List<Item>> func2 = new Func2<List<Item>, List<ZhuangbiImage>, List<Item>>() {
                    @Override
                    public List<Item> call(List<Item> gankItems, List<ZhuangbiImage> zhuangbiImages) {
                        List<Item> items = new ArrayList<Item>();
                        for (int i = 0; i < gankItems.size() / 2 && i < zhuangbiImages.size(); i++) {
                            items.add(gankItems.get(i * 2));
                            items.add(gankItems.get(i * 2 + 1));
                            Item zhuangbiItem = new Item();
                            ZhuangbiImage zhuangbiImage = zhuangbiImages.get(i);
                            zhuangbiItem.description = zhuangbiImage.description;
                            zhuangbiItem.imageUrl = zhuangbiImage.image_url;
                            items.add(zhuangbiItem);
                        }
                        return items;
                    }
                };
                mSubscribe = Observable.zip(o1,o2,func2)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);
                break;

        }
    }
}
