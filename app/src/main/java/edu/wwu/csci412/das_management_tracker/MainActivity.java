package edu.wwu.csci412.das_management_tracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextPaint;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    TextView splash;
    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splash = (TextView) findViewById(R.id.splashtext);

        TextPaint paint = splash.getPaint();
        float width = paint.measureText("Moodpins");

        Shader textShader = new LinearGradient(0, 0, width, splash.getTextSize(),
                new int[]{
                        Color.parseColor("#F97C3C"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),
                }, null, Shader.TileMode.CLAMP);
        splash.getPaint().setShader(textShader);

        Thread welcome = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(2000);
                } catch (Exception error) {

                } finally {

                    Intent intent = new Intent(MainActivity.this, PinActivity.class);
                    startActivity(intent);
//                    SplashAnimation();
                    overridePendingTransition(0, R.anim.zoom_exit);
                    finish();
                }
            }
        };
        welcome.start();
    }

//    /**
//     * SplashAnimation
//     *
//     * (* WIP *) Makes the splash screen text bounce in and out
//     */
//    private void SplashAnimation() {
//
//        Animation animin = AnimationUtils.loadAnimation(this, R.anim.splash_anim_in);
//        Animation animout = AnimationUtils.loadAnimation(this, R.anim.splash_anim_out);
//        animin.reset();
//        animout.reset();
//        splash.clearAnimation();
//        splash.startAnimation(animin);
//
//        h.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                splash.startAnimation(animout);
//            }
//        }, 400);
//    }

}