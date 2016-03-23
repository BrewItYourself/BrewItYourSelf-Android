package com.brewityourself.android.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.brewityourself.android.R;
import com.brewityourself.android.server.dto.BrewRecipe;
import com.brewityourself.android.util.Constants;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sjung on 09/03/16.
 */
public class BrewRecipeInputFragment extends BrewFragment {

    private Button mSubmitButton;
    private static String LOG_TAG = Constants.BREW_RECIPE_INPUT_FRAGMENT_TAG;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_brewrecipe, container, false);

        mSubmitButton = (Button) view.findViewById(R.id.submit_button);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrewRecipe brewRecipe = new BrewRecipe();

                brewRecipe.setBoilTemperature(100.0);
                brewRecipe.setRecipeName("Pale Ale");

                Call<Void> inputCall =  brewActivity.brewRecipeAPI.inputRecipe(brewRecipe);
                inputCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Response<Void> response, Retrofit retrofit) {
                        if (response.code() == 201) {
                            Log.d(LOG_TAG, "Created");
                            Snackbar.make(view, "Recipe Created", Snackbar.LENGTH_LONG).show();
                        } else {
                            Log.d(LOG_TAG, "Not Created");
                            Snackbar.make(view, "Cannot Create Recipe", Snackbar.LENGTH_LONG)
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
