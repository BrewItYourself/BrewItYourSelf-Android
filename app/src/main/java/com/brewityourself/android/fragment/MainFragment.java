package com.brewityourself.android.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.brewityourself.android.MainActivity;
import com.brewityourself.android.R;
import com.brewityourself.android.server.dto.BrewLog;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sjung on 09/02/16.
 */
public class MainFragment extends Fragment {

    private MainActivity mainActivity;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        final String[] options = getResources().getStringArray(R.array.app_options);

        listView = (ListView) view.findViewById(R.id.mainlistView);
        ArrayAdapter<String> simpleArrayAdapter =
                new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, options);
        listView.setAdapter(simpleArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String option = options[position];
                Snackbar.make(view, option, Snackbar.LENGTH_LONG).show();

                Call<BrewLog> brewLogCall = mainActivity.brewLogAPI.testBrewLog();

                brewLogCall.enqueue(new Callback<BrewLog>() {
                    @Override
                    public void onResponse(Response<BrewLog> response, Retrofit retrofit) {
                        if (response.code() == 200) {
                            BrewLog brewLog = response.body();
                            Log.d("TAG", brewLog.getBrewState());
                        }
                        Log.d("TAG","none");
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.e("TAG","failure");
                    }
                });
            }
        });
        return view;
    }
}
