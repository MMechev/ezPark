package com.example.ezpark;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class RecyclerAdapterParkings extends RecyclerView.Adapter<ViewHolderParking> {

    ArrayList<Parking> parking;
    Context context;
    String username;
    String city;
    String date;
    String time;
    int reservations;

    public RecyclerAdapterParkings(ArrayList<Parking> parking, Application application, String username, String city, String date, String time){
        this.parking = parking;
        this.context = application;
        this.username = username;
        this.city = city;
        this.date = date;
        this.time = time;
    }

    @NonNull
    @Override
    public ViewHolderParking onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.parking, parent, false);
        ViewHolderParking holder1 = new ViewHolderParking(v);
        return holder1;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderParking holder1, int position) {
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(context);
                reservations = db.checkReservations(city,time,date,parking.get(position).getName());
                int freespaces = parking.get(position).getNum_spaces();
                if(db.checkUsernameReservations(username) && freespaces > 0){

                    String data = username + city + parking.get(position).getName() + date + time;
                    QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 300);
                    Bitmap bitmap = qrgEncoder.getBitmap();

                    Reservation reservation = new Reservation(username, city, parking.get(position).getName(), time, date, bitmap);
                    db = new DatabaseHelper(context);
                    db.addReservation(reservation);
                    db.close();

                    Intent intent = new Intent(context,ReservationConfirmationActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("parking_latitude", parking.get(position).getLatitude());
                    intent.putExtra("parking_longitude", parking.get(position).getLongitude());
                    intent.putExtra("parking_city", parking.get(position).getCity());
                    intent.putExtra("parking_name", parking.get(position).getName());
                    intent.putExtra("time",time);
                    intent.putExtra("date",date);
                    db.close();
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context,"You already have 3 reservations!",Toast.LENGTH_LONG).show();
                }

            }
        });

        DatabaseHelper db = new DatabaseHelper(context);
        reservations = db.checkReservations(city,time,date,parking.get(position).getName());
        db.close();


        holder1.parking_name.setText(parking.get(position).getName()+"");
        holder1.reserved.setImageResource(R.drawable.reserved_spots);
        holder1.free.setImageResource(R.drawable.free_spots);
        holder1.reserved_spaces.setText(String.valueOf(reservations));
        holder1.free_spaces.setText(parking.get(position).getNum_spaces()-reservations+"");

    }

    @Override
    public int getItemCount() {
        return parking.size();
    }
}
