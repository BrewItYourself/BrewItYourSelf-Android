package com.brewityourself.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.brewityourself.android.fragment.BrewStatusViewFragment;
import com.brewityourself.android.fragment.MainFragment;
import com.brewityourself.android.gcm.RegistrationIntentService;
import com.brewityourself.android.server.api.BrewLogAPI;
import com.brewityourself.android.util.Constants;

import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    protected FragmentManager fragmentManager;
    protected int fragmentContentFrame;
    public final BrewLogAPI brewLogAPI;

    public MainActivity() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.IP_ADDRESSS)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        brewLogAPI = retrofit.create(BrewLogAPI.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /**
         * Setup GCM registration
         */
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);

        /**
         * Set up Fragment view and first fragment
         */
        fragmentManager = getSupportFragmentManager();
        fragmentContentFrame = R.id.content_frame;
        setFirstFragment();
    }

    private void setFirstFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragmentContentFrame, new MainFragment(), Constants.MAIN_FRAGMENT_TAG);
        fragmentTransaction.addToBackStack(Constants.MAIN_FRAGMENT_TAG);

        fragmentTransaction.commit();
    }


    public void switchFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContentFrame, new BrewStatusViewFragment());
        fragmentTransaction.addToBackStack(Constants.BREW_STATUS_VIEW_FRAGMENT_TAG);
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
}
