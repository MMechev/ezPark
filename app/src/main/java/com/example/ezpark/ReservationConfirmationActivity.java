package com.example.ezpark;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

public class ReservationConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_confirmation_activity);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String city = intent.getStringExtra("parking_city");
        String parking = intent.getStringExtra("parking_name");
        String time = intent.getStringExtra("time");
        String date = intent.getStringExtra("date");
        double latitude = intent.getDoubleExtra("parking_latitude",0.0);
        double longitude = intent.getDoubleExtra("parking_longitude",0.0);

        Button reservations = findViewById(R.id.reservations_button3);
        reservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservationConfirmationActivity.this,MyReservationsActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        ReservationConfirmFragment fragment1 = new ReservationConfirmFragment();
        ReservationExtraFragment fragment2 = new ReservationExtraFragment();
    }
}
