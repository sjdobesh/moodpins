package edu.wwu.csci412.das_management_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

public class AddNote extends AppCompatActivity {
    static DatabaseManager db;
    Toolbar toolbar;
    EditText title;
    EditText entry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = findViewById(R.id.diaryTitle);
        entry = findViewById(R.id.entry);
        String input = entry.getText().toString();
        String diaryTitle = title.getText().toString();
        String time = "";
        DiaryEntry entry = new DiaryEntry(0,diaryTitle, input,getDayMonthYear(),time);
        db.insert(entry);
    }
    private String getDayMonthYear() {
        Calendar c = Calendar.getInstance();
        return c.getTime().toString();
    }

}