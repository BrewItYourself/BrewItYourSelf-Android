package com.brewityourself.android.activity;

import android.app.Activity;
import android.os.Bundle;

import com.brewityourself.android.R;

/**
 * Created by sjung on 12/03/16.
 */
public class BrewViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_brewview);
    }
}
