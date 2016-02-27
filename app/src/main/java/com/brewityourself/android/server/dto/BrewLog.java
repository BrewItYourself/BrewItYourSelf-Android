package com.brewityourself.android.server.dto;

import java.sql.Date;

/**
 * Created by sjung on 22/01/16.
 * Class to store BrewLog
 */
public class BrewLog {
    private int brewId;
    private String brewState;
    private Date logTime;
    private double temperature;

    public BrewLog() {}

    public BrewLog(int id, String state, Date time, double temperature) {
        brewId = id;
        brewState = state;
        logTime = time;
        this.temperature = temperature;
    }

    public int getBrewId() {
        return brewId;
    }

    public void setBrewId(int brewId) {
        this.brewId = brewId;
    }

    public String getBrewState() {
        return brewState;
    }

    public void setBrewState(String brewState) {
        this.brewState = brewState;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
