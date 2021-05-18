package edu.wwu.csci412.das_management_tracker;

public class Pins {
    private int latitude;
    private int longitude;

    public Pins(int latitude, int longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }
}
