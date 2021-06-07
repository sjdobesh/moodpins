package edu.wwu.csci412.das_management_tracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;
import java.util.Arrays;

public class AnalysisActivity extends AppCompatActivity {

    ConstraintLayout analysisLayout;
    GraphView graph, pointGraph;
//    Spinner dropdown;
    EditText dayRange;
    Toolbar toolbar;

    ArrayList<Pin> allPins;
    DatabaseManager db = new DatabaseManager(this);
    PointsGraphSeries<DataPoint> redPoints, bluePoints, greenPoints, cyanPoints, whitePoints;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        analysisLayout = (ConstraintLayout) findViewById(R.id.layout_analysis);
        graph = (GraphView) findViewById(R.id.test_graph);
        pointGraph = (GraphView) findViewById(R.id.test_point_graph);
//        dropdown = (Spinner) findViewById(R.id.spinner_graph);
        dayRange = (EditText) findViewById(R.id.day_counter);
        toolbar = (Toolbar) findViewById(R.id.graph_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Analysis");
        toolbar.setTitleTextAppearance(this, R.style.MontserratText);

        allPins = db.selectAllPins();

        pointGraph.getViewport().setXAxisBoundsManual(true);
        pointGraph.getViewport().setYAxisBoundsManual(true);
        pointGraph.getViewport().setMinX(-100);
        pointGraph.getViewport().setMaxX(100);
        pointGraph.getViewport().setMinY(-100);
        pointGraph.getViewport().setMaxY(100);

        for(int i = 0; i < allPins.size(); i++) {
            Pin currPin = allPins.get(i);
            DataPoint dp = new DataPoint(currPin.getX(), currPin.getY() * -1);
            DataPoint[] point = {dp};
            PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(point);
            pointGraph.addSeries(series);
            series.setColor(currPin.getColor());
        }


        pointGraph.setTitle("Pin Distribution");
        pointGraph.getGridLabelRenderer().setVerticalLabelsVisible(false);
        pointGraph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        pointGraph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.BOTH);


        analysisLayout.setOnTouchListener(new OnSwipeTouchListener(AnalysisActivity.this) {

            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(AnalysisActivity.this, PinActivity.class);
                intent.putExtra("colorStart", "analysis");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }

        });

//        String[] paths = {"what", "nice", "great"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AnalysisActivity.this, android.R.layout.simple_spinner_item, paths);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        dropdown.setAdapter(adapter);
//
//        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("TEST", "position " + position + " chosen");
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        dayRange.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER)) {
                    LineGraphSeries<DataPoint> test = new LineGraphSeries<DataPoint>(new DataPoint[] {
                            new DataPoint(0, 5),
                            new DataPoint(1, 5),
                            new DataPoint(2, 4),
                            new DataPoint(3, 4),
                            new DataPoint(4, 3),
                    });
                    graph.removeAllSeries();
                    graph.addSeries(test);
//                    Toast.makeText(AnalysisActivity.this, dayRange.getText().toString() + " day range selected", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


        /**
         * WORK USING GRAPHVIEW PACKAGE
         * WIKI LINK:   https://github.com/jjoe64/GraphView/wiki/Summary-and-Features
         */


        // MAKING A SERIES
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
        });

        graph.addSeries(series);

        // MAKING A 2nd SERIES
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 30),
                new DataPoint(1, 30),
                new DataPoint(2, 60),
                new DataPoint(3, 20),
                new DataPoint(4, 50),
        });

        // ADDING SECOND SCALE TO RIGHT HAND SIDE
        graph.getSecondScale().addSeries(series2);
        graph.getSecondScale().setMinY(0);
        graph.getSecondScale().setMaxY(100);
        series2.setColor(Color.RED);
        graph.getGridLabelRenderer().setVerticalLabelsSecondScaleColor(Color.RED);

        // DATA POINT CLICK HANDLER
        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(AnalysisActivity.this, "Series1: On Data Point Clicked: " + dataPoint, Toast.LENGTH_SHORT).show();
            }
        });

        // LEGEND CREATION
        series.setTitle("foo");
        series2.setTitle("bar");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

        // MAKE GRAPH SCROLLABLE AND SCALABLE
//        graph.getViewport().setYAxisBoundsManual(true);
//        graph.getViewport().setMinY(0);
//        graph.getViewport().setMaxY(8);
//
//        graph.getViewport().setXAxisBoundsManual(true);
//        graph.getViewport().setMinX(0);
//        graph.getViewport().setMaxX(4);
//
//        graph.getViewport().setScalable(true);
//        graph.getViewport().setScalableY(true);
//        graph.getViewport().setScrollable(true);
//        graph.getViewport().setScrollableY(true);

        // SET TITLE OF GRAPH
        graph.setTitle("Test");
//        graph.setTitleTextSize();
//        graph.setTitleColor();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.graph_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId())
        {
            case R.id.mood_item:
                Toast.makeText(this, "Mood", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.valence_item:
                Toast.makeText(this, "Valence", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.frequency_item:
                Toast.makeText(this, "Frequency", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }

    }

//    public DataPoint[] increaseLength(DataPoint[] old, DataPoint dp) {
//        DataPoint[] newArr = new DataPoint[old.length + 1];
//        for(int i = 0; i < old.length; i++) {
//            newArr[i] = old[i];
//        }
//        newArr[old.length] = dp;
//
//        return newArr;
//    }

    //        DataPoint[] redPins = new DataPoint[0];
//        DataPoint[] bluePins = new DataPoint[0];
//        DataPoint[] greenPins = new DataPoint[0];
//        DataPoint[] cyanPins = new DataPoint[0];
//        DataPoint[] whitePins = new DataPoint[0];
//
//        for(int i = 0; i < allPins.size(); i++) {
//            Pin currPin = allPins.get(i);
//            DataPoint dp = new DataPoint(currPin.getX(), currPin.getY() * -1);
//            if((dp.getX() > 15 && dp.getY() >= 0) || (dp.getX() >= 0 && dp.getY() > 15)) {
//                greenPins = increaseLength(greenPins, dp);
//                Log.d("Color test", "GREEN");
//            }
//            else if((dp.getX() > 15 && dp.getY() < 0) || (dp.getX() > 0 && dp.getY() < -15)) {
//                cyanPins = increaseLength(cyanPins, dp);
//                Log.d("Color test", "CYAN");
//            }
//            else if((dp.getX() < -15 && dp.getY() >= 0) || (dp.getX() < -1 && dp.getY() > 15)) {
//                redPins = increaseLength(redPins, dp);
//                Log.d("Color test", "RED");
//            }
//            else if((dp.getX() < -15 && dp.getY() < 0) || (dp.getX() <= 1 && dp.getY() < -15)) {
//                bluePins = increaseLength(bluePins, dp);
//                Log.d("Color test", "BLUE");
//            }
//            else {
//                whitePins = increaseLength(whitePins, dp);
//                Log.d("Color test", "WHITE");
//            }
////            redPins[i] = new DataPoint(currPin.getX(), currPin.getY() * -1);
//        }
//
//        if(redPins.length > 0) {
//            redPoints = new PointsGraphSeries<>(redPins);
//            pointGraph.addSeries(redPoints);
//            redPoints.setColor(Color.RED);
//        }
//        if(bluePins.length > 0) {
//            bluePoints = new PointsGraphSeries<>(bluePins);
//            pointGraph.addSeries(bluePoints);
//            bluePoints.setColor(Color.BLUE);
//        }
//        if(greenPins.length > 0) {
//            greenPoints = new PointsGraphSeries<>(greenPins);
//            pointGraph.addSeries(greenPoints);
//            greenPoints.setColor(Color.GREEN);
//        }
//        if(cyanPins.length > 0) {
//            cyanPoints = new PointsGraphSeries<>(cyanPins);
//            pointGraph.addSeries(cyanPoints);
//            cyanPoints.setColor(Color.CYAN);
//        }
//        if(whitePins.length > 0) {
//            whitePoints = new PointsGraphSeries<>(whitePins);
//            pointGraph.addSeries(whitePoints);
//            whitePoints.setColor(Color.WHITE);
//        }

}
