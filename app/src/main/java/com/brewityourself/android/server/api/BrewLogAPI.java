package com.brewityourself.android.server.api;

import com.brewityourself.android.server.dto.BrewLog;
import com.brewityourself.android.server.dto.BrewRecipe;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by sjung on 22/01/16.
 */
public interface BrewLogAPI {

    /**
     * Get BrewLog from server with brewLog
     * @param brewid ID to get
     * @return List of BrewLogs
     */
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
    @GET("/brewlog/{brewid}")
    Call<BrewLog> getBrewData(@Path("brewid") int brewid);

    /**
     * Gets Brew title for all existing brews
     * @return list of brews
     */
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
    @GET("/brewlog/allbrews")
    Call<List<String>> getBrews();

    /**
     * Starts a brew process
     * @param brewRecipe BrewRecipe to start brew
     * @return
     */
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
    @PUT("/brewlog/start")
    Call<Void> startBrew(@Body BrewRecipe brewRecipe);

    @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
    @PUT("/brewlog/heat")
    Call<Void> startHeat(@Body boolean start);

    /**
     * Test connection for brewlog
     * @return  A random brewlog
     */
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
    @GET("/brewlog/test")
    Call<BrewLog> testBrewLog();
}
