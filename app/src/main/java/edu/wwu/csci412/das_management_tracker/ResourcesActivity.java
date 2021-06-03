package edu.wwu.csci412.das_management_tracker;

import android.annotation.SuppressLint;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
    ResourceButton [] buttons = new ResourceButton[15];
    private int index = 0;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        scrollView = findViewById( R.id.scrollView );
        createResources();

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
    private void createResources(){
        buttons[0] = new ResourceButton(this);
        buttons[0].setLink("https://bmcpsychology.biomedcentral.com/articles/10.1186/s40359-016-0111-x");
        buttons[0].setText("Resource 1: Psychometric Properties");

        buttons[1] = new ResourceButton(this);
        buttons[1].setLink("https://www.youtube.com/watch?v=ZToicYcHIOU");
        buttons[1].setText("Resource 2: Online Meditation");

        buttons[2] = new ResourceButton(this);
        buttons[2].setLink("https://www.mayoclinic.org/healthy-lifestyle/adult-health/in-depth/anger-management/art-20045434");
        buttons[2].setText("Resource 3: Anger Management");


        for(int i = 0; i < 15; i++) {
            //delete once we have all resources
            if(i > 2) {
                buttons[i] = new ResourceButton(this);
                String printIt = "Resource " + (i + 1);
                buttons[i].setText(printIt);
                buttons[i].setLink(" ");
            }
            index = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(buttons[index].getLink()));
                    startActivity(intent);
                }
            });
        }
    }
    //Need to add in actual resources
    //Like links or past diary entries etc.
    //Diary entries can be pulled from database
    private void update(){
        //Make scrolling list of resources
        scrollView.removeAllViewsInLayout();
        GridLayout grid = new GridLayout( this );

        grid.setRowCount(15);
        grid.setColumnCount( 1 );

        List<Integer> template = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14);
        Collections.shuffle(template);

        for(int i = 0; i < 15; i++) {

            System.out.println(i + " " + template.get(i));
            if(buttons[template.get(i)].getParent() == null) {
                grid.addView(buttons[template.get(i)], GridLayout.LayoutParams.MATCH_PARENT, 300);
            }
        }

        scrollView.addView( grid );
        //13 14 8 12 3 15 2 5 10 7 6 4 9 1 11
    }


}
