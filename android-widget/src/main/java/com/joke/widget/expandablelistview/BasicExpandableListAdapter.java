package com.joke.widget.expandablelistview;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.joke.widget.R;

import java.io.File;
import java.util.List;

public class BasicExpandableListAdapter<T> extends BaseExpandableListAdapter{

	private final List<List<T>> mDatas;
	private final LayoutInflater mInflater;
	private final Drawable expandDrawable,collapseDrawable;

	public BasicExpandableListAdapter(List<List<T>> data,Context context) {
		mDatas = data;
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
		List<T> list = mDatas.get(groupPosition);
		return list == null ? 0 : list.size();
	}

	@Override
	public List<T> getGroup(int groupPosition) {
		return mDatas.get(groupPosition);
	}

	@Override
	public T getChild(int groupPosition,int childPosition) {
		T child = null;
		final List<T> list = mDatas.get(groupPosition);
		if(list != null && list.size() >0) {
			child = list.get(childPosition);
		}
		return child;
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
			convertView = mInflater.inflate(R.layout.group_expand_view,parent,false);
			GroupViewHolder holder = new GroupViewHolder(convertView, isExpanded);
			convertView.setTag(holder);
		}
		final GroupViewHolder tag = (GroupViewHolder)convertView.getTag();
		tag.text.setText(String.valueOf(groupPosition));
		tag.text.setCompoundDrawables(null,null,isExpanded ? collapseDrawable : expandDrawable, null);
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition,int childPosition,boolean isLastChild,View convertView,ViewGroup parent) {
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.group_expand_view,parent,false);
			GroupViewHolder holder = new GroupViewHolder(convertView, false);
			convertView.setTag(holder);
		}
		final GroupViewHolder tag = (GroupViewHolder)convertView.getTag();
		final File t = (File)mDatas.get(groupPosition).get(childPosition);
		tag.text.setText(t.getName());
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition,int childPosition) {
		return false;
	}

	private static class GroupViewHolder {
		TextView text;
		boolean isExpanded;
		GroupViewHolder(View view, boolean isExpanded) {
			text = (TextView)view.findViewById(R.id.txt_folder);
			this.isExpanded = isExpanded;
		}
	}
}