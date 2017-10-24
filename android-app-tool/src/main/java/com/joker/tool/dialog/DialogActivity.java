package com.joker.tool.dialog;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.joker.tool.BaseActivity;
import com.joker.tool.R;


public class DialogActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);
	}

	public Bitmap drawableToBitmap(Drawable drawable) {
		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
		final Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(),config);
		final Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	public void showDialog(View view) {
		//        new StandardDialog.Builder(this, R.style.BaseDialog, R.layout.standard_dialog).create().show();
		//        StandardDialog.defaultBuilder(this, "new Title", "new Content", true).show();
		//        alertDialog();
		progressDialog();
	}

	private void progressDialog() {
		final ProgressDialogFragment fragment = new ProgressDialogFragment();
		final Bundle bundle = new Bundle();
		bundle.putBoolean("showProgress",true);
		fragment.addPositiveListener(new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog,int which) {
				//TODO send message or something
			}
		});
		fragment.addPositiveListener(new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog,int which) {
				//TODO send message or something interacting with service
			}
		});
		fragment.setArguments(bundle);
		fragment.show(getSupportFragmentManager(),ProgressDialogFragment.class.getSimpleName());
		addBackPressObservable(fragment);
	}

	private void alertDialog() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final AlertDialog dialog = builder.show();
		dialog.hide();
		final FrameLayout frameLayout = (FrameLayout)dialog.findViewById(android.R.id.custom);
		assert frameLayout != null;
		frameLayout.addView(getLayoutInflater().inflate(R.layout.standard_dialog,(ViewGroup)getWindow().getDecorView(),false));
		dialog.show();
	}
}
