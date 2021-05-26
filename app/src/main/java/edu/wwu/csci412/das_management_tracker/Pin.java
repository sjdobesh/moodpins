package edu.wwu.csci412.das_management_tracker;

public class Pin {
    private double x;
    private double y;
    private String date;
    private int id;

    public Pin(int id, double x, double y, String date){
        this.id = id;
        this.x = x;
        this.y = y;
        this.date = date;
    }

    public double getX() { return this.x; }

    public double getY() { return this.y; }

    public String getDate() { return this.date; }

    public String getTime() { return this.date.split(" ")[1]; }

    public String getDay() { return this.date.split(" ")[0]; }
}
