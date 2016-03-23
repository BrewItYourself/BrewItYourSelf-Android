package com.brewityourself.android.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.brewityourself.android.R;

/**
 * Created by sjung on 12/03/16.
 */
public class BrewViewActivity extends BrewActivity {

    private boolean brewInProgress = false;

    public BrewViewActivity() {
        super(R.id.brewview_content_frame);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        brewInProgress = getIntent().getBooleanExtra(Constants.BREW_IN_PROGRESS, false);
//        setContentView(R.layout.activity_brewview);
//
//        if (brewInProgress) {
//            setBrewFragment(new BrewStatusViewFragment(), Constants.BREW_STATUS_VIEW_FRAGMENT_TAG);
//        } else {
//            setBrewFragment(new BrewStartFragment(), Constants.BREW_START_FRAGMENT_TAG);
//        }
    }

    private void setBrewFragment(Fragment fragment, String fragmentTag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragmentFrameId, fragment, fragmentTag);
        fragmentTransaction.commit();
    }
}
