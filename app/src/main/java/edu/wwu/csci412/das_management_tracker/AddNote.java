package edu.wwu.csci412.das_management_tracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import android.widget.EditText;

import java.util.Calendar;

import static edu.wwu.csci412.das_management_tracker.PinActivity.get_formatted_date;

public class AddNote extends AppCompatActivity {
    DatabaseManager db = new DatabaseManager(this);
    Toolbar toolbar;
    EditText title;
    EditText entry;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addNote();

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addNote() {
        title = findViewById(R.id.diaryTitle);
        entry = findViewById(R.id.entry);
        String input = entry.getText().toString();
        String diaryTitle = title.getText().toString();
        String[] date_time = get_formatted_date().split(" ");
        String date = date_time[0];
        String time = date_time[1];
        DiaryEntry entry = new DiaryEntry(0,diaryTitle, input,date,time);
        db.insert(entry);

    }

}