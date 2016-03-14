package com.brewityourself.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.brewityourself.android.activity.MainActivity;

/**
 * Created by sjung on 12/03/16.
 */
public class BrewFragment extends Fragment {

    protected MainActivity mainActivity;
    public BrewFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }
}
