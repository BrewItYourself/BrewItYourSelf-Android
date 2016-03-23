package com.brewityourself.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.brewityourself.android.R;
import com.brewityourself.android.fragment.MainFragment;
import com.brewityourself.android.gcm.RegistrationIntentService;
import com.brewityourself.android.util.Constants;

public class MainActivity extends BrewActivity {

    public MainActivity() {
        super(R.id.main_content_frame);
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
