package com.example.ezpark;

import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class ReservationFormActivity extends AppCompatActivity {

    private String selected_city;
    private int selected_city_img_id;
    String[] time_slots = {"01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00"
    ,"19:00","20:00","21:00","22:00","23:00","24:00"};
    private String username;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_form);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_TD);
        setSupportActionBar(toolbar);

        Intent reservation_intent = getIntent();
        username = reservation_intent.getStringExtra("username");
        selected_city = reservation_intent.getStringExtra("city_name");
        selected_city_img_id = reservation_intent.getIntExtra("city_img",0);

        ImageView img = (ImageView) findViewById(R.id.city_img);
        img.setImageResource(selected_city_img_id);
        TextView txt = (TextView) findViewById(R.id.city_name);
        txt.setText(selected_city);

        DatePicker datepicker = (DatePicker) findViewById(R.id.date_picker);
        datepicker.setMinDate(System.currentTimeMillis() - 1000);
        datepicker.setSpinnersShown(true);

        int day = datepicker.getDayOfMonth();
        int month = datepicker.getMonth() + 1;
        int year = datepicker.getYear();

        String date = day + "/" +  month + "/" + year;
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,time_slots);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        String time = spinner.getSelectedItem().toString();

        Button reservations = findViewById(R.id.reservations_button1);
        reservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservationFormActivity.this,MyReservationsActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        Button reserve_button = (Button) findViewById(R.id.TD_button);
        reserve_button.setOnClickListener(v -> {
                Intent intent = new Intent(ReservationFormActivity.this,ParkingPlacesActivity.class);
                intent.putExtra("date",date);
                intent.putExtra("time",time);
                intent.putExtra("username",username);
                intent.putExtra("city",selected_city);
                startActivity(intent);
        });

    }

    private boolean validateDateTime(int date,int month,int year){
        return true;
    }
}
