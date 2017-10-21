package com.joker.tool.popupwindow;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.joker.tool.R;

public class ProgressPopupWindow extends PopupWindow{

	private ProgressPopupWindow(Context context) {
		super(context);
	}

	public void showAtLocation(View anchor, String des) {
		final View view = getContentView();
		ContentLoadingProgressBar progress = (ContentLoadingProgressBar)view.findViewById(R.id.progress);
		final TextView desView = (TextView)view.findViewById(R.id.txt_progress_description);
		progress.show();
		desView.setText(des);
		final int height = view.getMeasuredHeight();
		final int width = view.getMeasuredWidth();
		showAtLocation(anchor, Gravity.CENTER, 0, 0);
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

		public ProgressPopupWindow create() {
			assert mPopupWindow.getContentView() == null;
			resolveWindowSize();
			return mPopupWindow;
		}

		private void resolveWindowSize() {
			float density = mPopupWindow.getContentView().getContext().getResources().getDisplayMetrics().density;
			if(mPopupWindow.getHeight() <= 0 || mPopupWindow.getHeight() <= 0) { //resolve popupwindow wrap_content
				mPopupWindow.getContentView().measure(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
			}
		}
	}
}
