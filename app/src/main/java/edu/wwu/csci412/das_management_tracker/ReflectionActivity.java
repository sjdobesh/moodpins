package edu.wwu.csci412.das_management_tracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class ReflectionActivity extends AppCompatActivity {

    ConstraintLayout reflectionLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.diaryList);

        reflectionLayout = (ConstraintLayout) findViewById(R.id.layout_reflection);

        reflectionLayout.setOnTouchListener(new OnSwipeTouchListener(ReflectionActivity.this) {

            @Override
            public void onSwipeRight() {
                Intent intent = new Intent(ReflectionActivity.this, PinActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_to_right, R.anim.slide_in_from_left);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
