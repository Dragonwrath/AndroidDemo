package com.joker.tool.popupwindow;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.joker.tool.BaseActivity;
import com.joker.tool.R;
import com.joker.tool.backpress.BackPressObservable;

public class ProgressPopupWindow extends PopupWindow implements BackPressObservable{

	public boolean supportBackPress = true;

	private ProgressPopupWindow(Context context) {
		super(context, null, R.style.animation_dialog);
	}

	private ProgressPopupWindow(Context context ,AttributeSet set) {
		super(context, set,R.style.BaseDialog);
	}

	public ProgressPopupWindow(View view) {
		super(view);
	}

	public void setBackPressEnabled(boolean supported){
		supportBackPress = supported;
	}

	@Override
	public boolean onBackPressed() {
		if(supportBackPress && isShowing()) {
			dismiss();
			return true;
		}
		return false;
	}

	public void showAtLocation(View anchor, String des) {
		View view = getContentView();
		Context context = view.getContext();
		if(context instanceof BaseActivity && supportBackPress) {
			BaseActivity activity = (BaseActivity)context;
			activity.addBackPressObservable(this);
		}
		ContentLoadingProgressBar progress = (ContentLoadingProgressBar)view.findViewById(R.id.progress);
		final TextView desView = (TextView)view.findViewById(R.id.txt_progress_description);
		desView.setText(des);
		showAtLocation(anchor, Gravity.CENTER, 0, 0);
		progress.show();
	}



	public static class Builder {
		ProgressPopupWindow mPopupWindow;

		public Builder(Activity act) {
			this(act, R.layout.popup_progress);
		}

		public Builder(Activity act,@LayoutRes int layoutRes) {
			mPopupWindow = new ProgressPopupWindow(act);
			LayoutInflater inflater = act.getLayoutInflater();
			mPopupWindow.setContentView(inflater.inflate(layoutRes, null, false));
			mPopupWindow.setOutsideTouchable(false);
		}

		public Builder(View view) {
			mPopupWindow = new ProgressPopupWindow(view);
			mPopupWindow.setOutsideTouchable(false);
		}

		public Builder setWidth(int width) {
			mPopupWindow.setWidth(width);
			return this;
		}

		public Builder setHeight(int height) {
			mPopupWindow.setHeight(height);
			return this;
		}

		public Builder setContentView(View view) {
			mPopupWindow.setContentView(view);
			return this;
		}

		public Builder setOutsideTouchable(boolean touchable) {
			mPopupWindow.setOutsideTouchable(touchable);
			return this;
		}

		public Builder setBackPressEnable(boolean enable) {
			mPopupWindow.setBackPressEnabled(enable);
			return this;
		}
		public ProgressPopupWindow create() {
			assert mPopupWindow.getContentView() == null;
			resolveWindowSize();
			return mPopupWindow;
		}

		/**
		 * if we don't have the size of PopupWindow(don't use setWidth() or setHeight() yet)
		 * we should measure container's size by default,
		 */
		private void resolveWindowSize() {
			if(mPopupWindow.getHeight() <= 0 || mPopupWindow.getHeight() <= 0) {
				mPopupWindow.getContentView().measure(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
			}
		}
	}
}
