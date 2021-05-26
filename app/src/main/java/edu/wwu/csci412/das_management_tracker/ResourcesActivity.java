package edu.wwu.csci412.das_management_tracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class ResourcesActivity extends AppCompatActivity {
    //randomly shows resources and activities
    ConstraintLayout resourcesLayout;
    private AlertDialog.Builder popupBuilder;
    private AlertDialog popup;
    private ScrollView scrollView;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        scrollView = findViewById( R.id.scrollView );
        resourcesLayout = (ConstraintLayout) findViewById(R.id.layout_resources);

        resourcesLayout.setOnTouchListener(new OnSwipeTouchListener(ResourcesActivity.this) {

            @Override
            public void onSwipeBottom() {
                Intent intent = new Intent(ResourcesActivity.this, PinActivity.class);
                intent.putExtra("colorStart", "resources");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_to_bottom, R.anim.slide_in_from_up);
            }

        });
    }
    protected void onStart(){
        super.onStart();
        update();
    }

    //Need to add in actual resources
    //Like links or past diary entries etc.
    //Diary entries can be pulled from database
    private void update(){
        scrollView.removeAllViewsInLayout();
        //Make scrolling list of resources
        scrollView.removeAllViewsInLayout();
        GridLayout grid = new GridLayout( this );

        grid.setRowCount(15);
        grid.setColumnCount( 1 );
        ResourceButton [] buttons = new ResourceButton[15];
        for(int i = 0; i < 15; i++) {
            buttons[i] = new ResourceButton( this);
            buttons[i].setText("Resource");
            grid.addView(buttons[i], GridLayout.LayoutParams.MATCH_PARENT, 300);
        }
        scrollView.addView( grid );
    }


}
