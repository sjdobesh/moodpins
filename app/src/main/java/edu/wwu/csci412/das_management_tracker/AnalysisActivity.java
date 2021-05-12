package edu.wwu.csci412.das_management_tracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class AnalysisActivity extends AppCompatActivity {

    ConstraintLayout analysisLayout;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        analysisLayout = (ConstraintLayout) findViewById(R.id.layout_analysis);

        analysisLayout.setOnTouchListener(new OnSwipeTouchListener(AnalysisActivity.this) {

            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(AnalysisActivity.this, PinActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }

        });
    }
}
