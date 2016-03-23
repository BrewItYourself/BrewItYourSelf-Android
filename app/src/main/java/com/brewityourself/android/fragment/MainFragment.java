package com.brewityourself.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.brewityourself.android.R;
import com.brewityourself.android.util.Constants;

/**
 * Created by sjung on 09/02/16.
 */
public class MainFragment extends BrewFragment {

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        final String[] options = getResources().getStringArray(R.array.app_options);

        listView = (ListView) view.findViewById(R.id.mainlistView);
        ArrayAdapter<String> simpleArrayAdapter =
                new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, options);

        listView.setAdapter(simpleArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                if (position == 0) {
//                    brewActivity.switchFragment(
//                            Constants.BREW_DATA_VIEW_FRAGMENT_TAG,
//                            new BrewDataViewFragment());
//                } else if (position == 1) {
//                    brewActivity.switchFragment(
//                            Constants.BREW_LIST_VIEW_FRAGMENT_TAG,
//                            new BrewListViewFragment());
//                } else if (position == 2) {
//                    brewActivity.switchFragment(
//                            Constants.BREW_RECIPE_INPUT_FRAGMENT_TAG,
//                            new BrewRecipeInputFragment());
//                }
                if (position == 0) {
                    brewActivity.switchFragment(
                            Constants.BREW_START_FRAGMENT_TAG,
                            new BrewStartFragment());
                } else if (position == 1) {
                    brewActivity.switchFragment(
                            Constants.BREW_STATUS_VIEW_FRAGMENT_TAG,
                            new BrewStatusViewFragment());
                }
            }
        });

        return view;
    }
}
