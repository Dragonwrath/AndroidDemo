package com.joke.widget.expandablelistview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.joke.widget.R;
import com.joke.widget.Unfinished;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Unfinished(description = "los of bugs")
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

	@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
	@Override
	public View getGroupView(int groupPosition,boolean isExpanded,View convertView,ViewGroup parent) {
		GroupViewHolder holder;
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.group_expand_file,parent,false);
			holder = new GroupViewHolder(convertView);
			convertView.setTag(holder);
		}
		holder = (GroupViewHolder)convertView.getTag();
		if(isExpanded) {
			holder.txt.setCompoundDrawables(null, null, collapseDrawable, null);
		} else {
			holder.txt.setCompoundDrawables(null, null, expandDrawable, null);
		}

		File file = mDatas.get(groupPosition);
		holder.txt.setText(file.getName());
		if(file.isDirectory()) {
			holder.img.setImageResource(R.drawable.ic_state_right);
		} else  {
			holder.img.setImageResource(R.drawable.ic_state_wrong);
		}
		return convertView;
	}

	@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
	@Override
	public View getChildView(final int groupPosition,int childPosition,boolean isLastChild,View convertView,ViewGroup parent) {
		File file = mDatas.get(groupPosition).listFiles()[childPosition];
		if(file.isDirectory()) {
			convertView = getDirectoryConvertView(convertView,parent,file);
		} else {
			convertView = getFileConvertView(convertView, parent, file);
		}
//		convertView = getFileConvertView(convertView, parent, file);
		return convertView;
	}

	@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
	private View getDirectoryConvertView(View convertView,ViewGroup parent,File file) {
		ExpandableGroupViewHolder holder;
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.group_expand_view,parent,false);
			holder = new ExpandableGroupViewHolder(convertView);
			convertView.setTag(holder);
		}
		holder = (ExpandableGroupViewHolder)convertView.getTag();
		holder.img.setImageResource(R.drawable.ic_state_right);
		holder.txt.setText(file.getName());
		if(holder.expand) {
			holder.txt.setCompoundDrawables(null, null, collapseDrawable, null);
			holder.list.setAdapter(new FileExpandAdapter(parent.getContext(), Arrays.asList(file.listFiles())));
		} else {
			//todo 优化设置
			holder.list.setAdapter(new FileExpandAdapter(parent.getContext(), null));
			holder.txt.setCompoundDrawables(null, null, expandDrawable, null);
		}
		return convertView;
	}

	private View getFileConvertView(View convertView,ViewGroup parent, File file) {
		GroupViewHolder holder;
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.group_expand_file,parent,false);
			holder = new GroupViewHolder(convertView);
			convertView.setTag(holder);
		}
		holder = (GroupViewHolder)convertView.getTag();
		holder.img.setImageResource(R.drawable.ic_state_wrong);
		holder.txt.setText(file.getName());
		return convertView;
	}


	@Override
	public boolean isChildSelectable(int groupPosition,int childPosition) {
		return false;
	}

	private class GroupViewHolder {
		TextView txt;
		ImageView img;
		GroupViewHolder(View view) {
			txt = (TextView)view.findViewById(R.id.txt_folder);
			img = (ImageView)view.findViewById(R.id.iv_folder);
		}
	}

	private class ExpandableGroupViewHolder {
		TextView txt;
		ImageView img;
		ExpandableListView list;
		boolean expand;

		ExpandableGroupViewHolder(View view) {
			view.setOnClickListener(new View.OnClickListener(){
				boolean isExpand;
				@Override
				public void onClick(View v) {
					ExpandableGroupViewHolder.this.expand = !isExpand;
				}
			});
			txt = (TextView)view.findViewById(R.id.txt_folder);
			img = (ImageView)view.findViewById(R.id.iv_folder);
			list = (ExpandableListView)view.findViewById(R.id.expand_list);
		}
	}
}
