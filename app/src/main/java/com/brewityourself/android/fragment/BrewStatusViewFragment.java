package com.brewityourself.android.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.brewityourself.android.R;
import com.brewityourself.android.util.TempSensor;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.HashMap;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sjung on 22/01/16.
 */
public class BrewStatusViewFragment extends BrewFragment {

    private ValueLineChart mCubicValueLineChart;
    private BootstrapButton mStartFetchButton;
    private BootstrapButton mFetchButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_brewstatusview, container, false);

        mCubicValueLineChart = (ValueLineChart) view.findViewById(R.id.brewLineChart);

        mStartFetchButton = (BootstrapButton) view.findViewById(R.id.start_fetch_button);
        mFetchButton = (BootstrapButton) view.findViewById(R.id.fetch_button);

        mStartFetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<Void> heatCall = brewActivity.tempSensorAPI.startRecording();
                heatCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Response<Void> response, Retrofit retrofit) {
                        if (response.code() == 200) {
                            Snackbar.make(view, "Starting recording", Snackbar.LENGTH_LONG)
                                    .show();
                        } else {
                            Snackbar.make(view, "Cannot Start Recording", Snackbar.LENGTH_LONG)
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                    }
                });
            }

        });

        mFetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<HashMap<Integer, List<TempSensor>>> heatCall = brewActivity.tempSensorAPI.fetchData();

                heatCall.enqueue(new Callback<HashMap<Integer, List<TempSensor>>>() {
                    @Override
                    public void onResponse(Response<HashMap<Integer, List<TempSensor>>> response, Retrofit retrofit) {
                        if (response.code() == 200) {
                            Snackbar.make(view, "Fetched Brew Data", Snackbar.LENGTH_LONG)
                                    .show();

                            ValueLineSeries series = new ValueLineSeries();
                            series.setColor(0xFF56B7F1);

                            HashMap<Integer, List<TempSensor>> hashMap = response.body();
                            Log.d("IN HERE", ""+hashMap.isEmpty());
                            int count = 0;
                            for (Integer integer : hashMap.keySet()) {
                                List<TempSensor> tempSensorList = hashMap.get(integer);

                                for (TempSensor tempSensor : tempSensorList) {
                                    series.addPoint(
                                            new ValueLinePoint(""+count,
                                                    (float)tempSensor.getTemperature()));
                                    ++count;
                                }
                            }
                            mCubicValueLineChart.addSeries(series);
                            mCubicValueLineChart.setShowIndicator(true);

                            mCubicValueLineChart.startAnimation();
                        } else {
                            Snackbar.make(view, "Cannot Fetch Brew Data", Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Snackbar.make(view, "Error Calling", Snackbar.LENGTH_LONG)
                                .show();
                    }
                });
            }
        });

        return view;
    }

}
