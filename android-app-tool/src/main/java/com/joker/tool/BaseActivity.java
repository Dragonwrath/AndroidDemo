package com.joker.tool;


import android.support.annotation.Keep;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;

import com.joker.tool.backpress.BackPressObservable;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity{

	private ArrayList<BackPressObservable> backPressObservers;

	@UiThread
	@Keep
	public void addBackPressObservable(BackPressObservable observable){
		if(backPressObservers == null) {
			backPressObservers = new ArrayList<>();
		}
		backPressObservers.add(0,observable);
	}

	@Override
	public void onBackPressed() {
		if(backPressObservers.size() > 0) {
			BackPressObservable observable = backPressObservers.get(0);
			if(observable != null) //TODO
				observable.onBackPressed();
			backPressObservers.remove(0);
		} else {
			super.onBackPressed();
		}
	}
}
