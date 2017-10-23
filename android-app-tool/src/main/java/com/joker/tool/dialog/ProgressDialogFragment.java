package com.joker.tool.dialog;

import android.app.*;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joker.tool.R;
import com.joker.tool.backpress.BackPressObservable;
import com.joker.tool.popupwindow.ProgressPopupWindow;

import android.tools.Unfinished;

import java.util.ArrayList;
import java.util.List;

@Unfinished(description = {
		"dialog disappear problem","interface for result"})
public class ProgressDialogFragment extends DialogFragment implements BackPressObservable{

	public final static String DEFAULT = "default";
	public final static String TITLE = "title";
	public final static String CONTENT = "content";
	public final static String POSITIVE_TEXT = "positiveText";
	public final static String NEGATIVE_TEXT = "negativeText";
	public final static String CANCEL = "cancel";

	private List<OnClickListener> mPosListeners;
	private List<OnClickListener> mNegListeners;
	private View mRootView;
	private ProgressPopupWindow mWindow;

	public ProgressDialogFragment() {
	}


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable final ViewGroup container,@Nullable Bundle savedInstanceState) {
		//set custom layout of dialog,so that wo could supply different dimens.
		mRootView = inflater.inflate(R.layout.standard_dialog,container,false);
		TextView content = (TextView)mRootView.findViewById(R.id.content);
		content.setText("new content about inflating new layout");

		final View pos = mRootView.findViewById(R.id.btn_confirm);
		final View neg = mRootView.findViewById(R.id.btn_cancel);

		pos.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				if(mPosListeners != null && mPosListeners.size() > 0) {
					for(OnClickListener posListener : mPosListeners) {
						posListener.onClick(getDialog(),DialogInterface.BUTTON_POSITIVE);
					}
				}
				getDialog().hide();
				displayProgressWindow(getActivity().getWindow().getDecorView(), "正在进行");
			}
		});

		neg.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				for(OnClickListener negListener : mNegListeners) {
					negListener.onClick(getDialog(),DialogInterface.BUTTON_NEGATIVE);
				}
				dismiss();
			}
		});

		return mRootView;
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		setStyle(STYLE_NO_TITLE,R.style.BaseDialog_DisableDim); //set the default style
		//must call super.onCreateDialog(). see more : https://developer.android.google.cn/guide/topics/ui/dialogs.html#FullscreenDialog
		return super.onCreateDialog(savedInstanceState);
	}

	@Override
	public Dialog getDialog() {
		return super.getDialog();
	}

	private Dialog generateDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		Bundle bundle = getArguments();
		final Resources res = getResources();
		String title = bundle.getString(TITLE,res.getString(R.string.title));
		String content = bundle.getString(CONTENT,res.getString(R.string.message));
		boolean cancelable = bundle.getBoolean(CANCEL,false);
		if(bundle.getBoolean(DEFAULT,false)) {
			return StandardDialog.defaultBuilder(getActivity(),title,content,true);
		}
		final boolean showProgress = bundle.getBoolean("showProgress",false);
		int progressLayoutIdes = bundle.getInt("progressLayout",-1);
		String positiveText = bundle.getString(POSITIVE_TEXT,"Confirm");
		String negativeText = bundle.getString(NEGATIVE_TEXT,"Cancel");

		if(!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}

		if(!TextUtils.isEmpty(content)) {
			builder.setMessage(content);
		}
		builder.setView(R.layout.standard_dialog);

		if(!TextUtils.isEmpty(positiveText)) {
			builder.setPositiveButton(positiveText,new OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog,int which) {
					for(OnClickListener posListener : mPosListeners) {
						posListener.onClick(dialog,which);
					}
				}
			});
		}
		if(!TextUtils.isEmpty(negativeText)) {
			builder.setNegativeButton(negativeText,new OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog,int which) {
					for(OnClickListener negListener : mNegListeners) {
						negListener.onClick(dialog,which);
					}
				}
			});
		}
		builder.setCancelable(cancelable);
		return builder.create();
	}


	public void addPositiveListener(DialogInterface.OnClickListener listener) {
		if(mPosListeners == null) {
			mPosListeners = new ArrayList<>();
		}
		mPosListeners.add(0,listener);
	}

	public void addNegativeListener(DialogInterface.OnClickListener listener) {
		if(mNegListeners == null) {
			mPosListeners = new ArrayList<>();
		}
		mNegListeners.add(0,listener);
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		super.onDismiss(dialog);
		if(mWindow !=null && mWindow.isShowing()) {
			mWindow.dismiss();
		}
	}

	private void displayProgressWindow(View anchor, String des) {
		if(mWindow == null) {
			final ProgressPopupWindow.Builder builder = new ProgressPopupWindow.Builder(getActivity(),R.layout.popup_progress);
			mWindow = builder.create();
		}
		mWindow.showAtLocation(anchor, des);
	}

	@Override
	public boolean onBackPressed() {
		final Dialog dialog = getDialog();
		if(dialog != null && dialog.isShowing()) {
			dismiss();
			return true;
		}
		return false;
	}
}
