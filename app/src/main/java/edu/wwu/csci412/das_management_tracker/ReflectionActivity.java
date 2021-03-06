package edu.wwu.csci412.das_management_tracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class ReflectionActivity extends AppCompatActivity {

    ConstraintLayout reflectionLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    AlertDialog.Builder popupBuilder;
    AlertDialog popup;
    Adapter adapter;
    List<DiaryEntry> entries;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.diaryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DatabaseManager db = new DatabaseManager(this);
        entries = db.selectAllEntries();
        adapter = new Adapter(this,entries);
        recyclerView.setAdapter(adapter);


        reflectionLayout = (ConstraintLayout) findViewById(R.id.layout_reflection);

        reflectionLayout.setOnTouchListener(new OnSwipeTouchListener(ReflectionActivity.this) {

            @Override
            public void onSwipeRight() {
                Intent intent = new Intent(ReflectionActivity.this, PinActivity.class);
                intent.putExtra("colorStart", "reflection");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_to_right, R.anim.slide_in_from_left);
            }

        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_entry){
            Intent intent = new Intent(this,AddEntry.class);
            startActivity(intent);
            Toast.makeText(this, "Add btn is clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    /*public void addNote(View view) {
        //set up popup reflection (diary) button
        popupBuilder = new AlertDialog.Builder(this);
        View entryView = getLayoutInflater().inflate(R.layout.activity_add_entry, null);
        popupBuilder.setView(entryView);
        popup = popupBuilder.create();
        popup.show();
    }*/


}
