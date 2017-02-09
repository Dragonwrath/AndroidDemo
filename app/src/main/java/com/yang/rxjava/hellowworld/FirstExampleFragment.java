package com.yang.rxjava.hellowworld;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yang.rxjava.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstExampleFragment extends Fragment {


    public FirstExampleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_example, container, false);
    }

}
