package edu.wwu.csci412.das_management_tracker;

public class Pin {
    private double x;
    private double y;
    private String date;
    private int id;
    private int color;

    public Pin(int id, double x, double y, String date, int color){
        this.id = id;
        this.x = x;
        this.y = y;
        this.date = date;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() { return this.x; }

    public double getY() { return this.y; }

    public String getDate() { return this.date; }

    public String getTime() { return this.date.split(" ")[1]; }

    public String getDay() { return this.date.split(" ")[0]; }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
