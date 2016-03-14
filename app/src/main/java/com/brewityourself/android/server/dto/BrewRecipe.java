package com.brewityourself.android.server.dto;

/**
 * Created by sjung on 09/03/16.
 */
public class BrewRecipe {

    private String recipeName;
    private Double boilTemperature;

    public BrewRecipe() {}

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Double getBoilTemperature() {
        return boilTemperature;
    }

    public void setBoilTemperature(Double boilTemperature) {
        this.boilTemperature = boilTemperature;
    }
}
