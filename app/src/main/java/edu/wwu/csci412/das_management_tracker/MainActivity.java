package edu.wwu.csci412.das_management_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread welcome = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(3000);
                } catch (Exception error) {

                } finally {

                    Intent intent = new Intent(MainActivity.this, PinActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        welcome.start();
    }
}