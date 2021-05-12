package edu.wwu.csci412.das_management_tracker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class PinActivity extends AppCompatActivity {

    ConstraintLayout pinLayout;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        pinLayout = (ConstraintLayout) findViewById(R.id.layout_pins);

        pinLayout.setOnTouchListener(new OnSwipeTouchListener(PinActivity.this) {

            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(PinActivity.this, ReflectionActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }

            @Override
            public void onSwipeRight() {
                Intent intent = new Intent(PinActivity.this, AnalysisActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_to_right, R.anim.slide_in_from_left);
            }

            @Override
            public void onSwipeTop() {
                Intent intent = new Intent(PinActivity.this, ResourcesActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_up);
            }

        });
    }



}
