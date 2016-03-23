package com.brewityourself.android.util;

/**
 * Created by sjung on 18/03/16.
 */

import java.sql.Timestamp;

/**
 * Created by sjung on 18/03/16.
 */
public class TempSensor {

    private int id;
    private double temperature;
    private Timestamp timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}

