package android.os.statfs;

import android.graphics.drawable.AnimationDrawable;
import android.marshon.approotdemo.R;
import android.os.Build;
import android.os.StatFs;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;

public class StatFsActivity extends AppCompatActivity{
	private static final String TAG = "StatFsActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stat_fs);
		getCacheFile();
	}


	private void getCacheFile() {
		if(Build.VERSION.SDK_INT >= 18) {
			final File cacheDir = getApplicationContext().getCacheDir();
			final StatFs fs = new StatFs(cacheDir.getAbsolutePath());
			final long blockSizeLong = fs.getBlockSizeLong();
			Log.e(TAG,"blockSizeLong = " + blockSizeLong);
			final long blockCountLong = fs.getBlockCountLong();
			Log.e(TAG,"blockCountLong = " + blockCountLong);
			final long availableBlocksLong = fs.getAvailableBlocksLong();
			Log.e(TAG,"availableBlocksLong = " + availableBlocksLong);
			final long freeBlocksLong = fs.getFreeBlocksLong();
			Log.e(TAG,"freeBlocksLong = " + freeBlocksLong);
			final long freeBytes = fs.getFreeBytes();
			Log.e(TAG,"freeBytes = " + freeBytes);
			final long availableBytes = fs.getAvailableBytes();
			Log.e(TAG,"availableBytes = " + availableBytes);
			final long totalBytes = fs.getTotalBytes();
			Log.e(TAG,"totalBytes = " + totalBytes);
		}
	}
}
