package com.brewityourself.android.util;

/**
 * Created by sjung on 13/03/16.
 */
public enum BrewState {
    STARTED ("Started", 0),
    BOILED ("Boiled", 1),
    SPARGING ("Sparging", 2),
    FINISHED ("Finished", 3);

    private String name;
    private int state;

    BrewState(String name, int state) {
        this.name = name;
        this.state = state;
    }

    String getNameFromState(int state) {
        return BrewState.values()[state].name;
    }

}
