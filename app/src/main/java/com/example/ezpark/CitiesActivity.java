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

public class CitiesActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities_activity);

        Toolbar mainToolbar = findViewById(R.id.toolbar_cities);
        setSupportActionBar(mainToolbar);

        final ArrayList<City> cities = new ArrayList<City>();
        cities.add(new City("Skopje", "Parking Lots: ",
                8,R.drawable.skopje));
        cities.add(new City("Ohrid", "Parking Lots: ",
                5,R.drawable.ohrid));
        cities.add(new City("Kumanovo", "Parking Lots: ",
                3,R.drawable.kumanovo));
        cities.add(new City("Prilep", "Parking Lots: ",
                3,R.drawable.prilep));
        cities.add(new City("Bitola", "Parking Lots: ",
                4,R.drawable.bitola));
        cities.add(new City("Strumica", "Parking Lots: ",
                3,R.drawable.strumica));
        cities.add(new City("Veles", "Parking Lots: ",
                3,R.drawable.veles));

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        Button reservations = findViewById(R.id.reservations_button);
        reservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CitiesActivity.this,MyReservationsActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.city_list);
        RecyclerAdapterCities adapter = new RecyclerAdapterCities(cities,getApplication(),username);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));


    }
}
