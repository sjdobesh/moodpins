package edu.wwu.csci412.das_management_tracker;

import android.annotation.SuppressLint;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.List;

import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;


public class ResourcesActivity extends AppCompatActivity {
    //randomly shows resources and activities
    ConstraintLayout resourcesLayout;
    private AlertDialog.Builder popupBuilder;
    private AlertDialog popup;
    private ScrollView scrollView;
    ResourceButton [] buttons = new ResourceButton[15];
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
    protected void onRestart() {
        super.onRestart();
        update();
    }
    private void createResources(){

        for(int i = 0; i < 15; i++) {
            //delete once we have all resources
            buttons[i] = new ResourceButton(this);
            /*int white = Color.parseColor("#ffffff");
            int black = Color.parseColor("#000000");
            buttons[i].setBackgroundColor(white);
            buttons[i].setTextColor(black);*/
            if(i > 3) {

                String printIt = "Resource " + (i + 1);
                buttons[i].setText(printIt);
                buttons[i].setLink(" ");
            }
        }

        buttons[0].setLink("https://bmcpsychology.biomedcentral.com/articles/10.1186/s40359-016-0111-x");
        buttons[0].setText("Curious about why tracking your mood on a scale results in valuable information?\n\n" +
                "Check out this article on Psychometric properties of the Positive Mental Health Scale ");
        buttons[0].setCategory("Happy");
        buttons[0].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(buttons[0].getLink()));
                startActivity(intent);
            }
        });

        buttons[1].setLink("https://www.youtube.com/watch?v=ZToicYcHIOU");
        buttons[1].setText("For a calming online meditation click here");
        buttons[1].setCategory("Stress");
        buttons[1].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(buttons[1].getLink()));
                startActivity(intent);
            }
        });

        buttons[2].setLink("https://www.mayoclinic.org/healthy-lifestyle/adult-health/in-depth/anger-management/art-20045434");
        buttons[2].setText("Click here for steps to manage anger");
        buttons[2].setCategory("Anger");
        buttons[2].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(buttons[2].getLink()));
                startActivity(intent);
            }
        });

        //set up suicide help line to click and call
        //category sad

        //Find something to manage anxiety

        //Find something for happy/excited

        //Add DASS-21


        buttons[3] = new ResourceButton(this);
        buttons[3].setLink("https://www.psychologytoday.com/us/blog/unhappy-achievers/202106/unhappy-achievers-and-anger");
        buttons[3].setText("Angry and unproductive? \n Click here to read about ways to manage stress surrounding performance");
        buttons[3].setCategory("Anger");
        buttons[3].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(buttons[3].getLink()));
                startActivity(intent);
            }
        });

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

        List<Integer> template = Arrays.asList(0,1,2,3,4);
        Collections.shuffle(template);
        //TODO: select resources that match the user most recent pin and save to ArrayList
        ArrayList<ResourceButton> chosen;
        int i = 0;
        while( i < 5) {

            System.out.println(i + " " + template.get(i));
            if(buttons[template.get(i)].getParent() == null) {
                grid.addView(buttons[template.get(i)], GridLayout.LayoutParams.MATCH_PARENT, 800);
                i++;
            }
        }

        scrollView.addView( grid );

    }


}
