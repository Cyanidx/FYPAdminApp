package com.example.jerald.fypadminapp;

/**
 * Created by 15017292 on 15/6/2017.
 */

public class TimeSlot {

    private String date;
    private String direction;
    private String flightNo;
    private String gateID;
    private String id;
    private String planeID;
    private String time;


    public TimeSlot(){

    }

    public TimeSlot(String date, String direction, String flightNo, String gateID, String id, String planeID, String time) {
        this.date = date;
        this.direction = direction;
        this.flightNo = flightNo;
        this.gateID = gateID;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getGateID() {
        return gateID;
    }

    public void setGateID(String gateID) {
        this.gateID = gateID;
    }
}
