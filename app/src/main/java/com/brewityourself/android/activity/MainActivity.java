package com.brewityourself.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.brewityourself.android.R;
import com.brewityourself.android.fragment.MainFragment;
import com.brewityourself.android.gcm.RegistrationIntentService;
import com.brewityourself.android.server.api.BrewLogAPI;
import com.brewityourself.android.server.api.BrewRecipeAPI;
import com.brewityourself.android.util.Constants;

import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    protected FragmentManager fragmentManager;
    private int fragmentFrameId;
    public final BrewLogAPI brewLogAPI;
    public final BrewRecipeAPI brewRecipeAPI;

    public MainActivity() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.IP_ADDRESSS)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        brewLogAPI = retrofit.create(BrewLogAPI.class);
        brewRecipeAPI = retrofit.create(BrewRecipeAPI.class);

        fragmentManager = getSupportFragmentManager();
        fragmentFrameId = R.id.content_frame;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Setup GCM registration
         */
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);

        setFirstFragment();
    }

    private void setFirstFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragmentFrameId, new MainFragment(), Constants.MAIN_FRAGMENT_TAG);
        fragmentTransaction.commit();
    }

    public void switchFragment(String currentTag, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentFrameId, fragment, currentTag);
        fragmentTransaction.addToBackStack(currentTag);

        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
