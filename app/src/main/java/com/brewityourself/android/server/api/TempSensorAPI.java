package com.brewityourself.android.server.api;

import com.brewityourself.android.util.TempSensor;

import java.util.HashMap;
import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.PUT;

/**
 * Created by sjung on 18/03/16.
 */
public interface TempSensorAPI {

    @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
    @PUT("/temp_sensor/start")
    Call<Void> startRecording();

    @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
    @GET("/temp_sensor/fetch")
    Call<HashMap<Integer, List<TempSensor>>> fetchData();
}
