package com.joke.widget.expandablelistview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.joke.widget.Unfinished;

import java.io.File;
import java.util.List;

@Unfinished(description = "not start yet")
public class FileExpandAdapter extends BaseExpandableListAdapter{

	private final List<File> mDatas;
	private final LayoutInflater mInflater;
	private final Drawable expandDrawable,collapseDrawable;

	public FileExpandAdapter(Context context,List<File> datas) {
		mDatas = datas;
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		expandDrawable = context.getResources().getDrawable(android.R.drawable.arrow_down_float);
		collapseDrawable = context.getResources().getDrawable(android.R.drawable.arrow_up_float);
	}

	@Override
	public int getGroupCount() {
		return mDatas == null ? 0 : mDatas.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		File[] files = mDatas.get(groupPosition).listFiles();
		return files == null ? 0 : files.length;
	}

	@Override
	public File getGroup(int groupPosition) {
		return mDatas.get(groupPosition);
	}

	@Override
	public File getChild(int groupPosition,int childPosition) {
		return mDatas.get(groupPosition).listFiles()[childPosition];
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition,int childPosition) {
		return groupPosition * 31 + childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int groupPosition,boolean isExpanded,View convertView,ViewGroup parent) {
		if(convertView == null) {

		}
		return null;
	}

	@Override
	public View getChildView(int groupPosition,int childPosition,boolean isLastChild,View convertView,ViewGroup parent) {
		return null;
	}

	@Override
	public boolean isChildSelectable(int groupPosition,int childPosition) {
		return false;
	}

	class ViewHolder {
		View view ;

		public ViewHolder(View view) {
			this.view = view;
		}
	}
}
