package com.example.ezpark;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterReservations extends RecyclerView.Adapter<ViewHolderReservation> {

    ArrayList<Reservation> reservations;
    Context context;
    String username;

    public RecyclerAdapterReservations(ArrayList<Reservation> reservations, Application application){
        this.reservations = reservations;
        this.context = application;
        this.username = username;
    }

    @NonNull
    @Override
    public ViewHolderReservation onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation,parent,false);
        ViewHolderReservation holder2 = new ViewHolderReservation(v);
        return holder2;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderReservation holder2, int position) {
        //
        holder2.parking_name.setText(reservations.get(position).getParking());
        holder2.city_name.setText(reservations.get(position).getCity());
        holder2.date.setText(reservations.get(position).getDate());
        holder2.time.setText(reservations.get(position).getTime());
        holder2.qr_code.setImageBitmap(reservations.get(position).getBitmap());
        holder2.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(context);
                db.deleteReservation(reservations.get(position).getId());
                reservations.remove(reservations.get(position));
                db.close();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

}
