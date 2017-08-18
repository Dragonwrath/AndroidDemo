package com.example.designdemo.appbarlayout;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder>{

    private Activity mActivity;
    private ArrayList<String> mList;
    public SimpleRecyclerAdapter(Activity activity, ArrayList<String> list) {
        mActivity = activity;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mActivity.getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
