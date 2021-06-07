package edu.wwu.csci412.das_management_tracker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import static edu.wwu.csci412.das_management_tracker.PinActivity.get_formatted_date;

public class AddEntry extends AppCompatActivity {
    DatabaseManager db = new DatabaseManager(this);
    Toolbar toolbar;
    EditText title;
    EditText entry;
    Calendar cal;
    String Date;
    String Time;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Entry");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title = findViewById(R.id.diaryTitle);
        entry = findViewById(R.id.entry);

        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() !=0){
                    getSupportActionBar().setTitle(charSequence);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        cal = Calendar.getInstance();
        Date = cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DAY_OF_MONTH);
        Time = pad(cal.get(Calendar.HOUR))+":"+pad(cal.get(Calendar.MINUTE));

        Log.d("calendar", "dateandtime: "+ Date+" "+Time);


    }

    private String pad(int i) {
        if(i<10) return "0"+i;
        return ""+i;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete){
            Toast.makeText(this, "save btn is clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}