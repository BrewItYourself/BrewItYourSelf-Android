package com.brewityourself.android.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.brewityourself.android.server.api.BrewLogAPI;
import com.brewityourself.android.server.api.BrewRecipeAPI;
import com.brewityourself.android.server.api.TempSensorAPI;
import com.brewityourself.android.util.Constants;

import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by sjung on 17/03/16.
 */
public class BrewActivity extends AppCompatActivity {

    protected FragmentManager fragmentManager;
    protected int fragmentFrameId;
    public final BrewLogAPI brewLogAPI;
    public final BrewRecipeAPI brewRecipeAPI;
    public final TempSensorAPI tempSensorAPI;
    public SharedPreferences sharedPreferences;

    public BrewActivity(int content_frame) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.IP_ADDRESSS)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        brewLogAPI = retrofit.create(BrewLogAPI.class);
        brewRecipeAPI = retrofit.create(BrewRecipeAPI.class);
        tempSensorAPI = retrofit.create(TempSensorAPI.class);

        fragmentManager = getSupportFragmentManager();
        fragmentFrameId = content_frame;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    public void switchFragment(String currentTag, Fragment fragment) {
        switchFragment(currentTag, fragment, true);
    }

    public void switchFragment(String currentTag, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentFrameId, fragment, currentTag);
        if (addToBackStack)
            fragmentTransaction.addToBackStack(currentTag);

        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }




}
