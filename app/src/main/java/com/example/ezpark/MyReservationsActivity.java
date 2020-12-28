package com.example.ezpark;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyReservationsActivity extends AppCompatActivity {

    String username;
    ArrayList<Reservation> reservations;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_reservations_activity);

        Toolbar mainToolbar = findViewById(R.id.toolbar_reservations);
        setSupportActionBar(mainToolbar);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        DatabaseHelper db = new DatabaseHelper(this);
        reservations = db.getReservations(username);

        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.reservation_list);
        RecyclerAdapterReservations adapter = new RecyclerAdapterReservations(reservations,getApplication());
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));


    }
}
