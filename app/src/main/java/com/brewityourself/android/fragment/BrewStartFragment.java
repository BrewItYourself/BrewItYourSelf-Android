package com.brewityourself.android.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.brewityourself.android.R;
import com.brewityourself.android.server.dto.BrewRecipe;
import com.brewityourself.android.util.Constants;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sjung on 17/03/16.
 */
public class BrewStartFragment extends BrewFragment {

    private BootstrapButton mStartButton;
    private static String LOG_TAG = Constants.BREW_START_FRAGMENT_TAG;
    private boolean start;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_startbrew, container, false);

        mStartButton = (BootstrapButton) view.findViewById(R.id.start_button);
        start = brewActivity.sharedPreferences.getBoolean(Constants.BREW_STARTED, true);
        if (start) {
            mStartButton.setText("Start");
        } else {
            mStartButton.setText("Stop");
        }

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrewRecipe brewRecipe = new BrewRecipe();

                brewRecipe.setBoilTemperature(100.0);
                brewRecipe.setRecipeName("Pale Ale");

                Call<Void> inputCall =  brewActivity.brewLogAPI.startHeat(start);
                inputCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Response<Void> response, Retrofit retrofit) {
                        if (response.code() == 201) {
                            if (start) {
                                Log.d(LOG_TAG, "Created");

                                Snackbar.make(view, "Started Brew", Snackbar.LENGTH_LONG).show();

                                mStartButton.setText("Stop");
                                start = false;

                            } else {
                                Log.d(LOG_TAG, "Stopped");

                                Snackbar.make(view, "Stopped Brew", Snackbar.LENGTH_LONG).show();

                                mStartButton.setText("Start");
                                start = true;
                            }
                            brewActivity
                                    .sharedPreferences
                                    .edit()
                                    .putBoolean(Constants.BREW_STARTED, start)
                                    .commit();
                        } else {
                            Log.d(LOG_TAG, "Not Created");
                            Snackbar.make(view, "Cannot Start Brew", Snackbar.LENGTH_LONG)
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.e(LOG_TAG, "onFailure()");
                        Snackbar.make(view, "Error Calling", Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }
}
