package com.brewityourself.android.server.api;

import com.brewityourself.android.server.dto.BrewRecipe;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PUT;

/**
 * Created by sjung on 09/03/16.
 */
public interface BrewRecipeAPI {

    @PUT("/brewrecipe/new_recipe")
    Call<Void> inputRecipe(@Body BrewRecipe brewRecipe);

    @GET("/brewrecipe/all_recipes")
    Call<List<BrewRecipe>> getRecipes();
}
