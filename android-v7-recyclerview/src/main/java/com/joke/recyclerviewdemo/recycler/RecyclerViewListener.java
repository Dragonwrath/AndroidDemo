package com.joke.recyclerviewdemo.recycler;

import android.view.View;

public interface RecyclerViewListener {
    void onItemClickListener(View v, int position);
    void onItemLongClickListener(int position);
}
