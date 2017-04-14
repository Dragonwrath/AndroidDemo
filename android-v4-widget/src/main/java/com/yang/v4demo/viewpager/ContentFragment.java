package com.yang.v4demo.viewpager;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yang.v4demo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends LazyFragment {


    private static final String TAG = "->";

    private String title;
    public ContentFragment() {
        // Required empty public constructor
    }

    public static ContentFragment newInstance(String title) {
        ContentFragment fragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title" ,title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        String title = bundle.getString("title", "");
        if (title.length() > 0 ){
            this.title =title;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(TAG, title+"-----isVisibleToUser: --------> "+ isVisibleToUser );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        TextView textView = (TextView) view.findViewById(R.id.content);
        textView.setText(title);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,22);
        Log.e(TAG, title+"------->onCreateView ");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, title+"------->onActivityCreated ");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, title+"------->onStart ");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, title+"------->onResume ");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, title+"------->onPause ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, title+"------->onStop ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, title+"------->onDestroyView ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, title+"------->onDestroy ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, title+"------->onDetach ");
    }


    public void loadDate() {
        Log.e(TAG, "loadDate: --->"+ title );
    }
}
