package edu.wwu.csci412.das_management_tracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
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
    int dayRangeInteger;
    Toolbar toolbar;

    ArrayList<Pin> allPinsAsc, allPinsTime;
    LineGraphSeries<DataPoint> seriesLine;
    DatabaseManager db = new DatabaseManager(this);

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

        dayRangeInteger = Integer.parseInt(dayRange.getText().toString());

        // toolbar fixes
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Analysis");
        toolbar.setTitleTextAppearance(this, R.style.MontserratText);

        // POINT GRAPH WORK
        allPinsAsc = db.selectAllPins();

        pointGraph.getViewport().setXAxisBoundsManual(true);
        pointGraph.getViewport().setYAxisBoundsManual(true);
        pointGraph.getViewport().setMinX(-100);
        pointGraph.getViewport().setMaxX(100);
        pointGraph.getViewport().setMinY(-100);
        pointGraph.getViewport().setMaxY(100);

        for(int i = 0; i < allPinsAsc.size(); i++) {
            Pin currPin = allPinsAsc.get(i);
            DataPoint dp = new DataPoint(currPin.getX(), currPin.getY() * -1);
            DataPoint[] point = {dp};
            if(currPin.getId() >= allPinsAsc.size() - dayRangeInteger + 1) {
                PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(point);
                pointGraph.addSeries(series);
                series.setColor(currPin.getColor());
            }
        }

        pointGraph.setTitle("Pin Distribution");
        pointGraph.getGridLabelRenderer().setVerticalLabelsVisible(false);
        pointGraph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        pointGraph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.BOTH);



        /**
         * WORK USING GRAPHVIEW PACKAGE
         * WIKI LINK:   https://github.com/jjoe64/GraphView/wiki/Summary-and-Features
         */


        // MAKING A SERIES
//        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
//                new DataPoint(0, 1),
//                new DataPoint(1, 5),
//                new DataPoint(2, 3),
//                new DataPoint(3, 2),
//                new DataPoint(4, 6),
//        });

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(dayRangeInteger);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(100);

        displayMoodGraph(dayRangeInteger);

//        allPinsTime = db.selectAllPinsByTime();
//        DataPoint[] timePoints = new DataPoint[allPinsTime.size()];
//
//        for(int i = 0; i < allPinsTime.size(); i++) {
//            Pin currPin = allPinsTime.get(i);
//            DataPoint dp = new DataPoint(i + 1, currPin.getX());
//            timePoints[i] = dp;
//        }
//
//        LineGraphSeries<DataPoint> seriesTime = new LineGraphSeries<DataPoint>(timePoints);
//        graph.addSeries(seriesTime);

//        graph.addSeries(series);

        // MAKING A 2nd SERIES
//        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(new DataPoint[] {
//                new DataPoint(0, 30),
//                new DataPoint(1, 30),
//                new DataPoint(2, 60),
//                new DataPoint(3, 20),
//                new DataPoint(4, 50),
//        });

        // ADDING SECOND SCALE TO RIGHT HAND SIDE
//        graph.getSecondScale().addSeries(series2);
//        graph.getSecondScale().setMinY(0);
//        graph.getSecondScale().setMaxY(100);
//        series2.setColor(Color.RED);
//        graph.getGridLabelRenderer().setVerticalLabelsSecondScaleColor(Color.RED);

//         DATA POINT CLICK HANDLER
//        series.setOnDataPointTapListener(new OnDataPointTapListener() {
//            @Override
//            public void onTap(Series series, DataPointInterface dataPoint) {
//                Toast.makeText(AnalysisActivity.this, "Series1: On Data Point Clicked: " + dataPoint, Toast.LENGTH_SHORT).show();
//            }
//        });

        // LEGEND CREATION
//        seriesTime.setTitle("Mood");
//        series.setTitle("Mood");
//        series2.setTitle("bar");
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
//        graph.setTitleTextSize();
//        graph.setTitleColor();

        analysisLayout.setOnTouchListener(new OnSwipeTouchListener(AnalysisActivity.this) {

            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(AnalysisActivity.this, PinActivity.class);
                intent.putExtra("colorStart", "analysis");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }

        });

        dayRange.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if(!TextUtils.isEmpty(dayRange.getText().toString()) && TextUtils.isDigitsOnly(dayRange.getText().toString())) {
                        dayRangeInteger = Integer.parseInt(dayRange.getText().toString());
                    }
                    else {
                        dayRangeInteger = 30;
                        Log.d("INPUT", "Day Range input invalid");
                    }

                    if(dayRangeInteger < 1) {
                        dayRangeInteger = 30;
                        Log.d("INPUT", "Day Range input invalid (Less than 1)");
                    }

//                    graph.removeAllSeries();
                    updateGraphAtRange(dayRangeInteger);
//                    graph.addSeries(seriesTime);
                    updateDistributionAtRange(dayRangeInteger);

//                    graph.removeAllSeries();
//                    graph.addSeries(test);
//                    test.setTitle("Mood");
                }
                return false;
            }
        });


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
                displayMoodGraph(dayRangeInteger);
                return true;
            case R.id.valence_item:
                Toast.makeText(this, "Valence", Toast.LENGTH_SHORT).show();
                displayValenceGraph(dayRangeInteger);
                return true;
            case R.id.frequency_item:
                Toast.makeText(this, "Frequency", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }

    }

    public void updateGraphAtRange(int range) {

        graph.removeAllSeries();

        if(range >= allPinsAsc.size()) {
            graph.getViewport().setMinX(1);
            graph.getViewport().setMaxX(range);
        }
        else {
            graph.getViewport().setMinX(allPinsAsc.size() - range + 1);
            graph.getViewport().setMaxX(allPinsAsc.size());
        }
//        graph.getViewport().setMinX(allPinsAsc.size() - range + 1);
//        graph.getViewport().setMaxX(allPinsAsc.size());
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(true);

        graph.addSeries(seriesLine);

    }

    public void updateDistributionAtRange(int range) {

        pointGraph.removeAllSeries();

        for(int i = 0; i < allPinsAsc.size(); i++) {
            Pin currPin = allPinsAsc.get(i);
            DataPoint dp = new DataPoint(currPin.getX(), currPin.getY() * -1);
            DataPoint[] point = {dp};
            if(currPin.getId() >= allPinsAsc.size() - range + 1) {
                PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(point);
                pointGraph.addSeries(series);
                series.setColor(currPin.getColor());
            }
        }
    }

    public void displayValenceGraph(int range) {

        graph.removeAllSeries();

        allPinsTime = db.selectAllPinsByTime();
        DataPoint[] moodPoints = new DataPoint[allPinsTime.size()];

        for(int i = 0; i < allPinsTime.size(); i++) {
            Pin currPin = allPinsTime.get(i);
            DataPoint dp = new DataPoint(currPin.getId(), ((currPin.getY() * -1) + 100) / 2);
            moodPoints[i] = dp;
        }

        seriesLine = new LineGraphSeries<DataPoint>(moodPoints);
        graph.addSeries(seriesLine);
        seriesLine.setTitle("Valence");
        graph.setTitle("Valence Tracking");
        seriesLine.setColor(Color.RED);
        seriesLine.setDrawDataPoints(true);
    }

    public void displayMoodGraph(int range) {

        graph.removeAllSeries();

        graph.removeAllSeries();
        allPinsTime = db.selectAllPinsByTime();
        DataPoint[] valencePoints = new DataPoint[allPinsTime.size()];

        for(int i = 0; i < allPinsTime.size(); i++) {
            Pin currPin = allPinsTime.get(i);
            DataPoint dp = new DataPoint(currPin.getId(), (currPin.getX() + 100) / 2);
            valencePoints[i] = dp;
        }

        seriesLine = new LineGraphSeries<DataPoint>(valencePoints);
        graph.addSeries(seriesLine);
        seriesLine.setTitle("Mood");
        graph.setTitle("Mood Tracking");
        seriesLine.setColor(Color.CYAN);
        seriesLine.setDrawDataPoints(true);
    }
}
