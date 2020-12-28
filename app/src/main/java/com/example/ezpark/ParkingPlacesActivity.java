package com.example.ezpark;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ParkingPlacesActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private String city;
    private String username;
    private String date;
    private String time;
    private ArrayList<Parking> parking_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_places_activity);

        Toolbar mainToolbar = findViewById(R.id.toolbar_parking);
        setSupportActionBar(mainToolbar);

        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        username = intent.getStringExtra("username");
        date = intent.getStringExtra("date");
        time = intent.getStringExtra("time");

        Button reservations = findViewById(R.id.reservations_button2);
        reservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParkingPlacesActivity.this,MyReservationsActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });



        db = new DatabaseHelper(this);
        parking_list = db.getParkings(city);

        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.parking_list);
        RecyclerAdapterParkings adapter = new RecyclerAdapterParkings(parking_list,getApplication(),username,city,date,time);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

    }
}
