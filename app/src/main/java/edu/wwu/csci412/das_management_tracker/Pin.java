package edu.wwu.csci412.das_management_tracker;

public class Pin {
    private double x;
    private double y;
    private String date;

    public Pin(double x, double y, String date){
        this.x = x;
        this.y = y;
        this.date = date;
    }

    public double getX() { return this.x; }

    public double getY() { return this.y; }

    public String getDate() { return this.date; }
}
