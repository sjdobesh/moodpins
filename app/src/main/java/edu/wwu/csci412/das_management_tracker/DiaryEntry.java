package edu.wwu.csci412.das_management_tracker;

public class DiaryEntry {
    private int ID;
    private String title;
    private String text;
    private String date;
    private String time;

    DiaryEntry(){

    }
    DiaryEntry(int ID, String title, String text, String date, String time){
        this.ID = ID;
        this.title = title;
        this.text = text;
        this.date = date;
        this.time = time;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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
