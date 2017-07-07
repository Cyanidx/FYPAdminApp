package com.example.jerald.fypadminapp;

/**
 * Created by 15017292 on 24/6/2017.
 */

public class Date {

    private String date;
    private String gateID;

    public Date(){

    }

    public Date(String date, String gateID) {
        this.date = date;
        this.gateID = gateID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGateID() {
        return gateID;
    }

    public void setGateID(String gateID) {
        this.gateID = gateID;
    }
}
