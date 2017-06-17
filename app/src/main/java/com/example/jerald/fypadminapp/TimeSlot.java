package com.example.jerald.fypadminapp;

/**
 * Created by 15017292 on 15/6/2017.
 */

public class TimeSlot {

    private String date;
    private String flightNo;
    private String id;
    private String planeID;
    private String time;


    public TimeSlot(){

    }

    public TimeSlot(String date, String flightNo, String id, String planeID, String time) {
        this.date = date;
        this.flightNo = flightNo;
        this.id = id;
        this.planeID = planeID;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaneID() {
        return planeID;
    }

    public void setPlaneID(String planeID) {
        this.planeID = planeID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
