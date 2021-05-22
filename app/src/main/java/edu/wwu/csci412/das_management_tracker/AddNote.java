package edu.wwu.csci412.das_management_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.EditText;

public class AddNote extends AppCompatActivity {
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
    }
}