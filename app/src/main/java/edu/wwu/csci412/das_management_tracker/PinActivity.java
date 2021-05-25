package edu.wwu.csci412.das_management_tracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import top.defaults.colorpicker.ColorObserver;
import top.defaults.colorpicker.ColorPickerView;

public class PinActivity extends AppCompatActivity {

    private AlertDialog.Builder popupBuilder;
    private AlertDialog settings_popup;
    // vars
    ConstraintLayout pin_layout;          // layout
    private TextView pin_text;            // 'drop a pin' text
    private ColorPickerView color_wheel;  // circular color picker
    private Button confirm_button;        // confirm new data point
    private double[] coords = {0, 0};     // last color coordinates (x, y)
    private ImageView selector;           // emoji popup


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        pin_layout = (ConstraintLayout) findViewById(R.id.layout_pins);

        // find xml views
        pin_text       = findViewById(R.id.pin_heading);
        selector          = findViewById(R.id.popup);
        confirm_button = findViewById(R.id.confirm_button);
        color_wheel    = findViewById(R.id.color_wheel);

        // debug text object
        final TextView data_debug = new TextView(this);
        pin_layout.addView(data_debug);

        // starting color is white
        color_wheel.setInitialColor(0xffffffff);

        // access color from wheel
        color_wheel.subscribe(new ColorObserver() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onColor(int color_subscribe, boolean fromUser, boolean shouldPropagate) {

                // update buttons and text
                pin_text.setTextColor(color_subscribe);
                confirm_button.setTextColor(color_subscribe);
                confirm_button.setVisibility(View.VISIBLE);


                // update exposed coordinates
                coords = rgb2xy(Integer.toHexString(color_subscribe));

                // update and move emoji popup
                int emoji_res = get_emotion(rgb2hs(Integer.toHexString(color_subscribe)));
                selector.setImageResource(emoji_res);

                // work out where on the screen this is using the
                // color coords and center of color wheel
                double x = (color_wheel.getX() + (color_wheel.getWidth()  / 2.0))
                           - (selector.getWidth()  / 2.0);
                double y = (color_wheel.getY() + (color_wheel.getHeight() / 2.0))
                           - (selector.getHeight() / 2.0);
                x += coords[0] * color_wheel.getWidth()/200.0 * 0.9;
                y += coords[1] * color_wheel.getWidth()/200.0 * 0.9;
                selector.setX((float)x);
                selector.setY((float)y);

                // DEBUG
                // data_debug.setText("Data Point : (" + coords[0] + "," + coords[1] + ","+ date+")");
            }
        });

        // add a new data point
        confirm_button.setOnClickListener(
            new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(final View v) {
                    confirm_button.setVisibility(View.GONE);
                    add_db_entry();
                    color_wheel.setInitialColor(0xffffffff);
                    confirm_button.setVisibility(View.GONE);
                }
            });

        // swipe controls
        pin_layout.setOnTouchListener(new OnSwipeTouchListener(PinActivity.this) {
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

    public void settings(View view) {
        popupBuilder = new AlertDialog.Builder(this);
        View settingsView = getLayoutInflater().inflate(R.layout.activity_settings, null);
        Button cancel_btn = settingsView.findViewById(R.id.cancel);

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                settings_popup.dismiss();
            }
        });
        Button confirm_btn = settingsView.findViewById(R.id.confirm);
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                settings_popup.dismiss();
            }
        });
        
        popupBuilder.setView(settingsView);
        settings_popup = popupBuilder.create();
        settings_popup.show();
    }

    // figure out which emoji to use based on hue and saturation
    public static int get_emotion(double[] hs) {
        int resource = 0;
        if (hs[1] < 50.0) {
            resource = R.drawable.nothing;
        }
        else {
            // get angle partition
            hs[0] = (hs[0] > 337.5) ? hs[0] - 337.5 : hs[0] + 22.5;
            int angle_partition = (int)hs[0] / 45;
            switch (angle_partition) {
                case 0:
                    resource = R.drawable.energetic;
                    break;
                case 1:
                    resource = R.drawable.happy;
                    break;
                case 2:
                    resource = R.drawable.positive;
                    break;
                case 3:
                    resource = R.drawable.content;
                    break;
                case 4:
                    resource = R.drawable.calm;
                    break;
                case 5:
                    resource = R.drawable.sad;
                    break;
                case 6:
                    resource = R.drawable.negative;
                    break;
                case 7:
                    resource = R.drawable.angry;
                    break;
            }
        }
        return resource;
    }

    // add database entry
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void add_db_entry() {
        String date = get_formatted_date();
        System.out.println("Adding data point: "+coords[0]+","+coords[1]+","+date);
        // TODO add entry to data base here
    }

    public static double[] rgb2xy(String RGBHex) {
       double[] hs = rgb2hs(RGBHex);
        return hs2cartesian(hs[0], hs[1]);
    }

    // Utility function converts rgb hex to hs(v)
    public static double[] rgb2hs(String RGBHex) {
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

        return new double[]{h, s};
    }

    public static double[] hs2cartesian(double h, double s) {
        double x =  s * Math.sin(Math.toRadians(h));
        double y = -s * Math.cos(Math.toRadians(h));
        return new double[]{x, y};
    }

    // helper to compute hue val
    public static double compute_hue(double c1, double c2, double color_diff, double axis_deg) {
        return (60.0 * ((c1-c2)/color_diff) + axis_deg) % 360.0;
    }

    // get current date and time with pre specified format
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String get_formatted_date() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dtf.format(LocalDateTime.now());
    }
}
