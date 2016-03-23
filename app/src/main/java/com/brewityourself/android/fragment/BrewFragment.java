package com.brewityourself.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.brewityourself.android.activity.BrewActivity;
import com.brewityourself.android.activity.BrewViewActivity;
import com.brewityourself.android.activity.MainActivity;

/**
 * Created by sjung on 12/03/16.
 */
public class BrewFragment extends Fragment {

    public BrewActivity brewActivity;
    public BrewFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity().getClass().equals(MainActivity.class)) {
            brewActivity = (MainActivity) getActivity();
        } else {
            brewActivity = (BrewViewActivity) getActivity();
        }
    }
}
