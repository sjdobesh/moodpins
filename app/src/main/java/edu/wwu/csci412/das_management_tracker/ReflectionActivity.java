package edu.wwu.csci412.das_management_tracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


public class ReflectionActivity extends AppCompatActivity {
    static DatabaseManager db;
    ConstraintLayout reflectionLayout;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection);

        reflectionLayout = (ConstraintLayout) findViewById(R.id.layout_reflection);

        reflectionLayout.setOnTouchListener(new OnSwipeTouchListener(ReflectionActivity.this) {

            @Override
            public void onSwipeRight() {
                Intent intent = new Intent(ReflectionActivity.this, PinActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_to_right, R.anim.slide_in_from_left);
            }

        });
    }

    private String getDayMonthYear() {
        Calendar c = Calendar.getInstance();
        return c.getTime().toString();
    }
    public void save(View view) {
        EditText newEntry = findViewById(R.id.anEntry);
        String input =  newEntry.getText().toString();
        DiaryEntry entry = new DiaryEntry(0, getDayMonthYear(),input);
        db.insert(entry);
    }
}
