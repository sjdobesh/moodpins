package edu.wwu.csci412.das_management_tracker;

import android.content.Context;

public class ResourceButton extends androidx.appcompat.widget.AppCompatButton {
    private String link;
    //need field for diary
    private DiaryEntry entry;
    private String category;

    public ResourceButton(Context context, String link) {
        super(context);
        this.link = link;
        this.category = category;
    }
    public ResourceButton(Context context, DiaryEntry entry) {
        super(context);
        this.entry = entry;
    }
    public ResourceButton(Context context) {
        super(context);

    }

    public void setEntry(DiaryEntry entry) {
        this.entry = entry;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public DiaryEntry getEntry() {
        return entry;
    }

    public String getLink() {
        return link;
    }

    public String getCategory() {
        return category;
    }
}
