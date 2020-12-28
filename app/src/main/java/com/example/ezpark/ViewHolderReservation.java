package com.example.ezpark;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderReservation extends RecyclerView.ViewHolder {

    public TextView parking_name;
    public TextView city_name;
    public TextView date;
    public TextView time;
    public ImageView qr_code;

    public ViewHolderReservation(View itemView){
        super(itemView);
        parking_name = (TextView) itemView.findViewById(R.id.parking_name1);
        city_name = (TextView) itemView.findViewById(R.id.city1);
        date = (TextView) itemView.findViewById(R.id.date1);
        time = (TextView) itemView.findViewById(R.id.time1);
        qr_code = (ImageView) itemView.findViewById(R.id.QR_code_reservations);
    }
}
