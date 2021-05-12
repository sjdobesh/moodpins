package edu.wwu.csci412.das_management_tracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class ResourcesActivity extends AppCompatActivity {

    ConstraintLayout resourcesLayout;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        resourcesLayout = (ConstraintLayout) findViewById(R.id.layout_resources);

        resourcesLayout.setOnTouchListener(new OnSwipeTouchListener(ResourcesActivity.this) {

            @Override
            public void onSwipeBottom() {
                Intent intent = new Intent(ResourcesActivity.this, PinActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_to_bottom, R.anim.slide_in_from_up);
            }

        });
    }
}
