package edu.wwu.csci412.das_management_tracker;

public class Pins {
    private int latitude;
    private int longitude;
    private String date;

    public Pins(int latitude, int longitude, String date){
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public String getDate() {
        return date;
    }
}
