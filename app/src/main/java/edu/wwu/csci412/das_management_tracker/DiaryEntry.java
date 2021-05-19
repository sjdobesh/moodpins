package edu.wwu.csci412.das_management_tracker;

public class DiaryEntry {
    String date;
    String input;
    int id;
    public DiaryEntry(int id, String date,String input){
        this.date = date;
        this.input = input;
    }

    public String getDate() {
        return date;
    }

    public String getInput() {
        return input;
    }
}
