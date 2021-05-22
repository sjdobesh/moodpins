package edu.wwu.csci412.das_management_tracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import top.defaults.colorpicker.ColorObserver;
import top.defaults.colorpicker.ColorPickerView;

public class PinActivity extends AppCompatActivity {

    // color picker vars
    // text view variable to set the color for GFG text
    private TextView pinTextView;
    // color wheel
    private ColorPickerView colorPicker;

    ConstraintLayout pinLayout;

    // Utility function converts rgb hex to hsv to x,y coordinates
    public static String rgb2xy(String RGBHex) {
        // separate 0xXXXXXXXX -> 0xXX XX XX XX -> a r g b
        // divide by 255 to normalize values between 0 and 1
        double r,g,b;
        r = (double)Integer.parseInt(RGBHex.substring(2,3), 16) / 255.0;
        g = (double)Integer.parseInt(RGBHex.substring(4,5), 16) / 255.0;
        b = (double)Integer.parseInt(RGBHex.substring(6,7), 16) / 255.0;

        // convert to hsv (we actually just need h and s for coordinates)
        double color_max = Math.max(r, Math.max(g,b));
        double color_min = Math.min(r, Math.min(g,b));
        double color_diff = color_max-color_min;
        double h = -1;
        double s;

        // hue
        if (color_max == color_min) {
            h = 0;
        } else if (color_max == r){
            h = compute_hue(g, b, color_diff, 360.0);
        } else if (color_max == g){
            h = compute_hue(b, r, color_diff, 120.0);
        } else if (color_max == b){
            h = compute_hue(r, g, color_diff, 240.0);
        }
        // saturation
        s = color_max == 0 ? 0 : (color_diff / color_max) * 100.0;
        // account for rotation
        h -= 60;
        h = h < 0 ? h+360 : h;

        // convert to (x, y) coordinates
        double y = s * Math.sin(Math.toRadians(h));
        double x = s * Math.cos(Math.toRadians(h));
        return x+","+y;
    }

    public static double compute_hue(double c1, double c2, double color_diff, double axis_deg) {
        return (60.0 * ((c1-c2)/color_diff) + axis_deg) % 360.0;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // create and load view layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        pinLayout = (ConstraintLayout) findViewById(R.id.layout_pins);

        // inspect value from color wheel
        final TextView data_debug = new TextView(this);
        pinLayout.addView(data_debug);

        // register the text heading
        pinTextView = findViewById(R.id.pin_heading);
        // get color picker
        colorPicker = findViewById(R.id.colorPicker);
        // get the color from wheel
        colorPicker.subscribe(new ColorObserver() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onColor(int color, boolean fromUser, boolean shouldPropagate) {
                // use the color
                pinTextView.setTextColor(color);
                LocalDateTime date = LocalDateTime.now();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                data_debug.setText("Data Point : (" + rgb2xy(Integer.toHexString(color))+", "
                                                    + dtf.format(date)+")");
            }
        });

        // swipe controls
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
