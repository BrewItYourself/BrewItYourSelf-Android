package com.brewityourself.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brewityourself.android.R;

/**
 * Created by sjung on 09/03/16.
 */
public class BrewDataViewFragment extends BrewFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brewdata, container, false);

        return view;
    }
}
