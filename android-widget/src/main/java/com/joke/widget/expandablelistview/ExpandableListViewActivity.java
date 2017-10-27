package com.joke.widget.expandablelistview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.joke.widget.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ExpandableListViewActivity extends AppCompatActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expandable_list_view);
		checkPermission();
		getOutFile();
	}

	private void getOutFile() {
		List<List<File>> list = new ArrayList<>();
		File[] dirs = null;
		if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
			dirs = Environment.getExternalStorageDirectory().listFiles();
			for(final File file : dirs) {
				if(file.isDirectory()) {
					list.add(Arrays.asList(file.listFiles()));
				}
			}
		}
		if(dirs != null) {
			final FileExpandAdapter adapter = new FileExpandAdapter(this,Arrays.asList(dirs));
			final ExpandableListView listView = (ExpandableListView)findViewById(R.id.list);
			listView.setAdapter(adapter);
		}
	}

	private void checkPermission() {
		if(Build.VERSION.SDK_INT >= 23) {
			if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
				requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},101);
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent data) {
		if(resultCode == RESULT_OK) {
			if(requestCode == 101) {
				if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
					if(shouldShowRequestPermissionRationale(Manifest.permission_group.STORAGE)) {
						checkPermission();
					} else {
						finish();
					}
				}
			}
		}
	}

	class FileComparator implements Comparator<File>{
		@Override
		public int compare(File o1,File o2) {
			int i = o1.isDirectory() ? 1 : 0;
			int j = o2.isDirectory() ? 1 : 0;
			if(i != j) {
				return j - i;
			} else {
				return o1.getName().compareTo(o2.getName());
			}
		}
	}
}
