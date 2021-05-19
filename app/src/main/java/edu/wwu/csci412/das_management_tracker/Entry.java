package edu.wwu.csci412.das_management_tracker;

public class Entry {
    private String ID;
    private String title;
    private String text;
    private String date;
    private String time;

    Entry(){

    }

    Entry(String title, String text, String date, String time){
        this.title = title;
        this.text = text;
        this.date = date;
        this.time = time;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
